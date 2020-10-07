package com.kodilla.frontend.ui.view;

import com.kodilla.frontend.domian.AppUserDtoMap;
import com.kodilla.frontend.domian.AppUserInfoDto;
import com.kodilla.frontend.domian.AppUserInfoDtoMap;
import com.kodilla.frontend.domian.AppUserMessage;
import com.kodilla.frontend.service.AppUserInfoService;
import com.kodilla.frontend.service.AppUserMessageService;
import com.kodilla.frontend.ui.MainView;
import com.kodilla.frontend.validate.ValidateFormField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;


import java.time.LocalDate;


@CssImport("./style.css")
@Route(value = "messageView", layout = MainView.class)
public class MessageView extends VerticalLayout {

    private static final String OK_NOTIFICATION = "Message was send";
    private static final String NOT_OK_NOTIFICATION = "Something gone wrong";

    private TextField emailField = new TextField("Email");
    private TextArea messageField = new TextArea("Your message to Admin");
    private Button sendButton = new Button("Send");
    private Notification notOkNotification = new Notification(NOT_OK_NOTIFICATION,3000);
    private Notification okNotification = new Notification(OK_NOTIFICATION,3000);

    private AppUserMessageService appUserMessageService;
    private AppUserInfoService appUserInfoService;
    private ValidateFormField validateFormField;

    public MessageView(AppUserMessageService appUserMessageService,
                       AppUserInfoService appUserInfoService, ValidateFormField validateFormField) {
        this.validateFormField = validateFormField;
        this.appUserMessageService = appUserMessageService;
        this.appUserInfoService = appUserInfoService;
        initValue();
        setValueInFields();
        clickSendButton();
        add(emailField,messageField,sendButton);
    }

    private void initValue() {
        addClassName("message-view");
        messageField.addClassName("message-field");
        emailField.setReadOnly(true);
    }

    private void setValueInFields() {
        if(AppUserInfoDtoMap.getInstance().getMapAppUserInfoDto().containsKey(VaadinSession.getCurrent())){
            AppUserInfoDto appUserInfoDto = AppUserInfoDtoMap.getInstance().getAppUserByKey(VaadinSession.getCurrent());
            emailField.setValue(appUserInfoDto.getEmail());
            messageField.setPlaceholder(appUserInfoDto.getFirstName() + " write here...");
        } else {
            tryFindInBackend();
        }
    }

    private void tryFindInBackend() {
        Long appUserId = AppUserDtoMap.getInstance().getAppUserDtoByKey(VaadinSession.getCurrent()).getId();
        AppUserInfoDto appUserInfoDto = appUserInfoService.getAppUserInfoByAppUserId(appUserId);
        if(appUserInfoDto.getFirstName()!= null){
            AppUserInfoDtoMap.getInstance().addToMap(VaadinSession.getCurrent(), appUserInfoDto);
            emailField.setValue(appUserInfoDto.getEmail());
            messageField.setPlaceholder(appUserInfoDto.getFirstName() + " write here...");
        } else {
            emailField.setReadOnly(false);
            emailField.setPlaceholder("Your email address");
            messageField.setPlaceholder ("Dear user write here...");
        }
    }

    private void clickSendButton() {
        sendButton.addClickListener(event -> {
            if(verifyFields()){
                sendMessageProcess();
            }
        });
    }

    private boolean verifyFields() {
        return validateFormField.validateEmailField(emailField.getValue()) &&
                validateFormField.validateTextArenaField(messageField.getValue());
    }

    private void sendMessageProcess() {
        Boolean result = appUserMessageService.createNewMessage(
                new AppUserMessage(emailField.getValue(),messageField.getValue(), LocalDate.now()));
        if(result){
            okNotification.open();
            messageField.clear();
        } else {
            notOkNotification.open();
        }
    }

}

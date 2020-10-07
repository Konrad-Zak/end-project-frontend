package com.kodilla.frontend.ui.view;

import com.kodilla.frontend.domian.*;
import com.kodilla.frontend.service.AppUserInfoService;
import com.kodilla.frontend.ui.MainView;
import com.kodilla.frontend.validate.ValidateFormField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@CssImport("./style.css")
@Route(value = "userInfoView", layout = MainView.class)
public class UserInfoView extends FormLayout {

    private static final String OK_NOTIFICATION = "Name and email saved";
    private static final String NOT_NOTIFICATION = "Name and email were not save";
    private TextField firstName = new TextField("First name");
    private EmailField email = new EmailField("Email");
    private Button create = new Button("Create");
    private Button update = new Button("Update");
    private Notification okNotification = new Notification(OK_NOTIFICATION,3000);
    private Notification notOkNotification = new Notification(NOT_NOTIFICATION, 3000);
    private AppUserInfoService appUserInfoService;
    private ValidateFormField validateFormField;

    public UserInfoView(AppUserInfoService appUserInfoService, ValidateFormField validateFormField) {
        addClassName("userInfo-form");
        this.validateFormField = validateFormField;
        this.appUserInfoService = appUserInfoService;
        setResponsiveSteps(
                new ResponsiveStep("10%",1),
                new ResponsiveStep("10%", 1),
                new ResponsiveStep("10%", 1),
                new ResponsiveStep("10%", 1));
        update.setVisible(false);
        initValues();
        clickCreateButton();
        clickUpdateButton();
        add(firstName, email, create, update);
    }

    private void initValues() {
        if (AppUserInfoDtoMap.getInstance().getMapAppUserInfoDto().containsKey(VaadinSession.getCurrent())) {
            getFromAppUserInfoDtoMap();
        } else {
            getFormBackend();
        }
    }

    private void getFormBackend() {
        Long appUserId = AppUserDtoMap.getInstance().getAppUserDtoByKey(VaadinSession.getCurrent()).getId();
        AppUserInfoDto appUserInfoDto = appUserInfoService.getAppUserInfoByAppUserId(appUserId);
        if(appUserInfoDto.getFirstName()!= null) {
            AppUserInfoDtoMap.getInstance().addToMap(VaadinSession.getCurrent(), appUserInfoDto);
            firstName.setValue(appUserInfoDto.getFirstName());
            email.setValue(appUserInfoDto.getEmail());
            create.setVisible(false);
            update.setVisible(true);
        }
    }

    private void getFromAppUserInfoDtoMap() {
        AppUserInfoDto appUserInfoDto = AppUserInfoDtoMap.getInstance().getAppUserByKey(VaadinSession.getCurrent());
        firstName.setValue(appUserInfoDto.getFirstName());
        email.setValue(appUserInfoDto.getEmail());
        create.setVisible(false);
        update.setVisible(true);
    }

    private void clickCreateButton() {
        create.addClickListener(event -> {
            if(verifyTextFields()){
                createUserInfoProcess();
            } else {
                notOkNotification.open();
            }
        });
    }

    private void createUserInfoProcess() {
        AppUserDto appUserDto = AppUserDtoMap.getInstance().getAppUserDtoByKey(VaadinSession.getCurrent());
        AppUserInfo appUserInfo = new AppUserInfo(firstName.getValue(), email.getValue(),
                new AppUser(appUserDto.getId(),appUserDto.getUsername(),appUserDto.getPassword()));
        Boolean result = appUserInfoService.createAppUserInfo(appUserInfo);
        if(result){
            okNotification.open();
            initValues();
        }
    }

    private void clickUpdateButton() {
        update.addClickListener(event -> {
            if(verifyTextFields()){
                updateUserInfoProcess();
                okNotification.open();
            } else {
                notOkNotification.open();
            }
        });
    }

    private void updateUserInfoProcess() {
        Long appUserInfoId = AppUserInfoDtoMap.getInstance().getAppUserByKey(VaadinSession.getCurrent()).getId();
        changeFirstNameAndEmail(appUserInfoId);
        AppUserDto appUserDto = AppUserDtoMap.getInstance().getAppUserDtoByKey(VaadinSession.getCurrent());
        AppUserInfo appUserInfo = new AppUserInfo(appUserInfoId,firstName.getValue(), email.getValue(),
                new AppUser(appUserDto.getId(),appUserDto.getUsername(),appUserDto.getPassword()));
        appUserInfoService.updateAppUserInfoDto(appUserInfo);
    }

    private void changeFirstNameAndEmail(Long appUserInfoId) {
        AppUserInfoDtoMap.getInstance().addToMap(
                VaadinSession.getCurrent(),new AppUserInfoDto(appUserInfoId,firstName.getValue(),email.getValue())
        );
    }

    private boolean verifyTextFields() {
        return validateFormField.validateFirstNameField(firstName.getValue()) &
                validateFormField.validateEmailField(email.getValue());
    }

}

package com.kodilla.frontend.ui.view;


import com.kodilla.frontend.domian.AppUser;
import com.kodilla.frontend.domian.AppUserDto;
import com.kodilla.frontend.domian.AppUserDtoMap;
import com.kodilla.frontend.service.AppUserService;
import com.kodilla.frontend.ui.MainView;
import com.kodilla.frontend.validate.ValidateFormField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@CssImport("./style.css")
@Route(value = "changePassword", layout = MainView.class)
public class ChangePasswordView extends FormLayout {

    private final static String OK_NOTIFICATION = "Password has been changed";
    private final static String NOT_NOTIFICATION = "Change password error";

    private TextField loginName = new TextField("Login");
    private PasswordField newPassword = new PasswordField("New password");
    private Button upgradeButton = new Button("Change Password");
    private Notification changePasswordIsOk = new Notification(OK_NOTIFICATION,3000);
    private Notification changePasswordIsNotOk = new Notification(NOT_NOTIFICATION, 3000);
    private AppUserService appUserService;

    public ChangePasswordView(AppUserService appUserService) {
        this.appUserService = appUserService;
        initView();
        clickUpgradeButton();
        add(loginName,newPassword, upgradeButton);
    }

    private void initView(){
        addClassName("changePassword-form");
        loginName.setValue(AppUserDtoMap.getInstance().getAppUserDtoByKey(VaadinSession.getCurrent()).getUsername());
        loginName.setReadOnly(true);
        setResponsiveSteps(
                new ResponsiveStep("10%", 1),
                new ResponsiveStep("10%", 1));
    }

    private void clickUpgradeButton(){
        upgradeButton.addClickListener(event -> {

            if(ValidateFormField.getInstance().validatePasswordField(newPassword.getValue())){
                AppUserDto appUserDto = AppUserDtoMap.getInstance().getAppUserDtoByKey(VaadinSession.getCurrent());
                appUserService.changeUserPassword(
                        new AppUser(appUserDto.getId(), appUserDto.getUsername(), newPassword.getValue())
                );
                changePasswordIsOk.open();
            } else {
                changePasswordIsNotOk.open();
            }
        });
    }
}
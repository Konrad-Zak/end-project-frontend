package com.kodilla.frontend.ui.view;

import com.kodilla.frontend.service.AppUserService;
import com.kodilla.frontend.validate.ValidateFormField;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route("login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final static String ERROR_NOTIFICATION = "User exist with this username";
    private final static String OK_NOTIFICATION = "New User has been added";
    private final static String CREATE_USER_ERROR = "Something go wrong";

    private LoginForm login = new LoginForm();
    private Register register = new Register();
    private LoginI18n loginI18n = LoginI18n.createDefault();
    private Notification registerErrorNotification = new Notification(ERROR_NOTIFICATION,3000);
    private Notification registerOkNotification = new Notification(OK_NOTIFICATION,3000);
    private Notification registerCreateUserError = new Notification(CREATE_USER_ERROR,3000);
    private AppUserService appUserService;

    public LoginView(AppUserService appUserService){
        this.appUserService = appUserService;
        prepareLoginForm();
        add(new H1("App nutritional plan"),login , register);
        clickRegisterNewUserButton();
        clickRegisterButton();
        clickRegisterBackButton();
    }


    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }

    private void clickRegisterBackButton() {
        register.getBack().addClickListener(event -> {
            clearRegisterFields();
            goToLogin();
        });
    }

    private void clickRegisterNewUserButton() {
        login.addForgotPasswordListener(event -> goToRegister());
    }

    private void clickRegisterButton() {
        register.getButton().addClickListener(event -> {
            if(validateFieldsData()){
                startCreateNewUser();
            } else {
                registerCreateUserError.open();
            }
        });
    }


    private boolean validateFieldsData() {
        return ValidateFormField.getInstance().validateUsernameField(register.getUsername().getValue()) &&
                ValidateFormField.getInstance().validatePasswordField(register.getPassword().getValue());
    }

    private void startCreateNewUser() {
        if(appUserService.checkExistByUsername(register.getUsername().getValue())){
            registerErrorNotification.open();
        } else {
            createNewUser();
        }
    }

    private void createNewUser() {
        if(appUserService.createNewUser(register.getUsername().getValue(),register.getPassword().getValue())){
            registerOkNotification.open();
            goToLogin();
            clearRegisterFields();
        } else {
            registerCreateUserError.open();
        }
    }

    private void goToRegister() {
        register.setVisible(true);
        login.setVisible(false);
    }

    private void goToLogin(){
        register.setVisible(false);
        login.setVisible(true);
    }

    private void clearRegisterFields() {
        register.getUsername().setValue("");
        register.getPassword().setValue("");
    }

    private void prepareLoginForm() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        loginI18n.getForm().setForgotPassword("Register new user");
        login.setI18n(loginI18n);
        login.setAction("login");
        register.setVisible(false);
    }

}

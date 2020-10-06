package com.kodilla.frontend.ui.view;

import com.kodilla.frontend.domian.AppUserDto;
import com.kodilla.frontend.domian.AppUserDtoMap;
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
import com.vaadin.flow.server.VaadinSession;

@Route("login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private static final String ERROR_NOTIFICATION = "User exist with this username";
    private static final String OK_NOTIFICATION = "New User has been added";
    private static final String CREATE_USER_ERROR = "Something go wrong";

    private LoginForm login = new LoginForm();
    private RegisterView registerView = new RegisterView();
    private LoginI18n loginI18n = LoginI18n.createDefault();
    private Notification registerErrorNotification = new Notification(ERROR_NOTIFICATION,3000);
    private Notification registerOkNotification = new Notification(OK_NOTIFICATION,3000);
    private Notification registerCreateUserError = new Notification(CREATE_USER_ERROR,3000);
    private AppUserService appUserService;
    private ValidateFormField validateFormField;

    public LoginView(AppUserService appUserService, ValidateFormField validateFormField) {
        this.validateFormField = validateFormField;
        this.appUserService = appUserService;
        prepareLoginForm();
        add(new H1("App nutritional plan"),login , registerView);
        clickRegisterNewUserButton();
        clickRegisterButton();
        clickRegisterBackButton();
        clickLoginButton();
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

    private void clickLoginButton() {
        login.addLoginListener(event -> {
            if(appUserService.checkExistByUsername(event.getUsername())) {
                AppUserDto appUserDto = appUserService.loadUserByUsername(event.getUsername());
                AppUserDtoMap.getInstance().addToMap(VaadinSession.getCurrent(), appUserDto);
            }
        });
    }

    private void clickRegisterBackButton() {
        registerView.getBack().addClickListener(event -> {
            clearRegisterFields();
            goToLogin();
        });
    }

    private void clickRegisterNewUserButton() {
        login.addForgotPasswordListener(event -> goToRegister());
    }

    private void clickRegisterButton() {
        registerView.getButton().addClickListener(event -> {
            if(validateFieldsData()){
                startCreateNewUser();
            } else {
                registerCreateUserError.open();
            }
        });
    }


    private boolean validateFieldsData() {
        return validateFormField.validateUsernameField(registerView.getUsername().getValue()) &&
                validateFormField.validatePasswordField(registerView.getPassword().getValue());
    }

    private void startCreateNewUser() {
        if(appUserService.checkExistByUsername(registerView.getUsername().getValue())){
            registerErrorNotification.open();
        } else {
            createNewUser();
        }
    }

    private void createNewUser() {
        if(appUserService.createNewUser(registerView.getUsername().getValue(), registerView.getPassword().getValue())){
            registerOkNotification.open();
            goToLogin();
            clearRegisterFields();
        } else {
            registerCreateUserError.open();
        }
    }

    private void goToRegister() {
        registerView.setVisible(true);
        login.setVisible(false);
    }

    private void goToLogin() {
        registerView.setVisible(false);
        login.setVisible(true);
    }

    private void clearRegisterFields() {
        registerView.getUsername().clear();
        registerView.getPassword().clear();
    }

    private void prepareLoginForm() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        loginI18n.getForm().setForgotPassword("Register new user");
        login.setI18n(loginI18n);
        login.setAction("login");
        registerView.setVisible(false);
    }

}

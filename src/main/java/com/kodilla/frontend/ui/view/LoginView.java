package com.kodilla.frontend.ui.view;

import com.kodilla.frontend.domian.AppUser;
import com.kodilla.frontend.security.SecurityUtils;
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

    private final static String ERROR_NOTIFICATION = "User exist with this username";
    private final static String OK_NOTIFICATION = "New User has been added";
    private final static String CREATE_USER_ERROR = "Something go wrong";

    private LoginForm login = new LoginForm();
    private RegisterView registerView = new RegisterView();
    private LoginI18n loginI18n = LoginI18n.createDefault();
    private Notification registerErrorNotification = new Notification(ERROR_NOTIFICATION,3000);
    private Notification registerOkNotification = new Notification(OK_NOTIFICATION,3000);
    private Notification registerCreateUserError = new Notification(CREATE_USER_ERROR,3000);
    private AppUserService appUserService;

    public LoginView(AppUserService appUserService){
        System.out.println(VaadinSession.getCurrent());
        this.appUserService = appUserService;
        prepareLoginForm();
        add(new H1("App nutritional plan"),login , registerView);
        clickRegisterNewUserButton();
        clickRegisterButton();
        clickRegisterBackButton();
        login.addLoginListener(event -> {
            AppUser.getInstance().setUsername(event.getUsername());
        });
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
        return ValidateFormField.getInstance().validateUsernameField(registerView.getUsername().getValue()) &&
                ValidateFormField.getInstance().validatePasswordField(registerView.getPassword().getValue());
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

    private void goToLogin(){
        registerView.setVisible(false);
        login.setVisible(true);
    }

    private void clearRegisterFields() {
        registerView.getUsername().setValue("");
        registerView.getPassword().setValue("");
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

package com.kodilla.frontend.ui.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route("login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private LoginForm login = new LoginForm();
    private Register register = new Register();
    private LoginI18n loginI18n = LoginI18n.createDefault();

    public LoginView(){
        prepareLoginForm();
        add(new H1("App nutritional plan"),login , register);
        login.addForgotPasswordListener(event -> {
            register.setVisible(true);
            login.setVisible(false);
        });
        register.getButton().addClickListener(event -> {
            register.setVisible(false);
            login.setVisible(true);
        });
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        // inform the user about an authentication error
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }
    private void prepareLoginForm(){
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        login.setI18n(loginI18n);
        loginI18n.getForm().setForgotPassword("Register");
        login.setAction("login");
        register.setVisible(false);
    }
}

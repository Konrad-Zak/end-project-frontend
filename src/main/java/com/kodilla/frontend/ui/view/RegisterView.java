package com.kodilla.frontend.ui.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import lombok.Getter;

@Getter
@CssImport("./style.css")
@Route("register")
public class RegisterView extends FormLayout {

    private TextField username = new TextField("Login");
    private PasswordField password = new PasswordField("Password");
    private Button button = new Button("Register");
    private Button back = new Button("Back");

    public RegisterView() {
        addClassName("register-form");
        back.setClassName("back-button");
        setResponsiveSteps(
                new ResponsiveStep("10%", 1),
                new ResponsiveStep("10%", 1));
        add(username, password, button, back);
    }

}

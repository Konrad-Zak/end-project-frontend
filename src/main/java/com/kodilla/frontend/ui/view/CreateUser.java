package com.kodilla.frontend.ui.view;

import com.kodilla.frontend.domian.AppUserDto;
import com.kodilla.frontend.domian.AppUserDtoMap;
import com.kodilla.frontend.ui.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;


@Route(value = "createUser", layout = MainView.class)
public class CreateUser extends FormLayout {
    TextField firstName = new TextField("First name");
    EmailField email = new EmailField("Email");
    Button create = new Button("Create");
    Button update = new Button("Update");

    public CreateUser(){
        add(firstName, email, create, update);
    }

}

package com.kodilla.frontend.ui.view;

import com.kodilla.frontend.ui.AppUserDto;
import com.kodilla.frontend.ui.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Route(value = "", layout = MainView.class)
public class CreateUser extends FormLayout {
    TextField username = new TextField("Username");
    PasswordField password = new PasswordField("Password");
    Button create = new Button("Create");

    public CreateUser(){
        add(username,password,create);
        create.addClickListener(event -> {
            AppUserDto appUserDto = b();
            System.out.println(appUserDto);
        });
    }

    public String a(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(getUri(),null,String.class);
    }
    private URI getUri(){
        return UriComponentsBuilder.fromHttpUrl("http://localhost:8081/v1/appUsers")
                .queryParam("login","s")
                .queryParam("password","s").build().encode().toUri();
    }

    public AppUserDto b(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(getUrii(),AppUserDto.class);
    }

    private URI getUrii(){
        return UriComponentsBuilder.fromHttpUrl("http://localhost:8081/v1/appUsers/find")
                .queryParam("username","x").build().encode().toUri();
    }


}

package com.kodilla.frontend.validate;

import com.vaadin.flow.component.notification.Notification;
import org.springframework.stereotype.Component;

@Component
public class ValidateFormField {

    private final static int MIN_USERNAME_LENGTH = 1;
    private final static int MIN_FIRST_NAME_LENGTH = 1;
    private final static int MIN_PASSWORD_LENGTH = 1;
    private final static String USERNAME_ERROR = "Login field is empty";
    private final static String PASSWORD_ERROR = "Password field is empty";
    private final static String FIRST_NAME_ERROR = "FirstName field is empty";
    private final static String EMAIL_ERROR = "Email is incorrect";
    private Notification validateFirstNameError = new Notification(FIRST_NAME_ERROR,3000);
    private Notification validateUsernameError = new Notification(USERNAME_ERROR,3000);
    private Notification validatePasswordError = new Notification(PASSWORD_ERROR, 3000);
    private Notification validateEmailError = new Notification(EMAIL_ERROR, 3000);

    public boolean validateUsernameField(String username){
        if(username.length()<MIN_USERNAME_LENGTH){
            validateUsernameError.open();
        }
        return username.length()>= MIN_USERNAME_LENGTH;
    }

    public boolean validateFirstNameField(String firstName) {
        if(firstName.length()<MIN_FIRST_NAME_LENGTH){
            validateFirstNameError.open();
        }
        return firstName.length()>=MIN_FIRST_NAME_LENGTH;
    }

    public boolean validatePasswordField(String password){
        if(password.length()<MIN_PASSWORD_LENGTH){
            validatePasswordError.open();
        }
        return password.length()>= MIN_PASSWORD_LENGTH;
    }

    public boolean validateEmailField(String email){
        if(!email.contains("@")){
            validateEmailError.open();
        }
        return email.contains("@");
    }

}

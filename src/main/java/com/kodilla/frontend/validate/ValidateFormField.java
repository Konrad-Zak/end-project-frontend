package com.kodilla.frontend.validate;

import com.vaadin.flow.component.notification.Notification;

public class ValidateFormField {

    private final static int MIN_USERNAME_LENGTH = 1;
    private final static int MIN_PASSWORD_LENGTH = 1;
    private final static String USERNAME_ERROR = "Login field is empty";
    private final static String PASSWORD_ERROR = "Password field is empty";
    private static ValidateFormField validateFormField = null;
    private Notification validateUsernameError = new Notification(USERNAME_ERROR,3000);
    private Notification validatePasswordError = new Notification(PASSWORD_ERROR, 3000);

    public ValidateFormField() {
    }

    public static ValidateFormField getInstance(){
        if(validateFormField == null){
            validateFormField =  new ValidateFormField();
        }
        return validateFormField;
    }

    public boolean validateUsernameField(String username){
        if(username.length()<MIN_USERNAME_LENGTH){
            validateUsernameError.open();
        }
        return username.length()>= MIN_USERNAME_LENGTH;
    }

    public boolean validatePasswordField(String password){
        if(password.length()<MIN_PASSWORD_LENGTH){
            validatePasswordError.open();
        }
        return password.length()>= MIN_PASSWORD_LENGTH;
    }

    public boolean validateEmailField(String email){
        return email.contains("@");
    }

}

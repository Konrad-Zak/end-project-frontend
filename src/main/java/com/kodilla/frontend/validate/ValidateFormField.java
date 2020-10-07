package com.kodilla.frontend.validate;

import com.vaadin.flow.component.notification.Notification;
import org.springframework.stereotype.Component;

@Component
public class ValidateFormField {

    private static final int MIN_USERNAME_LENGTH = 1;
    private static final int MIN_FIRST_NAME_LENGTH = 1;
    private static final int MIN_PASSWORD_LENGTH = 1;
    private static final int MAX_NUMBER_OF_CHARACTERS = 200;
    private static final int MIN_NUMBER_OF_CHARACTERS = 1;

    private static final String USERNAME_ERROR = "Login field is empty";
    private static final String PASSWORD_ERROR = "Password field is empty";
    private static final String TEXT_ARENA_ERROR = "Too long message... Max characters: 200";
    private static final String VALUE_MAX_ERROR = "Value is greater than max";
    private static final String VALUE_MIN_ERROR = "Value is less than min";
    private static final String SEARCH_FIELD_ERROR = "Search food field is empty";

    private Notification validateUsernameError = new Notification(USERNAME_ERROR,2000);
    private Notification validatePasswordError = new Notification(PASSWORD_ERROR, 2000);
    private Notification validateTextArenaError = new Notification(TEXT_ARENA_ERROR,2000);
    private Notification validateMaxValueError = new Notification(VALUE_MAX_ERROR,2000);
    private Notification validateMinValueError = new Notification(VALUE_MIN_ERROR, 2000);
    private Notification validateSearchFood = new Notification(SEARCH_FIELD_ERROR, 2000);

    public boolean validateUsernameField(String username) {
        if(username.length()<MIN_USERNAME_LENGTH){
            validateUsernameError.open();
        }
        return username.length()>= MIN_USERNAME_LENGTH && !username.equals(" ");
    }

    public boolean validateFirstNameField(String firstName) {
        return firstName.length()>=MIN_FIRST_NAME_LENGTH && !firstName.equals(" ");
    }

    public boolean validatePasswordField(String password) {
        if(password.length()<MIN_PASSWORD_LENGTH){
            validatePasswordError.open();
        }
        return password.length()>= MIN_PASSWORD_LENGTH && !password.equals(" ");
    }

    public boolean validateEmailField(String email) {
        return email.contains("@");
    }

    public boolean validateTextArenaField(String message) {
        if(message.length()>MAX_NUMBER_OF_CHARACTERS){
            validateTextArenaError.open();
        }
        return message.length()<=MAX_NUMBER_OF_CHARACTERS && message.length()>MIN_NUMBER_OF_CHARACTERS;
    }

    public boolean validateNumberField(Double max, Double min, Double currentValue ) {
        if(currentValue<min){
            validateMinValueError.open();
        }
        if (currentValue>max){
            validateMaxValueError.open();
        }
        return currentValue<=max && currentValue>= min;
    }

    public boolean validateSearchFoodField(String text) {
        if(text.length()<MIN_NUMBER_OF_CHARACTERS){
            validateSearchFood.open();
        }
        return text.length()>=MIN_NUMBER_OF_CHARACTERS && !text.equals(" ");
    }

}

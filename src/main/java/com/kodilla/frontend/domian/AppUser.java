package com.kodilla.frontend.domian;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUser {
    private static AppUser appUser;
    private String username;

    public static AppUser getInstance() {
        if (appUser == null) {
            appUser = new AppUser();
        }
        return appUser;
    }
}

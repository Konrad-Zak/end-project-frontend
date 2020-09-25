package com.kodilla.frontend.domian;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AppUserInfo {

    private Long id;
    private String firstName;
    private String email;
    private AppUser appUser;

    public AppUserInfo(String firstName, String email, AppUser appUser) {
        this.firstName = firstName;
        this.email = email;
        this.appUser = appUser;
    }
}

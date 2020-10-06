package com.kodilla.frontend.domian;

import lombok.Getter;

@Getter
public class AppUser {

    private Long id;
    private String username;
    private String password;

    public AppUser(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

}

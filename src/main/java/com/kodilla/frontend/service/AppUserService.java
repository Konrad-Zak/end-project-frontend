package com.kodilla.frontend.service;

import com.kodilla.frontend.client.AppUserClient;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private AppUserClient appUserClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserClient.getAppUser(username);
    }

    public Boolean checkExistByUsername(String username){
        return appUserClient.checkUserInDB(username);
    }

    public Boolean createNewUser(String username, String password){
        return appUserClient.createNewUser(username, password);
    }
}

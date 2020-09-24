package com.kodilla.frontend.service;

import com.kodilla.frontend.client.AppUserClient;
import com.kodilla.frontend.domian.AppUser;
import com.kodilla.frontend.domian.AppUserDto;
import com.kodilla.frontend.domian.AppUserDtoMap;
import com.vaadin.flow.server.VaadinSession;
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
    public AppUserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserClient.getAppUser(username);
    }

    public Boolean checkExistByUsername(String username){
        return appUserClient.checkUserInDB(username);
    }

    public Boolean createNewUser(String username, String password){
        return appUserClient.createNewUser(username, password);
    }

    public void changeUserPassword(AppUser appUser){
        appUserClient.changeUserPassword(appUser);
    }
}

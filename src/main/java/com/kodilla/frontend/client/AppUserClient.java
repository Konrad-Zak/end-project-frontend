package com.kodilla.frontend.client;

import com.kodilla.frontend.client.config.BackendConfig;
import com.kodilla.frontend.domian.AppUser;
import com.kodilla.frontend.domian.AppUserDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@Component
public class AppUserClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppUserClient.class);
    private RestTemplate restTemplate;
    private BackendConfig backendConfig;

    public AppUserDto getAppUser(String username){
        try {
            URI uri = UriComponentsBuilder.fromHttpUrl(backendConfig.getBackendAppUsersApiEndpoint())
                    .queryParam("username", username)
                    .build().encode().toUri();
            return restTemplate.getForObject(uri, AppUserDto.class);
        } catch (HttpClientErrorException ex){
            LOGGER.error("User not found - " + ex.getMessage());
        }
        return new AppUserDto();
    }

    public Boolean checkUserInDB(String username){
        URI uri = UriComponentsBuilder.fromHttpUrl(backendConfig.getBackendAppUsersApiEndpoint() + "/check")
                .queryParam("username", username)
                .build().encode().toUri();
        return restTemplate.getForObject(uri, Boolean.class);
    }

    public Boolean createNewUser(String username, String password){
        URI uri = UriComponentsBuilder.fromHttpUrl(backendConfig.getBackendAppUsersApiEndpoint())
                .queryParam("username", username)
                .queryParam("password", password)
                .build().encode().toUri();

        return restTemplate.postForObject(uri,null, Boolean.class);
    }

    public void changeUserPassword(AppUser appUser){
        restTemplate.put(backendConfig.getBackendAppUsersApiEndpoint(), appUser);
    }


}

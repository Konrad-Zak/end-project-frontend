package com.kodilla.frontend.service;

import com.kodilla.frontend.domian.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@Component
public class BackendClient {
    private RestTemplate restTemplate;


    public UserDetails getAppUser(String name){
        return restTemplate.getForObject(getUrl(name), AppUser.class);
    }

    public URI getUrl(String name){
        return UriComponentsBuilder.fromHttpUrl("http://localhost:8081/v1/appUsers/"+name)
                .build().encode().toUri();
    }
}

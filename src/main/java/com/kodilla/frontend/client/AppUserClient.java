package com.kodilla.frontend.client;

import com.kodilla.frontend.client.config.BackendConfig;
import com.kodilla.frontend.domian.AppUserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@Component
public class AppUserClient {

    private RestTemplate restTemplate;
    private BackendConfig backendConfig;

    public AppUserDto getAppUser(String username){
        URI uri = UriComponentsBuilder.fromHttpUrl(backendConfig.getBackendApiEndpoint() + "/appUsers")
                .queryParam("username", username)
                .build().encode().toUri();

        return restTemplate.getForObject(uri, AppUserDto.class);
    }

}

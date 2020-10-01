package com.kodilla.frontend.client;

import com.kodilla.frontend.client.config.BackendConfig;
import com.kodilla.frontend.domian.AppUserMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@AllArgsConstructor
public class AppUserMessageClient {

    private RestTemplate restTemplate;
    private BackendConfig backendConfig;


    public Boolean createNewMessage(AppUserMessage appUserMessage){
        URI uri = UriComponentsBuilder.fromHttpUrl(backendConfig.getBackendAppUserMessageEndpoint())
                .build().encode().toUri();
        return restTemplate.postForObject(uri, appUserMessage, Boolean.class);
    }
}

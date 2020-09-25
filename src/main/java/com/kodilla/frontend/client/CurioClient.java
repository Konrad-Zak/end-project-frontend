package com.kodilla.frontend.client;

import com.kodilla.frontend.client.config.BackendConfig;
import com.kodilla.frontend.domian.CurioDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@Component
public class CurioClient {

    private RestTemplate restTemplate;
    private BackendConfig backendConfig;

    public CurioDto getCurio(){
        URI uri = UriComponentsBuilder.fromHttpUrl(backendConfig.getBackendCurioApiEndpoint())
                .build().encode().toUri();

        return restTemplate.getForObject(uri, CurioDto.class);
    }
}

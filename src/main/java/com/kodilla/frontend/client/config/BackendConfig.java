package com.kodilla.frontend.client.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class BackendConfig {

    @Value("${backend.api.endpoint.prod}")
    private String backendApiEndpoint;
}

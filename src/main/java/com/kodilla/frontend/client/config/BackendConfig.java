package com.kodilla.frontend.client.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class BackendConfig {

    @Value("${backend.api.endpoint.appUsers}")
    private String backendAppUsersApiEndpoint;

    @Value("${backend.api.endpoint.curio}")
    private String backendCurioApiEndpoint;

    @Value("${backend.api.endpoint.appUserInfo}")
    private String backendAppUserInfoApiEndpoint;
}

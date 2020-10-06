package com.kodilla.frontend.client;

import com.kodilla.frontend.client.config.BackendConfig;
import com.kodilla.frontend.domian.AppUserInfo;
import com.kodilla.frontend.domian.AppUserInfoDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@Component
public class AppUserInfoClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppUserInfoClient.class);
    private RestTemplate restTemplate;
    private BackendConfig backendConfig;

    public Boolean createAppUserInfo(AppUserInfo appUserInfo) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backendConfig.getBackendAppUserInfoApiEndpoint())
                .build().encode().toUri();
        return restTemplate.postForObject(uri, appUserInfo, Boolean.class);
    }

    public AppUserInfoDto getAppUserInfoByAppUserId(Long appUserId) {
        try {
            URI uri = UriComponentsBuilder.fromHttpUrl(backendConfig.getBackendAppUserInfoApiEndpoint())
                    .queryParam("appUserId", appUserId)
                    .build().encode().toUri();
            return restTemplate.getForObject(uri, AppUserInfoDto.class);
        } catch (RuntimeException ex){
            LOGGER.info("User not found " + ex.getMessage());
            return new AppUserInfoDto();
        }
    }

    public void updateAppUserInfo(AppUserInfo appUserInfo) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backendConfig.getBackendAppUserInfoApiEndpoint())
                .build().encode().toUri();
        restTemplate.put(uri,appUserInfo);
    }
}

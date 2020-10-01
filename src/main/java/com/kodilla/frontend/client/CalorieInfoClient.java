package com.kodilla.frontend.client;

import com.kodilla.frontend.client.config.BackendConfig;
import com.kodilla.frontend.domian.CalorieInfoDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@Component
public class CalorieInfoClient {

    private RestTemplate restTemplate;
    private BackendConfig backendConfig;

    public Boolean createNewCalorieInfo(Long appUserId, Double weight, Double fitness) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backendConfig.getBackendCalorieInfoEndpoint())
                .queryParam("appUserId", appUserId)
                .queryParam("weight", weight)
                .queryParam("fitness", fitness)
                .build().encode().toUri();

        return restTemplate.postForObject(uri,null, Boolean.class);
    }

    public CalorieInfoDto getCalorieInfoByAppUserId(Long appUserId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backendConfig.getBackendCalorieInfoEndpoint())
                .queryParam("appUserId", appUserId)
                .build().encode().toUri();
        return restTemplate.getForObject(uri, CalorieInfoDto.class);
    }

    public Boolean checkCalorieInfoExistByAppUserId(Long appUserId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(
                backendConfig.getBackendCalorieInfoEndpoint() + "/check")
                .queryParam("appUserId", appUserId)
                .build().encode().toUri();
        return restTemplate.getForObject(uri, Boolean.class);
    }

    public void uploadCalorieInfo(Long appUserId, Double weight, Double fitness) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backendConfig.getBackendCalorieInfoEndpoint())
                .queryParam("appUserId", appUserId)
                .queryParam("weight", weight)
                .queryParam("fitness", fitness)
                .build().encode().toUri();

        restTemplate.put(uri, null);
    }

}

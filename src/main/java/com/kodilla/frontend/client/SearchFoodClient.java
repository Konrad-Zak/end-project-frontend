package com.kodilla.frontend.client;

import com.kodilla.frontend.client.config.BackendConfig;
import com.kodilla.frontend.domian.SearchFoodDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@AllArgsConstructor
public class SearchFoodClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchFoodClient.class);
    private RestTemplate restTemplate;
    private BackendConfig backendConfig;

    public SearchFoodDto getSearchFood(String foodName) {
        try {
            URI uri = UriComponentsBuilder.fromHttpUrl(backendConfig.getBackendEdamamEndpoint())
                    .queryParam("foodName", foodName)
                    .build().encode().toUri();
            return restTemplate.getForObject(uri, SearchFoodDto.class);
        } catch (ResponseStatusException ex) {
            LOGGER.error("Search food: " + ex);
            return new SearchFoodDto();
        }
    }
}

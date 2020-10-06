package com.kodilla.frontend.service;

import com.kodilla.frontend.client.SearchFoodClient;
import com.kodilla.frontend.domian.SearchFoodDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchFoodService {

    private SearchFoodClient searchFoodClient;

    public SearchFoodDto getSearchFoodDto(String foodName) {
        return searchFoodClient.getSearchFood(foodName);
    }
}

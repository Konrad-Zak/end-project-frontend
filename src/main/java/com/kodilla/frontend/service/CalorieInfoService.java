package com.kodilla.frontend.service;

import com.kodilla.frontend.client.CalorieInfoClient;
import com.kodilla.frontend.domian.CalorieInfoDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CalorieInfoService {

    private CalorieInfoClient calorieInfoClient;

    public Boolean createCalorieInfo(Long appUserId, Double weight, Double fitness) {
        return calorieInfoClient.createNewCalorieInfo(appUserId,weight,fitness);
    }

    public CalorieInfoDto getCalorieInfoDtoByAppUserId(Long appUserId) {
        return calorieInfoClient.getCalorieInfoByAppUserId(appUserId);
    }

    public Boolean checkExistByAppUserId(Long appUserId) {
        return calorieInfoClient.checkCalorieInfoExistByAppUserId(appUserId);
    }

    public void uploadCalorieInfo(Long appUserId, Double weight, Double fitness) {
        calorieInfoClient.uploadCalorieInfo(appUserId,weight,fitness);
    }
}

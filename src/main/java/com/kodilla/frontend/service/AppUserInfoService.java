package com.kodilla.frontend.service;

import com.kodilla.frontend.client.AppUserInfoClient;
import com.kodilla.frontend.domian.AppUserInfo;
import com.kodilla.frontend.domian.AppUserInfoDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserInfoService {

    private AppUserInfoClient appUserInfoClient;

    public void createAppUserInfo(AppUserInfo appUserInfo){
        appUserInfoClient.createAppUserInfo(appUserInfo);
    }

    public AppUserInfoDto getAppUserInfoByAppUserId(Long appUserId){
        return appUserInfoClient.getAppUserInfoByAppUserId(appUserId);
    }
}

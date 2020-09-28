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

    public Boolean createAppUserInfo(AppUserInfo appUserInfo){
        return appUserInfoClient.createAppUserInfo(appUserInfo);
    }

    public AppUserInfoDto getAppUserInfoByAppUserId(Long appUserId) {
        return appUserInfoClient.getAppUserInfoByAppUserId(appUserId);
    }

    public void updateAppUserInfoDto(AppUserInfo appUserInfo) {
        appUserInfoClient.updateAppUserInfo(appUserInfo);
    }
}

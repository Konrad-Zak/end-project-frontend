package com.kodilla.frontend.domian;

import com.vaadin.flow.server.VaadinSession;

import java.util.HashMap;
import java.util.Map;

public class AppUserInfoDtoMap {

    private static AppUserInfoDtoMap appUserInfoDtoMap = null;
    private Map<VaadinSession, AppUserInfoDto> mapAppUserInfoDto = new HashMap<>();

    public static AppUserInfoDtoMap getInstance(){
        if (appUserInfoDtoMap == null) {
            appUserInfoDtoMap= new AppUserInfoDtoMap();
        }
        return appUserInfoDtoMap;
    }

    public void addToMap(VaadinSession vaadinSession, AppUserInfoDto appUserInfoDto){
        mapAppUserInfoDto.put(vaadinSession,appUserInfoDto);
    }

    public void deleteCurrentSession(VaadinSession vaadinSession){
        mapAppUserInfoDto.remove(vaadinSession);
    }

    public AppUserInfoDto getAppUserByKey(VaadinSession vaadinSession){
        return mapAppUserInfoDto.get(vaadinSession);
    }

    public Map<VaadinSession, AppUserInfoDto> getMapAppUserInfoDto() {
        return mapAppUserInfoDto;
    }
}

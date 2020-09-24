package com.kodilla.frontend.domian;

import com.vaadin.flow.server.VaadinSession;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class AppUserDtoMap {

    private static AppUserDtoMap appUserDtoMap = null;
    private Map<VaadinSession, AppUserDto> appUserMap = new HashMap<>();

    public static AppUserDtoMap getInstance() {
        if (appUserDtoMap == null) {
            appUserDtoMap = new AppUserDtoMap();
        }
        return appUserDtoMap;
    }

    public void addToMap( VaadinSession vaadinSession, AppUserDto appUserDto){
        appUserMap.put(vaadinSession,appUserDto);
    }

    public void deleteCurrentSession(VaadinSession vaadinSession){
        appUserMap.remove(vaadinSession);
    }

    public AppUserDto getAppUserDtoByKey(VaadinSession vaadinSession){
        return appUserMap.get(vaadinSession);
    }


}

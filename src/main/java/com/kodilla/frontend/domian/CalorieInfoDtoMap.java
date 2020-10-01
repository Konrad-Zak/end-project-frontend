package com.kodilla.frontend.domian;

import com.vaadin.flow.server.VaadinSession;

import java.util.HashMap;
import java.util.Map;

public class CalorieInfoDtoMap {

    private static CalorieInfoDtoMap calorieInfoDtoMap = null;
    private Map<VaadinSession, CalorieInfoDto> calorieInfoDtoMaps = new HashMap<>();

    public static CalorieInfoDtoMap getInstance() {
        if (calorieInfoDtoMap == null) {
            calorieInfoDtoMap = new CalorieInfoDtoMap();
        }
        return calorieInfoDtoMap;
    }

    public void addToMap(VaadinSession vaadinSession, CalorieInfoDto calorieInfoDto) {
        calorieInfoDtoMaps.put(vaadinSession,calorieInfoDto);
    }

    public void deleteCurrentSession(VaadinSession vaadinSession) {
        calorieInfoDtoMaps.remove(vaadinSession);
    }

    public CalorieInfoDto getAppUserByKey(VaadinSession vaadinSession) {
        return calorieInfoDtoMaps.get(vaadinSession);
    }

    public Map<VaadinSession, CalorieInfoDto> getCalorieInfoDtoMap() {
        return calorieInfoDtoMaps;
    }
}

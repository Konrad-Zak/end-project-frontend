package com.kodilla.frontend.domian;

import com.vaadin.flow.server.VaadinSession;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
@Getter
public class CurioMap {

    private static CurioMap curioMap = null;
    private Map<VaadinSession, CurioDto> map = new HashMap<>();

    public static CurioMap getInstance() {
        if (curioMap == null) {
            curioMap = new CurioMap();
        }
        return curioMap;
    }

    public void addToMap(VaadinSession vaadinSession, CurioDto curioDto){
        map.put(vaadinSession,curioDto);
    }

    public void deleteCurrentSession(VaadinSession vaadinSession){
        map.remove(vaadinSession);
    }

    public CurioDto getCurioDtoByKey(VaadinSession vaadinSession){
        return map.get(vaadinSession);
    }

}

package com.kodilla.frontend.client.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Curio {

    private static Curio curio = null;
    private String text;
    private Integer year;

    public static Curio getInstance() {
        if (curio == null) {
            curio = new Curio();
        }
        return curio;
    }


}

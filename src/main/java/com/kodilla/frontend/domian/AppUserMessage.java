package com.kodilla.frontend.domian;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class AppUserMessage {
    private String email;
    private String message;
    private LocalDate localDate;
}

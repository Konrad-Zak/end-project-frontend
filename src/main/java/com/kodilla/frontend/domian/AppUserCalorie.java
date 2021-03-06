package com.kodilla.frontend.domian;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppUserCalorie {

    private Long id;
    private Double protein;
    private Double fat;
    private Double carbohydrates;
    private Double calories;

}

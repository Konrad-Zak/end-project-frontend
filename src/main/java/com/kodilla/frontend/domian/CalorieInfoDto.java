package com.kodilla.frontend.domian;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CalorieInfoDto {

    private Long id;
    private Double appUserWeight;
    private Double appUserFitness;
    private AppUserCalorie appUserCalorie;
}

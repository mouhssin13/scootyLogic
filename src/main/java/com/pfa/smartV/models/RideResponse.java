package com.pfa.smartV.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RideResponse {
    private Long id;
    private LocalTime dateDep;
    private LocalTime dateArr;
    private float price;
    private Long trottinetteID;
    private Long userID;
    private int duree;
}

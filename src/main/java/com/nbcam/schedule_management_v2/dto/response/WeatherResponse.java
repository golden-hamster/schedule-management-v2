package com.nbcam.schedule_management_v2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WeatherResponse {
    private String date;
    private String weather;
}

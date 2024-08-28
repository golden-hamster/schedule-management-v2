package com.nbcam.schedule_management_v2.service;

import com.nbcam.schedule_management_v2.dto.response.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class WeatherService {

    private final RestTemplate restTemplate;

    public List<WeatherResponse> getWeatherData() {
        String url = "https://f-api.github.io/f-api/weather.json";
        WeatherResponse[] weatherResponses = restTemplate.getForObject(url, WeatherResponse[].class);
        return Arrays.asList(Objects.requireNonNull(weatherResponses));
    }
}

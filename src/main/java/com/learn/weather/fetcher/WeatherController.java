package com.learn.weather.fetcher;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    @PostMapping("/api/weather")
    public ResponseEntity<?> getWeatherViaAjax(
            @RequestBody String loc, Errors errors) {
        String result = "{\n" +
                "  \"request\": {\n" +
                "    \"type\": \"City\",\n" +
                "    \"query\": \"Chicago, United States of America\",\n" +
                "    \"language\": \"en\",\n" +
                "    \"unit\": \"m\"\n" +
                "  },\n" +
                "  \"location\": {\n" +
                "    \"name\": \"Chicago\",\n" +
                "    \"country\": \"United States of America\",\n" +
                "    \"region\": \"Illinois\",\n" +
                "    \"lat\": \"41.850\",\n" +
                "    \"lon\": \"-87.650\",\n" +
                "    \"timezone_id\": \"America/Chicago\",\n" +
                "    \"localtime\": \"2021-04-07 23:40\",\n" +
                "    \"localtime_epoch\": 1617838800,\n" +
                "    \"utc_offset\": \"-5.0\"\n" +
                "  },\n" +
                "  \"current\": {\n" +
                "    \"observation_time\": \"04:40 AM\",\n" +
                "    \"temperature\": 22,\n" +
                "    \"weather_code\": 116,\n" +
                "    \"weather_icons\": [\n" +
                "      \"https://assets.weatherstack.com/images/wsymbols01_png_64/wsymbol_0004_black_low_cloud.png\"\n" +
                "    ],\n" +
                "    \"weather_descriptions\": [\n" +
                "      \"Partly cloudy\"\n" +
                "    ],\n" +
                "    \"wind_speed\": 24,\n" +
                "    \"wind_degree\": 160,\n" +
                "    \"wind_dir\": \"SSE\",\n" +
                "    \"pressure\": 1005,\n" +
                "    \"precip\": 1.4,\n" +
                "    \"humidity\": 53,\n" +
                "    \"cloudcover\": 25,\n" +
                "    \"feelslike\": 22,\n" +
                "    \"uv_index\": 1,\n" +
                "    \"visibility\": 16,\n" +
                "    \"is_day\": \"no\"\n" +
                "  }\n" +
                "}";
        return ResponseEntity.ok(result);

    }
}

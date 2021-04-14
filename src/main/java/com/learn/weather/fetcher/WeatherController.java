package com.learn.weather.fetcher;

import com.learn.weather.fetcher.exception.WeatherException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@RestController
public class WeatherController {
    private static final Logger logger = LogManager.getLogger(WeatherController.class);
    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .followRedirects(HttpClient.Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    @Value("${ACCESS_KEY}")
    private String access_key;

    @GetMapping("/api/weather")
    public ResponseEntity<?> getWeatherViaAjax(@RequestParam String loc) throws Exception {
        logger.info("Inside WeatherController, loc :" + loc);
        String apiResponse = getWeatherReport(loc);
        logger.info("The api response is :" + apiResponse);
        if (apiResponse.contains("request_failed")) {
            throw new WeatherException("Invalid location !!!");
        }
        return ResponseEntity.ok(apiResponse);
    }

    private String getWeatherReport(String location) throws WeatherException {
        if (access_key == null || access_key.trim().isEmpty()) {
            throw new WeatherException("Configuration error occurred !!");
        }
        String responseBody = "";

        try {
            String url = "http://api.weatherstack.com/current?" +
                    "access_key=" + access_key +
                    "&query=" + URLEncoder.encode(location, StandardCharsets.UTF_8);
            logger.info("Inside encoded loc :" +
                    URLEncoder.encode(location, StandardCharsets.UTF_8));
            final HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();
            final HttpResponse<String> response = HTTP_CLIENT
                    .send(request, HttpResponse.BodyHandlers.ofString());
            responseBody = response.body();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new WeatherException("Connection timed out !!");
        }
        return responseBody;
    }
}

package com.learn.weather.fetcher.exception;

public class WeatherException extends Exception {
    private String message;

    public WeatherException(String message) {
        super(message);
    }
}

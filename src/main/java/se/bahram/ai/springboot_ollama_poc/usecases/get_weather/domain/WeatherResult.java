package se.bahram.ai.springboot_ollama_poc.usecases.get_weather.domain;

public record WeatherResult(
        String city,
        double temperatureC,
        String condition
) {
}

package se.bahram.ai.springboot_ollama_poc.usecases.get_weather.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;
import se.bahram.ai.springboot_ollama_poc.usecases.get_weather.domain.WeatherRequest;
import se.bahram.ai.springboot_ollama_poc.usecases.get_weather.domain.WeatherResult;

@Component
public class WeatherTools {

    /**
     * Demo tool: normally you’d call a real weather API here.
     * For the POC we just return deterministic mock data.
     */
    @Tool(
            name = "getWeather",
            description = "Get current weather for a city. ",
            returnDirect = true
    )
    public WeatherResult getWeather(WeatherRequest request) {
        String city = request.city();
        // Mock: pretend only a few cities; otherwise default
        return switch (city.toLowerCase()) {
            case "stockholm" -> new WeatherResult("Stockholm", 18.0, "partly cloudy");
            case "berlin"    -> new WeatherResult("Berlin",    22.5, "sunny");
            default          -> new WeatherResult(city,        20.0, "clear");
        };
    }
}

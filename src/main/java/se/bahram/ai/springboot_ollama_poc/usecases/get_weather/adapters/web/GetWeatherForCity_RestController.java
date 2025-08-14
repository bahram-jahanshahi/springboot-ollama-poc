package se.bahram.ai.springboot_ollama_poc.usecases.get_weather.adapters.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.bahram.ai.springboot_ollama_poc.usecases.get_weather.applications.ports.in.GetWeatherForCity_AiAgentUseCase;
import se.bahram.ai.springboot_ollama_poc.usecases.get_weather.domain.WeatherRequest;

@RestController
@CrossOrigin(origins = "*")
public class GetWeatherForCity_RestController {

    private final GetWeatherForCity_AiAgentUseCase getWeatherForCityAiAgentUseCase;

    public GetWeatherForCity_RestController(GetWeatherForCity_AiAgentUseCase getWeatherForCityAiAgentUseCase) {
        this.getWeatherForCityAiAgentUseCase = getWeatherForCityAiAgentUseCase;
    }

    @GetMapping("/api/weather")
    public String getWeatherForCity(String city) {
        var weatherRequest = new WeatherRequest(city);
        var weatherResult = getWeatherForCityAiAgentUseCase.getWeatherForCity(weatherRequest);
        return String.format("The current weather in %s is %.2f°C with %s.",
                weatherResult.city(),
                weatherResult.temperatureC(),
                weatherResult.condition());
    }
}

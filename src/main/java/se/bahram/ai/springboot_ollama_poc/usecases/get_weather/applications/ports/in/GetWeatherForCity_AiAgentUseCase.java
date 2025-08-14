package se.bahram.ai.springboot_ollama_poc.usecases.get_weather.applications.ports.in;

import se.bahram.ai.springboot_ollama_poc.usecases.get_weather.domain.WeatherRequest;
import se.bahram.ai.springboot_ollama_poc.usecases.get_weather.domain.WeatherResult;

public interface GetWeatherForCity_AiAgentUseCase {

    WeatherResult getWeatherForCity(WeatherRequest request);
}

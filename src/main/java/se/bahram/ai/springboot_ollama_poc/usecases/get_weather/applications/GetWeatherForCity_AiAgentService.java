package se.bahram.ai.springboot_ollama_poc.usecases.get_weather.applications;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;
import se.bahram.ai.springboot_ollama_poc.usecases.get_weather.applications.ports.in.GetWeatherForCity_AiAgentUseCase;
import se.bahram.ai.springboot_ollama_poc.usecases.get_weather.domain.WeatherRequest;
import se.bahram.ai.springboot_ollama_poc.usecases.get_weather.domain.WeatherResult;
import se.bahram.ai.springboot_ollama_poc.usecases.get_weather.tools.WeatherTools;

@Service
public class GetWeatherForCity_AiAgentService implements GetWeatherForCity_AiAgentUseCase {

    private final OllamaChatModel ollamaChatModel;
    private final WeatherTools weatherTools;

    public GetWeatherForCity_AiAgentService(OllamaChatModel ollamaChatModel, WeatherTools weatherTools) {
        this.ollamaChatModel = ollamaChatModel;
        this.weatherTools = weatherTools;
    }

    @Override
    public WeatherResult getWeatherForCity(WeatherRequest request) {

        String query = "What is the current weather in " + request.city() + "?";

        var client = ChatClient.create(ollamaChatModel);
        var response = client.prompt(query)
                .tools(weatherTools)
                .call()
                .entity(WeatherResult.class);

        return response;
    }
}

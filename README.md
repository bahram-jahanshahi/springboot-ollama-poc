# 🌤️ Spring Boot + Ollama POC — AI Tool Calling for Weather

A minimal **Proof of Concept** demonstrating **Spring AI 1.0** with **Ollama** to perform **tool/function calling**  
using the `llama3.1:8b` model to retrieve (mock) weather data in **structured form**.

---

## 🚀 Overview

This project answers a simple question:

> Can we use Spring AI + Ollama to let an LLM call a Java function (tool) and return a **structured entity**?

Spoiler: ✅ Yes, we can.

The flow:

1. User asks for the weather in a given city.
2. **Spring AI** sends the query to **Ollama** (`llama3.1:8b`).
3. The model decides to call the **`getWeather(city)`** Java tool.
4. The tool returns a **`WeatherResult`** object (mocked here, but could be a real API).
5. The structured result is returned to the client.

---

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot 3.4.x**
- **Spring AI 1.0.x**
- **Ollama** (local LLM runtime)
- **llama3.1:8b** model
- **Tool Calling API** via `@Tool(returnDirect = true)`

---

## 📦 Project Structure

```
se.bahram.ai.springboot_ollama_poc
 ├── usecases.get_weather.domain
 │    ├── WeatherRequest.java
 │    └── WeatherResult.java
 │
 ├── usecases.get_weather.tools
 │    └── WeatherTools.java        # @Tool-annotated function
 │
 ├── usecases.get_weather.applications
 │    └── GetWeatherForCity_AiAgentService.java
 │
 ├── usecases.get_weather.adapters.web
 │    └── GetWeatherForCity_RestController.java
 │
 └── resources/application.yml     # Ollama + model configuration
```

---

## ⚡ Getting Started

### 1️⃣ Install Ollama

<details>
<summary>Mac</summary>

```bash
brew install ollama
```
</details>

<details>
<summary>Linux</summary>

```bash
curl -fsSL https://ollama.com/install.sh | sh
```
</details>

Start the Ollama server:

```bash
ollama serve
```

---

### 2️⃣ Pull the Model

```bash
ollama pull llama3.1:8b
```

You can verify with:

```bash
ollama list
```

---

### 3️⃣ Clone & Build

```bash
git clone https://github.com/your-username/springboot-ollama-poc.git
cd springboot-ollama-poc
./mvnw clean install
```

---

### 4️⃣ Run the App

```bash
./mvnw spring-boot:run
```

Spring Boot will start on [http://localhost:8080](http://localhost:8080).

---

## 🌐 API Usage

### Endpoint

```
GET /api/weather?city=<cityName>
```

### Example

```bash
curl "http://localhost:8080/api/weather?city=Stockholm"
```

**Response:**
```
The current weather in Stockholm is 18.00°C with partly cloudy.
```

---

## 🔍 How It Works

1. **User Query:**  
   The controller receives `GET /api/weather?city=Stockholm`.

2. **LLM Prompting:**  
   The service builds a prompt and sends it to the **OllamaChatModel**.

3. **Tool Calling:**  
   The model recognizes it can answer by calling the **`getWeather`** tool (annotated with `@Tool(returnDirect = true)`).

4. **Structured Output:**  
   The tool returns a **WeatherResult** Java record, which Spring converts to JSON or formats in the controller.

---

## 🧪 Example Model Interaction (direct call)

```java
var client = ChatClient.create(ollamaChatModel);
var response = client.prompt("What's the weather in Berlin?")
                     .tools(weatherTools)
                     .call()
                     .entity(WeatherResult.class);
```

**Result:**
```json
{
  "city": "Berlin",
  "temperatureC": 22.5,
  "condition": "sunny"
}
```

---

## 🔮 Next Steps

- 🔗 **Integrate a real weather API** (e.g., OpenWeatherMap) in `WeatherTools`.
- 📄 **Structured Output Converters** for more complex schemas.
- 📊 Evaluate **performance** and **accuracy** across different models (`qwen`, `mistral`, etc.).
- 🚀 Deploy to Docker or Kubernetes for local AI microservice experiments.

---

## 📜 License

MIT License — feel free to fork, modify, and experiment.

---

**Author:** Bahram — _Exploring AI function calling in the JVM world._

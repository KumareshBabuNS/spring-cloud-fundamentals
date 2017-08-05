package io.zhy2002.weatherservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class WeatherServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherServiceApplication.class, args);
	}

	private String[] weathers = {"sunny", "cloudy", "rainly", "windy"};

	@GetMapping("/weather")
	public String getWeather() {
		return weathers[ThreadLocalRandom.current().nextInt(weathers.length)];
	}
}

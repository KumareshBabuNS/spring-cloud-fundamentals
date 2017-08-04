package io.zhy2002.ribbontimeapp;

import io.zhy2002.config.RibbonTimeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RibbonClient(name = "time-service", configuration = RibbonTimeConfig.class)
//@EnableDiscoveryClient
@SpringBootApplication
public class RibbonTimeAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonTimeAppApplication.class, args);
    }

    @GetMapping
    public String getTime() {
        return restTemplate.getForEntity("http://time-service", String.class).getBody();
    }

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}

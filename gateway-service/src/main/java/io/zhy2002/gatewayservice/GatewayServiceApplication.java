package io.zhy2002.gatewayservice;

import io.zhy2002.filters.AddRequestHeaderFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
@Configuration
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    @Bean
    public AddRequestHeaderFilter getAddRequestHeaderFilter() {
        return new AddRequestHeaderFilter();
    }
}

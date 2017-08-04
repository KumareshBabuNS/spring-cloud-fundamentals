package io.zhy2002.firstclient;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RefreshScope
public class ClientController {

    @Autowired
    private EurekaClient client;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private FirstClientConfiguration configuration;

    @Value("${some.other.property}")
    private String someOtherProperty;

    @RequestMapping("/")
    public String[] callClientService() {
        InstanceInfo instanceInfo = client.getNextServerFromEureka("first-service", false);
        String baseUrl = instanceInfo.getHomePageUrl();
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class);
        return new String[]{
                response.getBody(),
                someOtherProperty,
                configuration.getProperty()
        };
    }
}

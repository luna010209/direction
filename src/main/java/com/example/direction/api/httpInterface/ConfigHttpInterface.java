package com.example.direction.api.httpInterface;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ConfigHttpInterface {
    @Bean
    public OdsayInterface odsayInterface() {
        WebClient webClient = WebClient.builder().baseUrl("https://api.odsay.com/v1/api/").build();
        WebClientAdapter adapter = WebClientAdapter.create(webClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(OdsayInterface.class);
    }
}

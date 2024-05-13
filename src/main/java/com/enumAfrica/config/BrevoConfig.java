package com.enumAfrica.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Getter
@Configuration
public class BrevoConfig {
    @Value("${mail_api}")
    private String brevoMailApi;
    @Value("${mail_service_url}")
    private String brevoMailServiceUrl;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

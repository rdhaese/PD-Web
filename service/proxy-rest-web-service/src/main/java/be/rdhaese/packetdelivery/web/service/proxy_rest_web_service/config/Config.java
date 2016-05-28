package be.rdhaese.packetdelivery.web.service.proxy_rest_web_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Robin D'Haese
 */
@Configuration
class Config {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

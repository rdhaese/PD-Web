package be.rdhaese.packetdelivery.web.service.proxy_rest_web_service.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created on 18/05/2016.
 *
 * @author Robin D'Haese
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan ("be.rdhaese.packetdelivery.web.service.proxy_rest_web_service")
public class TestConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

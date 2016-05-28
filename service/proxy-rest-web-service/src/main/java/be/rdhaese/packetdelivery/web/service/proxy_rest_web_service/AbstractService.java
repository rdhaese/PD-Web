package be.rdhaese.packetdelivery.web.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.web.service.proxy_rest_web_service.properties.BackEndProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Robin D'Haese
 */
abstract class AbstractService {

    @Autowired
    private BackEndProperties backEndProperties;

    @Autowired
    private RestTemplate restTemplate;

    protected RestTemplate getRestTemplate() {
        return restTemplate;
    }

    protected BackEndProperties getBackEndProperties() {
        return backEndProperties;
    }
}

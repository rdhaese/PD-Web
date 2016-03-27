package be.rdhaese.packetdelivery.web.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.web.service.proxy_rest_web_service.util.UriUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * Created on 31/12/2015.
 *
 * @author Robin D'Haese
 */
public class AbstractService {

    @Autowired
    private UriUtil uris;

    public UriUtil getUris() {
        return uris;
    }

    public void setUris(UriUtil uris) {
        this.uris = uris;
    }

    public RestTemplate getNewRestTemplate(){
        return new RestTemplate();
    }
}

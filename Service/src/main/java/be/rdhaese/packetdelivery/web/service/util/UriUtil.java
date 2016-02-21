package be.rdhaese.packetdelivery.web.service.util;

import be.rdhaese.packetdelivery.web.service.properties.BackEndProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

/**
 * Created on 31/12/2015.
 *
 * @author Robin D'Haese
 */
@Component
public class UriUtil {

    @Autowired
    private BackEndProperties backEndProperties;

    public String getServerPath(){
        return format("http://%s:%s", backEndProperties.getIp(), backEndProperties.getPort());
    }

    private BackEndProperties.Uris getUris(){
        return backEndProperties.getUris();
    }

    public String getWithServerPath(String path){
        return format("%s/%s", getServerPath(), path);
    }

    public String getContactInformationPath(){
        return getWithServerPath(getUris().getContactInformation());
    }

}

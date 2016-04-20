package be.rdhaese.packetdelivery.web.service.proxy_rest_web_service.util;

import be.rdhaese.packetdelivery.web.service.proxy_rest_web_service.properties.BackEndProperties;
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

    public String getLongLatForAddressPath() { return getWithServerPath(getUris().getLongLatForAddress()); }

    public String getCompanyNamePath() { return getWithServerPath(getUris().getCompanyName()); }

    public String getCompanyAddressPath() { return getWithServerPath(getUris().getCompanyAddress()); }

    public String getPacketAddressPath() { return getWithServerPath(getUris().getPacketAddress()); }

    public String getLocationUpdatesPath() { return getWithServerPath(getUris().getLocationUpdates()); }

    public String getRemarksPath() { return getWithServerPath(getUris().getRemarks()); }
}

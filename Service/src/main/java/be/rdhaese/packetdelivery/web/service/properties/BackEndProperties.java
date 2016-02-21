package be.rdhaese.packetdelivery.web.service.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created on 31/12/2015.
 *
 * @author Robin D'Haese
 */
@Component
@ConfigurationProperties(locations = "classpath:back-end.properties")
public class BackEndProperties {
    private String ip;
    private String port;
    private Uris uris;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Uris getUris() {
        return uris;
    }

    public void setUris(Uris uris) {
        this.uris = uris;
    }

    public static class Uris{
        private String contactInformation;

        public String getContactInformation() {
            return contactInformation;
        }

        public void setContactInformation(String contactInformation) {
            this.contactInformation = contactInformation;
        }
    }
}

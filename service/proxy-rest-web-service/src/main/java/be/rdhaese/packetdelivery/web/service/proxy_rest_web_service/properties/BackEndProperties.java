package be.rdhaese.packetdelivery.web.service.proxy_rest_web_service.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created on 31/12/2015.
 *
 * @author Robin D'Haese
 */
@Component
@ConfigurationProperties(prefix = "back_end")
public class BackEndProperties {
    private String ip;
    private String port;
    private String serverPath;
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

    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }

    public Uris getUris() {
        return uris;
    }

    public void setUris(Uris uris) {
        this.uris = uris;
    }

    public static class Uris {
        private String contactInformation;
        private String longLatForAddress;
        private String companyName;
        private String companyAddress;
        private String packetAddress;
        private String locationUpdates;
        private String remarks;
        private String packetsLeftBefore;

        public String getContactInformation() {
            return contactInformation;
        }

        public void setContactInformation(String contactInformation) {
            this.contactInformation = contactInformation;
        }

        public String getLongLatForAddress() {
            return longLatForAddress;
        }

        public void setLongLatForAddress(String longLatForAddress) {
            this.longLatForAddress = longLatForAddress;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCompanyAddress() {
            return companyAddress;
        }

        public void setCompanyAddress(String companyAddress) {
            this.companyAddress = companyAddress;
        }

        public String getPacketAddress() {
            return packetAddress;
        }

        public void setPacketAddress(String packetAddress) {
            this.packetAddress = packetAddress;
        }

        public String getLocationUpdates() {
            return locationUpdates;
        }

        public void setLocationUpdates(String locationUpdates) {
            this.locationUpdates = locationUpdates;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getPacketsLeftBefore() {
            return packetsLeftBefore;
        }

        public void setPacketsLeftBefore(String packetsLeftBefore) {
            this.packetsLeftBefore = packetsLeftBefore;
        }
    }
}

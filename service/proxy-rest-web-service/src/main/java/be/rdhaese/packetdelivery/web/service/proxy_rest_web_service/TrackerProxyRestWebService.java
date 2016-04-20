package be.rdhaese.packetdelivery.web.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.back_end.application.web_service.interfaces.TrackerWebService;
import be.rdhaese.packetdelivery.dto.LocationUpdateDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import be.rdhaese.packetdelivery.dto.RemarkDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created on 19/04/2016.
 *
 * @author Robin D'Haese
 */
@Service
public class TrackerProxyRestWebService extends AbstractService implements TrackerWebService {

    @Override
    public LongLatDTO getCompanyAddress() {
        return getRestTemplate().getForObject(getUris().getCompanyAddressPath(), LongLatDTO.class);
    }

    @Override
    public LongLatDTO getPacketAddress(String packetId) {
        return getRestTemplate().getForObject(getUris().getPacketAddressPath(), LongLatDTO.class, packetId);
    }

    @Override
    public Collection<LocationUpdateDTO> getLocationUpdates(String packetId) {
        return Arrays.asList(
                getRestTemplate()
                        .getForObject(getUris().getLocationUpdatesPath(), LocationUpdateDTO[].class, packetId));

    }

    @Override
    public Collection<RemarkDTO> getRemarks(String packetId) {
        return Arrays.asList(
                getRestTemplate()
                        .getForObject(getUris().getRemarksPath(), RemarkDTO[].class, packetId));
    }
}

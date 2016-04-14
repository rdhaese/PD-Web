package be.rdhaese.packetdelivery.web.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.back_end.application.web_service.interfaces.LongLatWebService;
import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import org.springframework.stereotype.Service;

/**
 * Created on 21/02/2016.
 *
 * @author Robin D'Haese
 */
@Service
public class LongLatProxyRestWebService extends AbstractService implements LongLatWebService {

    @Override
    public LongLatDTO getForAddress(AddressDTO addressDTO) {
        return getRestTemplate().postForObject(getUris().getLongLatForAddressPath(), addressDTO, LongLatDTO.class);
    }
}

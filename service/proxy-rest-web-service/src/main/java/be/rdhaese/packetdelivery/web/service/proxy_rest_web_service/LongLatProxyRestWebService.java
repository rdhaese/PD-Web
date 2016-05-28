package be.rdhaese.packetdelivery.web.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.LongLatWebService;
import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import org.springframework.stereotype.Service;

/**
 *
 * @author Robin D'Haese
 */
@Service
class LongLatProxyRestWebService extends AbstractService implements LongLatWebService {

    @Override
    public LongLatDTO getForAddress(AddressDTO addressDTO) throws Exception{
        return getRestTemplate().postForObject(getBackEndProperties().getUris().getLongLatForAddress(), addressDTO, LongLatDTO.class);
    }
}

package be.rdhaese.packetdelivery.web.service.impl;

import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import be.rdhaese.packetdelivery.web.service.AbstractService;
import be.rdhaese.packetdelivery.web.service.LongLatService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created on 21/02/2016.
 *
 * @author Robin D'Haese
 */
@Service
public class LongLatServiceImpl extends AbstractService implements LongLatService {

    @Override
    public LongLatDTO getForAddress(AddressDTO addressDTO) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(getUris().getLongLatForAddressPath(), addressDTO, LongLatDTO.class);
    }
}

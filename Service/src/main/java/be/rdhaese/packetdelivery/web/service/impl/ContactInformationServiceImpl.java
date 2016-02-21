package be.rdhaese.packetdelivery.web.service.impl;

import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;
import be.rdhaese.packetdelivery.web.service.AbstractService;
import be.rdhaese.packetdelivery.web.service.ContactInformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created on 14/02/2016.
 *
 * @author Robin D'Haese
 */
@Service
public class ContactInformationServiceImpl extends AbstractService implements ContactInformationService {

    @Override
    public ContactDetailsDTO get() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ContactDetailsDTO> response = restTemplate.getForEntity(getUris().getContactInformationPath(), ContactDetailsDTO.class);
        return response.getBody();
    }
}

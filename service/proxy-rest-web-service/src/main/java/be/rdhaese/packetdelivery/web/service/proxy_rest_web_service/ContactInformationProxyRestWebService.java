package be.rdhaese.packetdelivery.web.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.ContactInformationWebService;
import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;
import org.springframework.stereotype.Service;

/**
 * Created on 14/02/2016.
 *
 * @author Robin D'Haese
 */
@Service
public class ContactInformationProxyRestWebService extends AbstractService implements ContactInformationWebService {

    @Override
    public ContactDetailsDTO get() {
        return getRestTemplate().getForEntity(getUris().getContactInformationPath(), ContactDetailsDTO.class).getBody();
    }

    @Override
    public boolean post(ContactDetailsDTO contactDetailsDTO) {
        throw new UnsupportedOperationException("Cannot change contact information from web application");
    }

    @Override
    public String getCompanyName() {
       return getRestTemplate().getForEntity(getUris().getCompanyNamePath(), String.class).getBody();
    }
}

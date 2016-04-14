package be.rdhaese.packetdelivery.web.front_end.thymeleaf_implementation;

import be.rdhaese.packetdelivery.back_end.application.web_service.interfaces.ContactInformationWebService;
import be.rdhaese.packetdelivery.back_end.application.web_service.interfaces.LongLatWebService;
import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import be.rdhaese.packetdelivery.web.front_end.interfaces.ContactController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created on 14/02/2016.
 *
 * @author Robin D'Haese
 */
@Controller
public class ContactControllerImpl implements ContactController {

    @Autowired
    private ContactInformationWebService contactInformationService;
    @Autowired
    private LongLatWebService longLatService;

    @Override
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String getContact(Model model) {
        ContactDetailsDTO contactDetailsDTO= contactInformationService.get();
        model.addAttribute("contactInformation", contactDetailsDTO);
        AddressDTO addressDTO = mapToAddressDTO(contactDetailsDTO);
        LongLatDTO longLat = longLatService.getForAddress(addressDTO);
        model.addAttribute("longLat", longLat);
        return "contact";
    }

    private AddressDTO mapToAddressDTO(ContactDetailsDTO contactDetailsDTO) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet(contactDetailsDTO.getStreet());
        addressDTO.setNumber(contactDetailsDTO.getNumber());
        addressDTO.setMailbox(contactDetailsDTO.getMailbox());
        addressDTO.setCity(contactDetailsDTO.getCity());
        addressDTO.setPostalCode(contactDetailsDTO.getPostalCode());
        return addressDTO;
    }
}

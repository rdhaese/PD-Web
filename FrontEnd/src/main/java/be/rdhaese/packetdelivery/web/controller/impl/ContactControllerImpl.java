package be.rdhaese.packetdelivery.web.controller.impl;

import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import be.rdhaese.packetdelivery.web.controller.ContactController;
import be.rdhaese.packetdelivery.web.service.LongLatService;
import be.rdhaese.packetdelivery.web.service.ContactInformationService;
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
    private ContactInformationService contactInformationService;
    @Autowired
    private LongLatService longLatService;

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

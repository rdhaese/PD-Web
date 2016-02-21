package be.rdhaese.packetdelivery.web.controller.impl;

import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;
import be.rdhaese.packetdelivery.web.controller.ContactController;
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

    @Override
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String getContact(Model model) {
        ContactDetailsDTO contactDetailsDTO= contactInformationService.get();
        model.addAttribute("contactInformation", contactDetailsDTO);
        return "contact";
    }
}

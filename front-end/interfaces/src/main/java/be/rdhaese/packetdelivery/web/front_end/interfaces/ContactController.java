package be.rdhaese.packetdelivery.web.front_end.interfaces;

import org.springframework.ui.Model;

/**
 * Created on 14/02/2016.
 *
 * @author Robin D'Haese
 */
public interface ContactController {

    String getContact(Model model) throws Exception;
}

package be.rdhaese.packetdelivery.web.front_end.interfaces;

import org.springframework.ui.Model;

/**
 *
 * @author Robin D'Haese
 */
public interface ContactController {

    @SuppressWarnings("SameReturnValue")
    String getContact(Model model) throws Exception;
}

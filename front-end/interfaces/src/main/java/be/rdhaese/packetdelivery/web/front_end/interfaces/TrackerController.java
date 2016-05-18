package be.rdhaese.packetdelivery.web.front_end.interfaces;

import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

/**
 * Created on 14/02/2016.
 *
 * @author Robin D'Haese
 */
public interface TrackerController {
    @SuppressWarnings("SameReturnValue")
    String track(String packetId, HttpSession session, Model model);
    @SuppressWarnings("SameReturnValue")
    String clearPacket(HttpSession session);
}

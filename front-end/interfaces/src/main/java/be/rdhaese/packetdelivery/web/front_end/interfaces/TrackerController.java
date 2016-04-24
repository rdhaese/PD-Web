package be.rdhaese.packetdelivery.web.front_end.interfaces;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created on 14/02/2016.
 *
 * @author Robin D'Haese
 */
public interface TrackerController {
    String track(String packetId, HttpSession session, Model model);
    String clearPacket(HttpSession session);
}

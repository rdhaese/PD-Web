package be.rdhaese.packetdelivery.web.front_end.interfaces;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 14/02/2016.
 *
 * @author Robin D'Haese
 */
public interface TrackerController {
    String track(String packetId, HttpServletRequest httpServletRequest);
    String clearPacket(HttpServletRequest httpServletRequest);
}

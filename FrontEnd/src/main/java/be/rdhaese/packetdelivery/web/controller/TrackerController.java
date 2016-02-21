package be.rdhaese.packetdelivery.web.controller;

import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 14/02/2016.
 *
 * @author Robin D'Haese
 */
public interface TrackerController {
    String getTracker();
    String track(String packetId, HttpServletRequest httpServletRequest);
    String clearPacket(HttpServletRequest httpServletRequest);
}

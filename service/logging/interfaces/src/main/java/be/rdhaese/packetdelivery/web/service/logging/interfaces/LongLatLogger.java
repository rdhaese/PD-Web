package be.rdhaese.packetdelivery.web.service.logging.interfaces;

import be.rdhaese.packetdelivery.dto.LongLatDTO;
import org.aspectj.lang.JoinPoint;

/**
 * Created on 24/04/2016.
 *
 * @author Robin D'Haese
 */
public interface LongLatLogger {

    void afterGetForAddress(JoinPoint joinpoint, LongLatDTO longLatDTO);
}

package be.rdhaese.packetdelivery.web.service.logging.interfaces;

import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.LocationUpdateDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import be.rdhaese.packetdelivery.dto.RemarkDTO;
import org.aspectj.lang.JoinPoint;

import java.util.Collection;

/**
 * Created on 24/04/2016.
 *
 * @author Robin D'Haese
 */
public interface TrackerLogger {

    void afterGetCompanyAddress(JoinPoint joinpoint, LongLatDTO longLatDTO);
    void afterGetPacketAddress(JoinPoint joinpoint, LongLatDTO longLatDTO);
    void afterGetLocationUpdates(JoinPoint joinpoint, Collection<LocationUpdateDTO> locationUpdateDTOs);
    void afterGetRemarks(JoinPoint joinpoint, Collection<RemarkDTO> remarkDTOs);
}

package be.rdhaese.packetdelivery.web.service.logging.default_implementation;

import be.rdhaese.packetdelivery.dto.LocationUpdateDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import be.rdhaese.packetdelivery.dto.RemarkDTO;
import be.rdhaese.packetdelivery.web.service.logging.interfaces.TrackerLogger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Collection;

/**
 * Created on 24/04/2016.
 *
 * @author Robin D'Haese
 */
public class TrackerLoggerImpl extends AbstractLogger implements TrackerLogger {

    @Autowired
    @Qualifier("trackerLogger")
    private Logger logger;

    @Override
    @AfterReturning(pointcut = "execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.TrackerWebService.getCompanyAddress(..)", returning = "longLatDTO")
    public void afterGetCompanyAddress(JoinPoint joinpoint, LongLatDTO longLatDTO) {
        String logText = String.format(
                "Company address [latitude: [%s]; longitude: [%s]] requested",
                longLatDTO.getLatitude(),
                longLatDTO.getLongitude()
        );
        info(logText);
    }

    @Override
    @AfterReturning(pointcut = "execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.TrackerWebService.getPacketAddress(..)", returning = "longLatDTO")
    public void afterGetPacketAddress(JoinPoint joinpoint, LongLatDTO longLatDTO) {
        String packetId = getArg(joinpoint, 0);
        String logText = String.format(
                "Packet address [latitude: [%s]; longitude: [%s]] requested for packet [%s]",
                longLatDTO.getLatitude(),
                longLatDTO.getLongitude(),
                packetId
        );
        info(logText);
    }

    @Override
    @AfterReturning(pointcut = "execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.TrackerWebService.getLocationUpdates(..)", returning = "locationUpdateDTOs")
    public void afterGetLocationUpdates(JoinPoint joinpoint, Collection<LocationUpdateDTO> locationUpdateDTOs) {
        String packetId = getArg(joinpoint, 0);
        String logText = String.format(
                "Location updates [%s] requested for packet [%s]",
                packetId,
                locationUpdateDTOs.size());
        info(logText);

        debug("Location Updates Content:");
        for (LocationUpdateDTO locationUpdateDTO : locationUpdateDTOs){
            logText = String.format(
                    "Location Update[time created: [%s]; latitude: [%s]; longitude: [%s]]",
                    locationUpdateDTO.getTimeCreated(),
                    locationUpdateDTO.getLatitude(),
                    locationUpdateDTO.getLongitude()
            );
            debug(logText);
        }
    }

    @Override
    @AfterReturning(pointcut = "execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.TrackerWebService.getRemarks(..)", returning = "remarkDTOs")
    public void afterGetRemarks(JoinPoint joinpoint, Collection<RemarkDTO> remarkDTOs) {
        String packetId = getArg(joinpoint, 0);
        String logText = String.format(
                "Remarks [%s] requested for packet [%s]",
                packetId,
                remarkDTOs.size());
        info(logText);

        debug("Remarks Content:");
        for (RemarkDTO remarkDTO : remarkDTOs){
            logText = String.format(
                    "Remark[time added: [%s]; description: [%s]",
                    remarkDTO.getTimeAdded(),
                    remarkDTO.getDescription()
            );
            debug(logText);
        }
    }

    @Override
    public Logger getLogger() {
        return logger;
    }
}

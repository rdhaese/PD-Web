package be.rdhaese.packetdelivery.web.service.logging.default_implementation;

import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import be.rdhaese.packetdelivery.web.service.logging.interfaces.LongLatLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created on 24/04/2016.
 *
 * @author Robin D'Haese
 */
public class LongLatLoggerImpl extends AbstractLogger implements LongLatLogger {

    @Autowired
    @Qualifier("longLatLogger")
    private Logger logger;

    @Override
    @AfterReturning(pointcut = "execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.LongLatWebService.getForAddress(..)", returning = "longLatDTO")
    public void afterGetForAddress(JoinPoint joinpoint, LongLatDTO longLatDTO) {
        AddressDTO addressDTO = getArg(joinpoint, 0);
        String logText = String.format(
                "Latitude [%s] and longitude [%s] requested for address [street: [%s]; number: [%s]; mailbox: [%s]; postalcode: [%s]; city: [%s]]",
                longLatDTO.getLatitude(),
                longLatDTO.getLongitude(),
                addressDTO.getStreet(),
                addressDTO.getNumber(),
                addressDTO.getMailbox(),
                addressDTO.getPostalCode(),
                addressDTO.getCity()
        );
        info(logText);
    }

    @Override
    public Logger getLogger() {
        return logger;
    }
}

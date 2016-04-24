package be.rdhaese.packetdelivery.web.service.logging.default_implementation;

import be.rdhaese.packetdelivery.web.service.logging.interfaces.ContactInformationLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created on 24/04/2016.
 *
 * @author Robin D'Haese
 */
public class ContactInformationLoggerImpl extends AbstractLogger implements ContactInformationLogger {

    @Autowired
    @Qualifier("contactInformationLogger")
    private Logger logger;

    @Override
    @After("execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.ContactInformationWebService.get(..)")
    public void afterGet(JoinPoint joinpoint) {
        info("Company contact details requested");
    }

    @Override
    @Before("execution(* be.rdhaese.packetdelivery.back_end.web_service.interfaces.ContactInformationWebService.post(..)")
    public void beforePost(JoinPoint joinpoint) {
        warn("Attempting to save contact information. Operation not supported from web application");
    }

    @Override
    public void afterGetCompanyName(JoinPoint joinpoint, String companyName) {
        String logText = String.format(
                "Company name [%s] requested",
                companyName
        );
        info(logText);
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

}

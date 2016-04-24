package be.rdhaese.packetdelivery.web.service.logging.interfaces;


import org.aspectj.lang.JoinPoint;

/**
 * Created on 24/04/2016.
 *
 * @author Robin D'Haese
 */
public interface ContactInformationLogger {

    void afterGet(JoinPoint joinpoint);
    void beforePost(JoinPoint joinpoint);
    void afterGetCompanyName(JoinPoint joinpoint, String companyName);
}

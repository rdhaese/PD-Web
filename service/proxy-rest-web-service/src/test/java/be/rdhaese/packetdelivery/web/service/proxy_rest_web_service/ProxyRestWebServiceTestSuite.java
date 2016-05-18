package be.rdhaese.packetdelivery.web.service.proxy_rest_web_service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created on 18/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ContactInformationProxyRestWebServiceTest.class,
        LongLatProxyRestWebServiceTest.class,
        TrackerProxyRestWebServiceTest.class
})
public class ProxyRestWebServiceTestSuite {
}

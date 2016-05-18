package be.rdhaese.packetdelivery.web.front_end.thymeleaf_implementation;

import be.rdhaese.packetdelivery.web.front_end.thymeleaf_implementation.util.UtilTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created on 18/05/2016.
 *
 * @author Robin D'Haese
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ContactControllerImplTest.class,
        TrackerControllerImplTest.class,
        UtilTestSuite.class
})
public class ThymeLeafImplementationTestSuite {
}

package be.rdhaese.packetdelivery.web.front_end.thymeleaf_implementation.util;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Robin D'Haese
 */
public class ManifestReaderTest extends TestCase{

    private ManifestReader manifestReader;

    @Before
    public void setUp(){
        manifestReader = new ManifestReader();
    }

    @Test
    public void testGetImplementationTitle(){
        String expected =this.getClass().getPackage().getImplementationTitle();
        assertEquals(expected, manifestReader.getImplementationTitle());
    }

    @Test
    public void testGetImplementationVersion(){
        String expected = this.getClass().getPackage().getSpecificationVersion();
        assertEquals(expected, manifestReader.getSpecificationVersion());
    }
}

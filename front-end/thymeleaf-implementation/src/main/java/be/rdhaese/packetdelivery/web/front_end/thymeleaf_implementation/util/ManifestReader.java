package be.rdhaese.packetdelivery.web.front_end.thymeleaf_implementation.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.Manifest;

/**
 * Created on 28/03/2016.
 *
 * @author Robin D'Haese
 */
@Component
public class ManifestReader {

    public String getImplementationTitle(){
        return this.getClass().getPackage().getImplementationTitle();
    }

    public String getSpecificationVersion(){
        return this.getClass().getPackage().getSpecificationVersion();
    }
}

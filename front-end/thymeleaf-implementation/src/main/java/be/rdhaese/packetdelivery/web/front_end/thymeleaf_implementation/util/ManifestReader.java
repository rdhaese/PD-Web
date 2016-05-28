package be.rdhaese.packetdelivery.web.front_end.thymeleaf_implementation.util;

import org.springframework.stereotype.Component;

/**
 *
 * @author Robin D'Haese
 */
@Component
public class ManifestReader {

    public String getImplementationTitle(){
        return ManifestReader.class.getPackage().getImplementationTitle();
    }

    public String getSpecificationVersion(){
        return ManifestReader.class.getPackage().getSpecificationVersion();
    }
}

package be.rdhaese.packetdelivery.web.front_end.thymeleaf_implementation.config;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.ContactInformationWebService;
import be.rdhaese.packetdelivery.back_end.web_service.interfaces.LongLatWebService;
import be.rdhaese.packetdelivery.back_end.web_service.interfaces.TrackerWebService;
import be.rdhaese.packetdelivery.web.front_end.thymeleaf_implementation.util.ManifestReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.resource.ResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import static org.mockito.Mockito.mock;

/**
 *
 * @author Robin D'Haese
 */
@Configuration
@ComponentScan(basePackages = "be.rdhaese.packetdelivery.web.front_end.thymeleaf_implementation")
public class TestConfig {

    public static final String PREFIX = "/templates/";
    public static final String SUFFIX = ".tml";

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix(PREFIX);
        resolver.setSuffix(SUFFIX);
        return resolver;
    }

    //Mocks
    @Bean
    public ContactInformationWebService contactInformationWebService(){
        return mock(ContactInformationWebService.class);
    }

    @Bean
    public LongLatWebService longLatWebService(){
        return mock(LongLatWebService.class);
    }

    @Bean
    public TrackerWebService trackerWebService(){
        return mock(TrackerWebService.class);
    }
}

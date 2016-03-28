package be.rdhaese.packetdelivery.web;

import be.rdhaese.packetdelivery.web.front_end.thymeleaf_implementation.util.ManifestReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.util.StringUtils;

import java.net.URL;
import java.util.Enumeration;
import java.util.Locale;
import java.util.jar.Manifest;

/**
 * Hello world!
 */
@SpringBootApplication
public class App extends WebMvcConfigurerAdapter {

    private static final String TEMPLATES_DIR = "/templates";
    private static final String JAR_EXTENSION = ".jar";
    private static final String TITLE_VERSION_SEPARATOR = "-";

    @Autowired
    private ManifestReader manifestReader;

    @Bean
    public ClassLoaderTemplateResolver classLoaderTemplateResolver() {
        ClassLoaderTemplateResolver classLoaderTemplateResolver = new ClassLoaderTemplateResolver();
        classLoaderTemplateResolver.setPrefix(getTemplatePrefix());
        return classLoaderTemplateResolver;
    }

    private String getTemplatePrefix(){
        return String.format("%s%s", getThymeleafImplFullJarName(), TEMPLATES_DIR);
    }

    private String getThymeleafImplFullJarName(){
        return String.format("%s%s%s%s", manifestReader.getImplementationTitle(), TITLE_VERSION_SEPARATOR, manifestReader.getImplementationVersion(), JAR_EXTENSION);
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }


    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(App.class).profiles("production").build().run(args);
    }

}

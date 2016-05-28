package be.rdhaese.packetdelivery.web.application;

import be.rdhaese.packetdelivery.web.front_end.thymeleaf_implementation.util.ManifestReader;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.resourceresolver.ClassLoaderResourceResolver;
import org.thymeleaf.resourceresolver.FileResourceResolver;
import org.thymeleaf.resourceresolver.IResourceResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.dialect.SpringStandardDialect;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import java.io.File;
import java.util.Locale;

/**
 *
 * @author Robin D'Haese
 */
@SpringBootApplication (scanBasePackages = "be.rdhaese.packetdelivery.web")
public class App extends WebMvcConfigurerAdapter {

    private static final String TEMPLATES_DIR = "/templates";
    private static final String JAR_EXTENSION = ".jar";
    private static final String TITLE_VERSION_SEPARATOR = "-";

    @Autowired
    private ManifestReader manifestReader;


    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class)
                .build()
                .run(args);
    }
//
//    @Bean
//    public TemplateResolver templateResolver() {
//        TemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//        templateResolver.setPrefix(getTemplatePrefix());
//        return templateResolver;
//    }

//    @Bean
//    public TemplateResolver templateResolver(){
//        TemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//        templateResolver.setPrefix("classpath:/templates/");
//        return templateResolver;
//    }
//
//    @Bean
//    public SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.setTemplateResolver(templateResolver());
//        return engine;
//    }
//
//    @Bean
//    public ThymeleafViewResolver thymeleafViewResolver() {
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        resolver.setTemplateEngine(templateEngine());
//        return resolver;
//    }
//
//
//    private String getTemplatePrefix(){
//        return String.format("%s%s", getThymeleafImplFullJarName(), TEMPLATES_DIR);
//    }
//
//    private String getThymeleafImplFullJarName(){
//        return String.format("%s%s%s%s", manifestReader.getImplementationTitle(), TITLE_VERSION_SEPARATOR, manifestReader.getSpecificationVersion(), JAR_EXTENSION);
//    }

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
}

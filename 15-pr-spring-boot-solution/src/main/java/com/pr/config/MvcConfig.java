package com.pr.config;

import com.pr.util.DateFormatter;
import com.pr.util.HospitalFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.convert.service.DefaultConversionService;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;


/**
 * Created by iuliana.cosmina on 7/12/15.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private WebFlowConfig webFlowConfig;

    @Bean
    public Validator validator() {
        final LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    @Override
    public Validator getValidator() {
        return validator();
    }

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {
        formatterRegistry.addFormatter(dateFormatter());
        formatterRegistry.addFormatter(hospitalFormatter());
    }

    @Bean
    public DateFormatter dateFormatter() {
        return new DateFormatter();
    }

    @Bean
    public HospitalFormatter hospitalFormatter() {
        return new HospitalFormatter();
    }

    //Declare our static resources. I added cache to the java config but it’s not required.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/*").addResourceLocations("/images/").setCachePeriod(31556926);
        registry.addResourceHandler("/styles/*").addResourceLocations("/static/styles/").setCachePeriod(31556926);
    }

    // <=> <mvc:default-servlet-handler/>
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // Serves up cached and compressed static content at resources from the webapp root and classpath
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/auth").setViewName("auth");
        registry.addViewController("/cancel").setViewName("cancel");
    }

    @Bean
    TilesViewResolver tilesViewResolver() {
        TilesViewResolver tilesViewResolver = new TilesViewResolver();
        tilesViewResolver.setOrder(0);
        tilesViewResolver.setRequestContextAttribute("requestContext");
        return tilesViewResolver;
    }

    @Bean
    TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        String[] definitions = new String[]{
                "classpath:templates/tiles.xml",
                "classpath:templates/persons/tiles.xml",
                "classpath:templates/accounts/tiles.xml",
                "classpath:templates/hospitals/tiles.xml",
                "classpath:templates/persons/newPerson/tiles.xml",
                "classpath:templates/hospitals/newHospital/tiles.xml"};
        tilesConfigurer.setDefinitions(definitions);
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }

    @Bean
    MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:templates/messages/global");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(1);
        return messageSource;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(themeChangeInterceptor());
        registry.addInterceptor(webChangeInterceptor());
    }

    @Bean
    LocaleChangeInterceptor localeChangeInterceptor() {
        return new LocaleChangeInterceptor();
    }

    @Bean
    ThemeChangeInterceptor themeChangeInterceptor() {
        return new ThemeChangeInterceptor();
    }

    @Bean
    WebContentInterceptor webChangeInterceptor() {
        WebContentInterceptor webContentInterceptor = new WebContentInterceptor();
        webContentInterceptor.setCacheSeconds(0);
        webContentInterceptor.setSupportedMethods("GET", "POST", "PUT", "DELETE");
        return webContentInterceptor;
    }

    @Bean
    CookieLocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        cookieLocaleResolver.setCookieMaxAge(3600);
        cookieLocaleResolver.setCookieName("locale");
        return cookieLocaleResolver;
    }

    @Bean
    CookieThemeResolver themeResolver() {
        CookieThemeResolver cookieThemeResolver = new CookieThemeResolver();
        cookieThemeResolver.setDefaultThemeName("green");
        cookieThemeResolver.setCookieMaxAge(3600);
        cookieThemeResolver.setCookieName("theme");
        return cookieThemeResolver;
    }

    // FlowHandlerMapping and FlowHandlerAdapter bean definitions
    @Bean
    public FlowHandlerMapping flowHandlerMapping() {
        FlowHandlerMapping handlerMapping = new FlowHandlerMapping();
        handlerMapping.setOrder(-1);
        handlerMapping.setFlowRegistry(this.webFlowConfig.flowRegistry());
        return handlerMapping;
    }

    @Bean
    public FlowHandlerAdapter flowHandlerAdapter() {
        FlowHandlerAdapter handlerAdapter = new FlowHandlerAdapter();
        handlerAdapter.setFlowExecutor(this.webFlowConfig.flowExecutor());
        handlerAdapter.setSaveOutputToFlashScopeOnRedirect(true);
        return handlerAdapter;
    }

    @Bean
    DefaultConversionService conversionServiceForWF() {
        return new DefaultConversionService(conversionServiceFactoryBeanforWF().getObject());
    }

    @Bean
    FormattingConversionServiceFactoryBean conversionServiceFactoryBeanforWF() {
        FormattingConversionServiceFactoryBean fcs = new FormattingConversionServiceFactoryBean();
        Set<Formatter> fmts = new HashSet<>();
        fmts.add(dateFormatter());
        fmts.add(hospitalFormatter());
        fcs.setFormatters(fmts);
        return fcs;
    }

}

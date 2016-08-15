
package com.pr.config;

import com.pr.audit.AuditFlowExecutorListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.convert.service.DefaultConversionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;
import org.springframework.webflow.security.SecurityFlowExecutionListener;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by iuliana.cosmina on 7/12/15.
 */

@Configuration
public class WebFlowConfig extends AbstractFlowConfiguration {

    @Autowired
    private MvcConfig mvcConfig;

    @Bean
    public SecurityFlowExecutionListener securityFlowExecutionListener(){
        return new SecurityFlowExecutionListener();
    }

    @Bean
    public FlowExecutor flowExecutor() {
        return getFlowExecutorBuilder(flowRegistry())
                .addFlowExecutionListener(new AuditFlowExecutorListener(), "*")
                .addFlowExecutionListener(securityFlowExecutionListener())
                .setMaxFlowExecutions(5)
                .setMaxFlowExecutionSnapshots(30)
                .build();
    }

    @Bean
    public FlowDefinitionRegistry flowRegistry() {
        return getFlowDefinitionRegistryBuilder(flowBuilderServices())
                .setBasePath("/WEB-INF")
                .addFlowLocation("/persons/newPerson/newPerson-flow.xml")
                .addFlowLocation("/hospitals/newHospital/newHospital-flow.xml")
                .build();
    }

    @Bean
    public FlowBuilderServices flowBuilderServices() {
        return getFlowBuilderServicesBuilder()
                .setViewFactoryCreator(mvcViewFactoryCreator())
                .setValidator(this.mvcConfig.validator())
                .setConversionService(conversionService())
                .setDevelopmentMode(true)
                .build();
    }

    @Bean
    public MvcViewFactoryCreator mvcViewFactoryCreator() {
        MvcViewFactoryCreator factoryCreator = new MvcViewFactoryCreator();
        factoryCreator.setViewResolvers(Arrays.<ViewResolver>asList(this.mvcConfig.tilesViewResolver()));
        factoryCreator.setUseSpringBeanBinding(true);
        factoryCreator.setMessageCodesResolver(messageCodesResolver());
        return factoryCreator;
    }

    @Bean
    public DefaultMessageCodesResolver messageCodesResolver(){
        return new DefaultMessageCodesResolver();
    }

    @Bean
    DefaultConversionService conversionService() {
        return new DefaultConversionService(conversionServiceFactoryBean().getObject());
    }


    @Bean
    FormattingConversionServiceFactoryBean conversionServiceFactoryBean() {
        FormattingConversionServiceFactoryBean fcs = new FormattingConversionServiceFactoryBean();
        Set<Formatter> fmts = new HashSet<>();
        fmts.add(this.mvcConfig.dateFormatter());
        fmts.add(this.mvcConfig.hospitalFormatter());
        fcs.setFormatters(fmts);
        return fcs;

    }
}


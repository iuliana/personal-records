package com.pr.config;

import com.pr.audit.AuditFlowExecutorListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.convert.service.DefaultConversionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;

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
    public FlowExecutor flowExecutor() {
        return getFlowExecutorBuilder(flowRegistry())
                .addFlowExecutionListener(new AuditFlowExecutorListener(), "*")
                .build();
    }

    @Bean
    public FlowDefinitionRegistry flowRegistry() {
        return getFlowDefinitionRegistryBuilder(flowBuilderServices())
        //TODO 28. set up the necessary properties for this flow registry so the flow definitions in the application
        // can be identified and registered
                .build();
    }

    @Bean
    public FlowBuilderServices flowBuilderServices() {
        return getFlowBuilderServicesBuilder()
                //.setViewFactoryCreator(mvcViewFactoryCreator())
                .setValidator(this.mvcConfig.validator())
                .setConversionService(conversionService())
                .setDevelopmentMode(true)
                .build();
    }

   //TODO 29. Define a view resolver beand for the Spring Web Flow engine to use

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

package com.pr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * Created by iuliana.cosmina on 10/4/15.
 */
@Configuration
public class TilesConfig {

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
                "/templates/tiles.xml"};
        tilesConfigurer.setDefinitions(definitions);
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }
}

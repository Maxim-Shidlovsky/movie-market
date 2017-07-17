package com.moviemarket.rest;

import com.moviemarket.service.ClientDetailsServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * Created by Maxim on 10.07.2017.
 */

@Configuration
@EnableWebMvc
@ComponentScan("com.moviemarket ")
public class WebAppConfig extends WebMvcConfigurerAdapter {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        LOGGER.debug("addResourceHandlers();");
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
    }

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        LOGGER.debug("setupViewResolver();");
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }

    @Bean
    public UserDetailsService getUserDetailsService(){
        LOGGER.debug("getUserDetailsService();");
        return new ClientDetailsServiceImpl();
    }
}

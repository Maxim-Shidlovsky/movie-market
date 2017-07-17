package com.moviemarket.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
/**
 * Created by Maxim on 10.07.2017.
 */
public class Initializer implements WebApplicationInitializer {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String DISPATCHER_SERVLET_NAME = "restDispatcherServlet";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        LOGGER.debug("onStartup();");

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

        context.register(WebAppConfig.class);
        context.register(SecurityConfig.class);
        servletContext.addListener(new ContextLoaderListener(context));

        context.setServletContext(servletContext);

        Dynamic servlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(context));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
}

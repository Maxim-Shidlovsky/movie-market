package com.moviemarket.rest;

import com.moviemarket.service.ClientDetailsServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Maxim on 10.07.2017.
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private ClientDetailsServiceImpl clientDetailsService;

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        LOGGER.debug("registerGlobalAuthentication()");
        auth.userDetailsService(clientDetailsService).passwordEncoder(getShaPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        LOGGER.debug("configure();");

        // Turn on CSRF attacks protection.
        http.csrf()
                .disable()
                // Choose query rule that will define access to resources and another data.
                .authorizeRequests()
                .antMatchers("/resources/**", "/**").permitAll()
                .anyRequest().permitAll()
                .and();

        http.formLogin()
                // Define a page with login form.
                .loginPage("/login")
                // Define login form action.
                .loginProcessingUrl("/j_spring_security_check")
                // Define URL for wrong logging in.
                .failureUrl("/login?error")
                // Define login and password parameters from the login form.
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                // Give access to the login form for everyone.
                .permitAll();

        http.logout()
                // Permit to logout for everyone.
                .permitAll()
                // Define logout URL.
                .logoutUrl("/logout")
                // Define URL with successful logout.
                .logoutSuccessUrl("/login?logout")
                // Make current session invalid.
                .invalidateHttpSession(true);

    }

    // Request Spring Container to init <b></b>ShaPasswordEncoder.
    // It can be replaced into WebAppConfig class.
    @Bean
    public ShaPasswordEncoder getShaPasswordEncoder(){
        LOGGER.debug("getShaPasswordEncoder();");
        return new ShaPasswordEncoder();
    }
}

package com.moviemarket.service;

import com.moviemarket.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Maxim on 26.7.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-service-spring.xml"})
@Transactional
public class ClientDetailsServiceTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private UserDetailsService userDetailsService;


    private static final String TEST_USERNAME = "user2";
    private static final String TEST_ROLE = "ROLE_CLIENT";

    @Test
    public void loadUserByUsernameTest() throws Exception {
        LOGGER.debug("loadUserByUsernameTest()");
        UserDetails userDetails = userDetailsService.loadUserByUsername(TEST_USERNAME);

        Assert.assertEquals(TEST_USERNAME, userDetails.getUsername());
        Assert.assertEquals(TEST_ROLE, userDetails.getAuthorities().iterator().next().toString());
    }
}

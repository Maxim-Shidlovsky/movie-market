package com.moviemarket.dao;

import com.moviemarket.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Maxim on 30.7.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-mybatis-spring.xml"})
@Transactional
public class ClientMapperTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private ClientMapper clientMapper;


    private static final Client TEST_CLIENT = new Client(2, "user2", "111");

    @Test
    public void getClientTest() throws Exception {
        LOGGER.debug("getClientTest()");
        Client testClient = clientMapper.getClient(TEST_CLIENT.getUsername());
        Assert.assertNotNull(testClient);
        Assert.assertEquals(TEST_CLIENT, testClient);
    }

    @Test
    public void addClientTest() throws Exception {
        LOGGER.debug("addClientTest()");
        Client client = new Client(null, "user333", "111");
        clientMapper.addClient(client);
        Client addedClient = clientMapper.getClient(client.getUsername());
        Assert.assertNotNull(addedClient);
        Assert.assertEquals(client.getUsername(), addedClient.getUsername());
        Assert.assertEquals(client.getPassword(), addedClient.getPassword());
    }
}

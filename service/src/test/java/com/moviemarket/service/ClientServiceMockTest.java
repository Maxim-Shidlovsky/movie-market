package com.moviemarket.service;

import com.moviemarket.dao.ClientMapper;
import com.moviemarket.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Maxim on 30.7.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class ClientServiceMockTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMapper mockClientMapper;


    private static final Client TEST_CLIENT =
            new Client(2, "user2", "111");

    @After
    public void clean() {
        LOGGER.debug("clean()");
        EasyMock.verify(mockClientMapper);
        EasyMock.reset(mockClientMapper);
    }

    @Test
    public void getClientTest() throws Exception {
        LOGGER.debug("getClientTest()");
        EasyMock.expect(mockClientMapper.getClient(TEST_CLIENT.getUsername()))
                .andReturn(TEST_CLIENT);
        EasyMock.replay(mockClientMapper);
        Assert.assertEquals(TEST_CLIENT, clientService.getClient(TEST_CLIENT.getUsername()));
    }

    @Test
    public void addClientTest() throws Exception {
        LOGGER.debug("addClientTest()");
        Client client = new Client(null, TEST_CLIENT.getUsername(), TEST_CLIENT.getPassword());
        EasyMock.expect(mockClientMapper.addClient(client)).andReturn(1);
        EasyMock.expect(mockClientMapper.getClient(client.getUsername()))
                .andReturn(TEST_CLIENT);
        EasyMock.replay(mockClientMapper);
        Integer newId = clientService.addClient(client);
        Client addedClient = clientService.getClient(client.getUsername());
        Assert.assertEquals(TEST_CLIENT.getUsername(), addedClient.getUsername());
        Assert.assertEquals(TEST_CLIENT.getPassword(), addedClient.getPassword());
    }
}

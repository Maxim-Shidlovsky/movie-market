package com.moviemarket.service;

import com.moviemarket.dao.ClientMapper;
import com.moviemarket.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

/**
 * Created by Maxim on 10.07.2017.
 */

public class ClientServiceImpl implements ClientService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private ClientMapper clientMapper;

    public void setClientMapper(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }


    @Override
    public Client getClient(String username) throws DataAccessException{
        LOGGER.debug("getClient(username=\"{}\")", username);
        Assert.notNull(username, "Username mustn't be null");
        Assert.hasText(username, "Username must have text.");

        return clientMapper.getClient(username);
    }

    @Override
    public Integer addClient(Client client) throws DataAccessException {
        LOGGER.debug("addClient({})", client);
        Assert.notNull(client, "Client mustn't be null.");
        Assert.isNull(client.getClientId(), "Client's ID must be null.");
        Assert.notNull(client.getUsername(), "Client's login mustn't be null.");
        Assert.hasText(client.getUsername(), "Client's login must have text.");
        Assert.notNull(client.getPassword(), "Client's password mustn't be null.");
        Assert.hasText(client.getPassword(), "Client's password must have text.");

        return clientMapper.addClient(client);
    }
}

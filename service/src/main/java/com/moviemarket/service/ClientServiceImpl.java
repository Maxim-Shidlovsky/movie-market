package com.moviemarket.service;

import com.moviemarket.model.Client;
import org.springframework.stereotype.Service;

/**
 * Created by Maxim on 10.07.2017.
 */

@Service
public class ClientServiceImpl implements ClientService {

    @Override
    public Client getClient(String login) {
        //TODO: Receive from DAO.
        Client client = new Client();
        client.setLogin(login);
        client.setPassword("7110eda4d09e062aa5e4a390b0a572ac0d2c0220");

        return client;
    }
}

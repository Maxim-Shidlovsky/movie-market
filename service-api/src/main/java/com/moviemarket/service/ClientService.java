package com.moviemarket.service;

import com.moviemarket.model.Client;
import org.springframework.dao.DataAccessException;

/**
 * Created by Maxim on 10.07.2017.
 */

public interface ClientService {

    public Client getClient(String login) throws DataAccessException;

    public Integer addClient(Client client) throws DataAccessException;
}
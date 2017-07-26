package com.moviemarket.dao;

import com.moviemarket.model.Client;
import org.springframework.dao.DataAccessException;

/**
 * Created by Maxim on 25.7.17.
 */
public interface ClientMapper {

    public Client getClient(String username) throws DataAccessException;

    //TODO: addClient(), updateClient(), deleteClient()
}

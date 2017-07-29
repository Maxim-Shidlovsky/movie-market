package com.moviemarket.service;

import com.moviemarket.model.Client;
import com.moviemarket.model.ClientRoleEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Maxim on 10.07.2017.
 */

public class ClientDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private ClientService clientService;

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.debug("loadUserByUsername(username={})", username);

        Client client = clientService.getClient(username);

        Set<GrantedAuthority> roles = new HashSet<>();

        if (client.getUsername().equals("admin")) {
            roles.add(new SimpleGrantedAuthority(ClientRoleEnum.ROLE_ADMIN.name()));
        }
        else {
            roles.add(new SimpleGrantedAuthority(ClientRoleEnum.ROLE_CLIENT.name()));
        }

        UserDetails userDetails = new User(client.getUsername(), client.getPassword(), roles);

        return userDetails;
    }
}

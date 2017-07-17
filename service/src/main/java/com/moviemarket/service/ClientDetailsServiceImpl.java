package com.moviemarket.service;

import com.moviemarket.model.Client;
import com.moviemarket.model.ClientRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Maxim on 10.07.2017.
 */

@Service
public class ClientDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientService clientService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientService.getClient("admin");

        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(ClientRoleEnum.ROLE_CLIENT.name()));

        UserDetails userDetails = new User(client.getLogin(), client.getPassword(), roles);

        return userDetails;
    }
}

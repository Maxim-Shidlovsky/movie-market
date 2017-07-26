package com.moviemarket.model;

import java.util.Objects;

/**
 * Created by Maxim on 09.07.2017.
 */
public class Client {

    private Integer clientId;
    private String username;
    private String password;

    public Client() { }

    public Client(Integer clientId, String username, String password) {
        this.clientId = clientId;
        this.username = username;
        this.password = password;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        Client client = (Client) o;
        return Objects.equals(this.clientId, client.getClientId())
                && Objects.equals(this.username, client.getUsername())
                && Objects.equals(this.password, client.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.clientId, this.username, this.password);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

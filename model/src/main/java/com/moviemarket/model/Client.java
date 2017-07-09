package com.moviemarket.model;

import java.util.Objects;

/**
 * Created by andr_srv on 09.07.2017.
 */
public class Client {

    private Integer clientId;
    private String login;
    private String password;

    public Client() { }

    public Client(Integer clientId, String login, String password) {
        this.clientId = clientId;
        this.login = login;
        this.password = password;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
                && Objects.equals(this.login, client.getLogin())
                && Objects.equals(this.password, client.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.clientId, this.login, this.password);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", login='" + login + '\'' +
                '}';
    }
}

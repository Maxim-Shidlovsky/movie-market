package com.moviemarket.model;

import java.util.Date;
import java.util.Objects;

/**
 * Created by Maxim on 16.7.17.
 */
public class Order {

    private Integer orderId;
    private Integer clientId;
    private Integer movieId;
    private Date orderDate;

    public Order() { }

    public Order(Integer orderId, Integer clientId, Integer movieId, Date orderDate) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.movieId = movieId;
        this.orderDate = orderDate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        Order order = (Order) o;
        return Objects.equals(this.orderId, order.getOrderId())
                && Objects.equals(this.clientId, order.getClientId())
                && Objects.equals(this.movieId, order.getMovieId())
                && Objects.equals(this.orderDate, order.getOrderDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, clientId, movieId, orderDate);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", clientId=" + clientId +
                ", movieId=" + movieId +
                ", orderDate=" + orderDate +
                '}';
    }
}

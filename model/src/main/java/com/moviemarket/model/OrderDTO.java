package com.moviemarket.model;

import java.util.Date;
import java.util.Objects;

/**
 * Created by Maxim on 16.7.17.
 */
public class OrderDTO {

    private Integer orderId;
    private Integer clientId;
    private Integer movieId;
    private Integer categoryId;
    private Date orderDate;
    private String movieTitle;
    private String categoryTitle;
    private Date releaseDate;
    private Integer rating;
    private Double price;

    public OrderDTO() { }

    public OrderDTO(Integer orderId, Integer clientId, Integer movieId, Integer categoryId,
                 Date orderDate, String movieTitle, String categoryTitle, Date releaseDate,
                 Integer rating, Double price) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.movieId = movieId;
        this.categoryId = categoryId;
        this.orderDate = orderDate;
        this.movieTitle = movieTitle;
        this.categoryTitle = categoryTitle;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.price = price;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }


    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        OrderDTO order = (OrderDTO) o;
        return Objects.equals(this.orderId, order.getOrderId())
                && Objects.equals(this.clientId, order.getClientId())
                && Objects.equals(this.movieId, order.getMovieId())
                && Objects.equals(this.categoryId, order.getCategoryId())
                && Objects.equals(this.orderDate, order.getOrderDate())
                && Objects.equals(this.movieTitle, order.getMovieTitle())
                && Objects.equals(this.categoryTitle, order.getCategoryTitle())
                && Objects.equals(this.releaseDate, order.getReleaseDate())
                && Objects.equals(this.rating, order.getRating())
                && Objects.equals(this.price, order.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, clientId, movieId, categoryId, orderDate,
                movieTitle, categoryTitle, releaseDate, rating, price);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId=" + orderId +
                ", clientId=" + clientId +
                ", movieId=" + movieId +
                ", categoryId=" + categoryId +
                ", orderDate=" + orderDate +
                ", movieTitle='" + movieTitle + '\'' +
                ", categoryTitle='" + categoryTitle + '\'' +
                ", releaseDate=" + releaseDate +
                ", rating=" + rating +
                ", price=" + price +
                '}';
    }
}

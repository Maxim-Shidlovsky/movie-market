package com.moviemarket.model;

import java.util.Date;
import java.util.Objects;

/**
 * Created by Maxim on 09.07.2017.
 */
public class MovieDTO {

    private Integer movieId;
    private String title;
    private Date releaseDate;
    private Integer rating;
    private Double price;
    private Integer categoryId;
    private String categoryTitle;

    public MovieDTO() { }

    public MovieDTO(Integer movieId, String title, Date releaseDate, Integer rating, Double price,
                    Integer categoryId, String categoryTitle) {
        this.movieId = movieId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.price = price;
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        MovieDTO movie = (MovieDTO) o;
        return Objects.equals(this.movieId, movie.getMovieId())
                && Objects.equals(this.title, movie.getTitle())
                && Objects.equals(this.releaseDate, movie.getReleaseDate())
                && Objects.equals(this.rating, movie.getRating())
                && Objects.equals(this.price, movie.getPrice())
                && Objects.equals(this.categoryId, movie.getCategoryId())
                && Objects.equals(this.categoryTitle, movie.getCategoryTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.movieId, this.title, this.releaseDate,
                this.rating, this.price, this.categoryId, this.categoryTitle);
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", rating=" + rating +
                ", price=" + price +
                ", categoryId=" + categoryId +
                ", categoryTitle='" + categoryTitle + '\'' +
                '}';
    }
}

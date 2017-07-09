package com.moviemarket.model;

import java.util.Objects;

/**
 * Created by andr_srv on 09.07.2017.
 */
public class CategoryDTO {

    private Integer categoryId;
    private String title;
    private Integer amount;

    public  CategoryDTO() { }

    public CategoryDTO(Integer categoryId, String title, Integer amount) {
        this.categoryId = categoryId;
        this.title = title;
        this.amount = amount;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        CategoryDTO category = (CategoryDTO) o;
        return Objects.equals(this.categoryId, category.getCategoryId())
                && Objects.equals(this.title, category.title)
                && Objects.equals(this.amount, category.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.categoryId, this.title, this.amount);
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "categoryId=" + categoryId +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                '}';
    }
}

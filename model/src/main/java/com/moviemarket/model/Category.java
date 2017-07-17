package com.moviemarket.model;

import java.util.Objects;

/**
 * Created by Maxim on 09.07.2017.
 */
public class Category {

    private Integer categoryId;
    private String title;

    public Category() { }

    public Category(Integer categoryId, String title) {
        this.categoryId = categoryId;
        this.title = title;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        Category category = (Category) o;
        return Objects.equals(this.categoryId, category.getCategoryId())
                && Objects.equals(this.title, category.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.categoryId, this.title);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", title='" + title + '\'' +
                '}';
    }
}

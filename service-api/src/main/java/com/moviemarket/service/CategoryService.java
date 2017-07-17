package com.moviemarket.service;

import com.moviemarket.model.Category;
import com.moviemarket.model.CategoryDTO;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by Maxim on 12.7.17.
 */
public interface CategoryService {

    public List<CategoryDTO> getAllCategories() throws DataAccessException;

    public CategoryDTO getCategoryById(Integer categoryId) throws DataAccessException;

    public CategoryDTO getCategoryByTitle(String title) throws DataAccessException;

    public Integer addCategory(Category category) throws DataAccessException;

    public Integer updateCategory(Category category) throws DataAccessException;

    public Integer deleteCategory(Integer categoryId) throws DataAccessException;
}

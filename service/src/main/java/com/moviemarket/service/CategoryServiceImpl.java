package com.moviemarket.service;

import com.moviemarket.dao.CategoryMapper;
import com.moviemarket.model.Category;
import com.moviemarket.model.CategoryDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by Maxim on 12.7.17.
 */

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CategoryMapper categoryMapper;

    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() throws DataAccessException {
        LOGGER.debug("getAllCategories()");
        return categoryMapper.getAllCategories();
    }

    @Override
    public CategoryDTO getCategoryById(Integer categoryId) throws DataAccessException {
        LOGGER.debug("getCategoryById({})", categoryId);
        Assert.notNull(categoryId, "Category's ID mustn't be null.");

        return categoryMapper.getCategoryById(categoryId);
    }

    @Override
    public CategoryDTO getCategoryByTitle(String title) throws DataAccessException {
        LOGGER.debug("getCategoryByTitle({})", title);
        Assert.notNull(title, "Category's title mustn't be null.");
        Assert.hasText(title, "Category's title must have text.");

        return categoryMapper.getCategoryByTitle(title);
    }

    @Override
    public Integer addCategory(Category category) throws DataAccessException {
        LOGGER.debug("addCategory({})", category);
        Assert.notNull(category, "Category mustn't be null.");
        Assert.isNull(category.getCategoryId(), "Category's ID must be null.");
        Assert.notNull(category.getTitle(), "Category's title mustn't be null.");
        Assert.hasText(category.getTitle(), "Category's title must have text.");

        return categoryMapper.addCategory(category);
    }

    @Override
    public Integer updateCategory(Category category) throws DataAccessException {
        LOGGER.debug("updateCategory({})", category);
        Assert.notNull(category, "Category mustn't be null.");
        Assert.notNull(category.getCategoryId(), "Category's ID mustn't be null.");
        Assert.notNull(category.getTitle(), "Category's title mustn't be null.");
        Assert.hasText(category.getTitle(), "Category's title must have text.");

        return categoryMapper.updateCategory(category);
    }

    @Override
    public Integer deleteCategory(Integer categoryId) throws DataAccessException {
        LOGGER.debug("deleteCategory({})", categoryId);
        Assert.notNull(categoryId, "Category's ID mustn't be null.");

        return categoryMapper.deleteCategory(categoryId);
    }
}

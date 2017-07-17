package com.moviemarket.dao;

import com.moviemarket.model.Category;
import com.moviemarket.model.CategoryDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Maxim on 12.7.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-mybatis-spring.xml"})
@Transactional
public class CategoryMapperTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CategoryMapper categoryMapper;


    private static final int CATEGORIES_LENGTH = 5;
    private static final Integer TEST_ID = 3;
    private static final String TEST_TITLE = "Fantastic";
    private static final CategoryDTO testCategory =
            new CategoryDTO(1, "Thriller", 2);

    @Test
    public void getAllCategoriesTest() throws Exception {
        LOGGER.debug("getAllCategoriesTest");
        List<CategoryDTO> categories = categoryMapper.getAllCategories();

        Assert.assertNotNull(categories);
        Assert.assertEquals(CATEGORIES_LENGTH, categories.size());
        Assert.assertEquals(testCategory, categories.get(0));
    }

    @Test
    public void getCategoryById() throws Exception {
        LOGGER.debug("getCategoryById");
        CategoryDTO category = categoryMapper.getCategoryById(TEST_ID);
        Assert.assertNotNull(category);
        Assert.assertEquals(TEST_TITLE, category.getTitle());
    }

    @Test
    public void getCategoryByTitleTest() throws Exception {
        LOGGER.debug("getAllCategoriesTest");
        CategoryDTO category = categoryMapper.getCategoryByTitle(TEST_TITLE);
        Assert.assertNotNull(category);
        Assert.assertTrue(category.getCategoryId() == TEST_ID);
    }

    @Test
    public void addCategoryTest() throws Exception {
        LOGGER.debug("addCategoryTest");

        String testTitle = "testTitle";
        Category newCategory = new Category(6, testTitle);
        categoryMapper.addCategory(newCategory);

        CategoryDTO addedCategory = categoryMapper.getCategoryByTitle(testTitle);
        Assert.assertNotNull(addedCategory);
        Assert.assertEquals(testTitle, addedCategory.getTitle());

        List<CategoryDTO> categories = categoryMapper.getAllCategories();
        int quantityAfterAdding = categories.size();
        Assert.assertEquals(CATEGORIES_LENGTH + 1, quantityAfterAdding);
    }

    @Test
    public void updateCategoryTest() throws Exception {
        LOGGER.debug("updateCategoryTest");
        Category category = new Category(TEST_ID, "newTitle");

        categoryMapper.updateCategory(category);
        CategoryDTO updatedCategory = categoryMapper.getCategoryById(TEST_ID);
        Assert.assertNotNull(updatedCategory);
        Assert.assertEquals(category.getCategoryId(), updatedCategory.getCategoryId());
        Assert.assertEquals(category.getTitle(), updatedCategory.getTitle());
    }

    @Test
    public void deleteCategoryTest() throws Exception {
        LOGGER.debug("deleteCategoryTest");
        categoryMapper.deleteCategory(1);
        int quantityAfterDeleting = categoryMapper.getAllCategories().size();
        Assert.assertEquals(CATEGORIES_LENGTH - 1, quantityAfterDeleting);
    }
}

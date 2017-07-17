package com.moviemarket.service;

import com.moviemarket.model.Category;
import com.moviemarket.model.CategoryDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Maxim on 13.7.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-service-spring.xml"})
@Transactional
public class CategoryServiceTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CategoryService categoryService;


    private static final int CATEGORIES_LENGTH = 5;
    private static final Integer TEST_ID = 3;
    private static final String TEST_TITLE = "Fantastic";
    private static final CategoryDTO testCategory =
            new CategoryDTO(1, "Thriller", 2);

    @Test
    public void getAllCategoriesTest() throws Exception {
        LOGGER.debug("getAllCategoriesTest()");
        List<CategoryDTO> categories = categoryService.getAllCategories();

        Assert.assertNotNull(categories);
        Assert.assertEquals(CATEGORIES_LENGTH, categories.size());
        Assert.assertEquals(testCategory, categories.get(0));
    }

    @Test
    public void getCategoryByIdTest() throws Exception {
        LOGGER.debug("getCategoryByIdTest()");
        CategoryDTO category = categoryService.getCategoryById(TEST_ID);
        Assert.assertNotNull(category);
        Assert.assertEquals(TEST_TITLE, category.getTitle());
    }

    @Test
    public void getCategoryByTitleTest() throws Exception {
        LOGGER.debug("getAllCategoriesTest()");
        CategoryDTO category = categoryService.getCategoryByTitle(TEST_TITLE);
        Assert.assertNotNull(category);
        Assert.assertTrue(category.getCategoryId() == TEST_ID);
    }

    @Test
    public void addCategoryTest() throws Exception {
        LOGGER.debug("addCategoryTest()");

        Category newCategory = new Category();
        String testTitle = "testTitle";
        newCategory.setTitle(testTitle);
        categoryService.addCategory(newCategory);

        List<CategoryDTO> categories = categoryService.getAllCategories();
        int quantityAfter = categories.size();
        Assert.assertEquals(CATEGORIES_LENGTH + 1, quantityAfter);

        CategoryDTO addedCategory = categoryService.getCategoryByTitle(testTitle);
        Assert.assertNotNull(addedCategory);
        Assert.assertEquals(testTitle, addedCategory.getTitle());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addCategoryWithNotNullIdTest() throws Exception {
        LOGGER.debug("addCategoryWithNotNullIdTest()");
        Category newCategory = new Category(56, "title");
        categoryService.addCategory(newCategory);
    }

    @Test
    public void updateCategoryTest() throws Exception {
        LOGGER.debug("updateCategoryTest()");
        Category category = new Category(TEST_ID, "newTitle");

        categoryService.updateCategory(category);
        CategoryDTO updatedCategory = categoryService.getCategoryById(TEST_ID);
        Assert.assertNotNull(updatedCategory);
        Assert.assertEquals(category.getTitle(), updatedCategory.getTitle());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateCategoryWithNullIdTest() throws Exception {
        LOGGER.debug("updateCategoryWithNullIdTest()");
        Category category = new Category(null, "testTitle");
        categoryService.updateCategory(category);
    }

    @Test
    public void deleteCategoryTest() throws Exception {
        LOGGER.debug("deleteCategoryTest()");
        categoryService.deleteCategory(1);
        int quantityAfterDeleting = categoryService.getAllCategories().size();
        Assert.assertEquals(CATEGORIES_LENGTH - 1, quantityAfterDeleting);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteCategoryByNullIdTest() throws Exception {
        LOGGER.debug("deleteCategoryByNullIdTest()");
        categoryService.deleteCategory(null);
    }
}

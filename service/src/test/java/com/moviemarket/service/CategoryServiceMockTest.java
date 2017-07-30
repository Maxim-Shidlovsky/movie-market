package com.moviemarket.service;

import com.moviemarket.dao.CategoryMapper;
import com.moviemarket.model.Category;
import com.moviemarket.model.CategoryDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxim on 13.7.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class CategoryServiceMockTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper mockCategoryMapper;


    private static final CategoryDTO TEST_CATEGORY =
            new CategoryDTO(1, "Thriller", 2);

    @After
    public void clean() {
        LOGGER.debug("clean()");
        EasyMock.verify(mockCategoryMapper);
        EasyMock.reset(mockCategoryMapper);
    }

    @Test
    public void getAllCategoriesTest() throws Exception {
        LOGGER.debug("getAllCategoriesTest()");
        List<CategoryDTO> categories = new ArrayList<CategoryDTO>();
        EasyMock.expect(mockCategoryMapper.getAllCategories()).andReturn(categories);
        EasyMock.replay(mockCategoryMapper);
        Assert.assertEquals(categories, categoryService.getAllCategories());
    }

    @Test
    public void getCategoryByIdTest() throws Exception {
        LOGGER.debug("getCategoryByIdTest()");
        EasyMock.expect(mockCategoryMapper.getCategoryById(TEST_CATEGORY.getCategoryId()))
                .andReturn(TEST_CATEGORY);
        EasyMock.replay(mockCategoryMapper);
        Assert.assertEquals(TEST_CATEGORY,
                categoryService.getCategoryById(TEST_CATEGORY.getCategoryId()));
    }

    @Test
    public void getCategoryByTitleTest() throws Exception {
        LOGGER.debug("getAllCategoriesTest()");
        EasyMock.expect(mockCategoryMapper.getCategoryByTitle(TEST_CATEGORY.getTitle()))
                .andReturn(TEST_CATEGORY);
        EasyMock.replay(mockCategoryMapper);
        Assert.assertEquals(TEST_CATEGORY,
                categoryService.getCategoryByTitle(TEST_CATEGORY.getTitle()));
    }

    @Test
    public void addCategoryTest() throws Exception {
        LOGGER.debug("addCategoryTest()");
        Category category = new Category();
        category.setTitle(TEST_CATEGORY.getTitle());
        EasyMock.expect(mockCategoryMapper.addCategory(category))
                .andReturn(TEST_CATEGORY.getCategoryId());
        EasyMock.expect(mockCategoryMapper.getCategoryById(TEST_CATEGORY.getCategoryId()))
                .andReturn(TEST_CATEGORY);
        EasyMock.replay(mockCategoryMapper);
        Integer newId = categoryService.addCategory(category);
        CategoryDTO addedCategory = categoryService.getCategoryById(newId);
        Assert.assertEquals(TEST_CATEGORY.getTitle(), addedCategory.getTitle());
    }

    @Test
    public void updateCategoryTest() throws Exception {
        LOGGER.debug("updateCategoryTest()");
        Category category = new Category(1, "title");
        EasyMock.expect(mockCategoryMapper.updateCategory(category)).andReturn(1);
        EasyMock.replay(mockCategoryMapper);
        Assert.assertEquals(1, categoryService.updateCategory(category).intValue());
    }

    @Test
    public void deleteCategoryTest() throws Exception {
        LOGGER.debug("deleteCategoryTest()");
        EasyMock.expect(mockCategoryMapper.deleteCategory(1)).andReturn(1);
        EasyMock.replay(mockCategoryMapper);
        Assert.assertEquals(1, categoryService.deleteCategory(1).intValue());
    }
}

package com.moviemarket.rest;

import com.moviemarket.model.Category;
import com.moviemarket.model.CategoryDTO;
import com.moviemarket.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import java.util.Arrays;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by Maxim on 14.7.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:rest-mock-test.xml"})
public class CategoryControllerMockTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Resource
    private CategoryRestController categoryController;

    private MockMvc mockMvc;

    @Autowired
    private CategoryService mockCategoryService;


    private static final CategoryDTO testCategory =
            new CategoryDTO(1, "Thriller", 2);

    @Before
    public void setUp() {
        LOGGER.debug("setUp()");
        mockMvc = standaloneSetup(categoryController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @After
    public void tearDown() {
        LOGGER.debug("tearDown()");
        verify(mockCategoryService);
        reset(mockCategoryService);
    }

    @Test
    public void getAllCategoriesTest() throws Exception {
        LOGGER.debug("getAllCategoriesTest()");
        expect(mockCategoryService.getAllCategories()).andReturn(
                Arrays.<CategoryDTO>asList(
                        new CategoryDTO(8, "testTitle", 10)));
        replay(mockCategoryService);

        mockMvc.perform(
                get("/categories")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getCategoryByIdTest() throws Exception {
        LOGGER.debug("getCategoryByIdTest()");
        expect(mockCategoryService.getCategoryById(testCategory.getCategoryId()))
                .andReturn(testCategory);
        replay(mockCategoryService);

        String categoryString = new ObjectMapper().writeValueAsString(testCategory);

        mockMvc.perform(
                get("/category/" + testCategory.getCategoryId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(categoryString));
    }

    @Test
    public void getCategoryByTitleTest() throws Exception {
        LOGGER.debug("getCategoryByTitleTest()");
        expect(mockCategoryService.getCategoryByTitle(testCategory.getTitle()))
                .andReturn(testCategory);
        replay(mockCategoryService);

        String categoryString = new ObjectMapper().writeValueAsString(testCategory);

        mockMvc.perform(
                get("/category/title/" + testCategory.getTitle())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(categoryString));
    }

    @Test
    public void addCategoryTest() throws Exception {
        LOGGER.debug("addCategoryTest()");
        expect(mockCategoryService.addCategory(anyObject(Category.class))).andReturn(3);
        replay(mockCategoryService);

        String category = new ObjectMapper().writeValueAsString(
                new Category(9, "testTitle"));

        mockMvc.perform(
                post("/category")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(category))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string( "3"));
    }

    @Test
    public void updateCategoryTest() throws Exception {
        LOGGER.debug("updateCategoryTest()");
        expect(mockCategoryService.updateCategory(anyObject(Category.class))).andReturn(0);
        replay(mockCategoryService);

        String category = new ObjectMapper().writeValueAsString(
                new Category(9, "testTitle"));


        mockMvc.perform(
                put("/category")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(category)
        ).andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().string(""));
    }

    @Test
    public void deleteCategoryTest() throws Exception {
        LOGGER.debug("deleteCategoryTest()");
        expect(mockCategoryService.deleteCategory(anyObject(Integer.class))).andReturn(0);
        replay(mockCategoryService);

        mockMvc.perform(
                delete("/category/8")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }
}

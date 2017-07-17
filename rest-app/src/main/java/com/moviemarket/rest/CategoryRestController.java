package com.moviemarket.rest;

import com.moviemarket.model.Category;
import com.moviemarket.model.CategoryDTO;
import com.moviemarket.service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Maxim on 13.7.17.
 */

@CrossOrigin
@RestController
public class CategoryRestController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CategoryService categoryService;

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({IllegalArgumentException.class})
    public String incorrectDataError() {
        return "{  \"response\" : \"Incorrect Data Error\" }";
    }


    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public @ResponseBody List<CategoryDTO> getAllCategories() {
        LOGGER.debug("getAllCategories()");
        return categoryService.getAllCategories();
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public @ResponseBody CategoryDTO getCategoryById(@PathVariable(value = "id") Integer categoryId) {
        LOGGER.debug("getCategoryById({})", categoryId);
        return categoryService.getCategoryById(categoryId);
    }

    @RequestMapping(value = "/category/title/{title}", method = RequestMethod.GET)
    public @ResponseBody CategoryDTO getCategoryByTitle(@PathVariable(value = "title") String title) {
        LOGGER.debug("getCategoryByTitle({})", title);
        return categoryService.getCategoryByTitle(title);
    }

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody Integer addCategory(@RequestBody Category category) {
        LOGGER.debug("addCategory({})", category);
        return categoryService.addCategory(category);
    }

    @RequestMapping(value = "/category", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody Integer updateCategory(@RequestBody Category category) {
        LOGGER.debug("updateCategory({})", category);
        return categoryService.updateCategory(category);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Integer deleteCategory(@PathVariable(value = "id") Integer categoryId) {
        LOGGER.debug("deleteCategory({})", categoryId);
        return categoryService.deleteCategory(categoryId);
    }
}

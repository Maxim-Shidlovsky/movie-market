package com.moviemarket.rest;

import com.moviemarket.model.Category;
import com.moviemarket.model.CategoryDTO;
import com.moviemarket.service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxim on 13.7.17.
 */

@Controller
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
    public String getAllCategories(Model model) {
        LOGGER.debug("getAllCategories()");
        List<CategoryDTO> categoryList = categoryService.getAllCategories();
        model.addAttribute(categoryList);
        return "categories";
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public String getCategoryById(@PathVariable(value = "id") Integer categoryId, Model model) {
        LOGGER.debug("getCategoryById({})", categoryId);
        List<CategoryDTO> categoryList = new ArrayList<CategoryDTO>();
        categoryList.add(categoryService.getCategoryById(categoryId));
        model.addAttribute(categoryList);
        return "categories";
    }

    @RequestMapping(value = "/category/title/{title}", method = RequestMethod.GET)
    public String getCategoryByTitle(@PathVariable(value = "title") String title, Model model) {
        LOGGER.debug("getCategoryByTitle({})", title);
        List<CategoryDTO> categoryList = new ArrayList<CategoryDTO>();
        categoryList.add(categoryService.getCategoryByTitle(title));
        model.addAttribute(categoryList);
        return "categories";
    }

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public String addCategory(@RequestParam String title, Model model) {
        LOGGER.debug("addCategory(title={})", title);
        Category category = new Category(null, title);
        categoryService.addCategory(category);
        model.addAttribute(categoryService.getAllCategories());
        return "categories";
    }

    @RequestMapping(value = "/category", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public String updateCategory(@RequestParam Integer categoryId, @RequestParam String title,
              Model model) {
        LOGGER.debug("updateCategory(categoryId={}, title={})", categoryId, title);
        Category category = new Category(categoryId, title);
        categoryService.updateCategory(category);
        model.addAttribute(categoryService.getAllCategories());
        return "categories";
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteCategory(@PathVariable(value = "id") Integer categoryId, Model model) {
        LOGGER.debug("deleteCategory({})", categoryId);
        categoryService.deleteCategory(categoryId);
        model.addAttribute(categoryService.getAllCategories());
        return "categories";
    }
}

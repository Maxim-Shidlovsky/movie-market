package com.moviemarket.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Maxim on 10.07.2017.
 */

@Controller
public class MainController {

    private static final Logger LOGGER = LogManager.getLogger();

    @RequestMapping(value = "/movies-catalog", method = RequestMethod.GET)
    public String showMovies() {
        LOGGER.debug("showMovies()");
        return "movies";
    }

    @RequestMapping(value = "/categories-catalog", method = RequestMethod.GET)
    public String showCategories() {
        LOGGER.debug("showCategories()");
        return "categories";
    }

    @RequestMapping(value = "/coupons-catalog", method = RequestMethod.GET)
    public String showCoupons() {
        LOGGER.debug("showCoupons()");
        return "coupons";
    }

    @RequestMapping(value = "/shopping-cart", method = RequestMethod.GET)
    public String showOrders() {
        LOGGER.debug("showOrders()");
        return "orders";
    }
}

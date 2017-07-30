package com.moviemarket.rest;

import com.moviemarket.model.Client;
import com.moviemarket.service.ClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Maxim on 10.07.2017.
 */

@Controller
public class MainController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private ClientService clientService;


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

    @RequestMapping(value = "/registration", method = RequestMethod.GET, params = "new")
    public String showRegistrationForm(Model model) {
        LOGGER.debug("showRegistrationForm()");
        model.addAttribute(new Client());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createClient(Client client) {
        LOGGER.debug("createClient({})", client);
        clientService.addClient(client);

        return "redirect:/login";
    }
}

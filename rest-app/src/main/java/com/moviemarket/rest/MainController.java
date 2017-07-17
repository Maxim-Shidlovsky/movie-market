package com.moviemarket.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Maxim on 10.07.2017.
 */

@Controller
@RequestMapping("/")
public class MainController {

    private static final Logger LOGGER = LogManager.getLogger();

    @RequestMapping(method = RequestMethod.GET)
    public String start(Model model) {
        LOGGER.debug("start();");
        return "index";
    }
}

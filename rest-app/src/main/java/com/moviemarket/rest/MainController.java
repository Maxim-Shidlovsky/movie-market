package com.moviemarket.rest;

import com.moviemarket.model.Category;
import com.moviemarket.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxim on 10.07.2017.
 */

@Controller
@RequestMapping("/home")
public class MainController {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final List<Client> clientList = new ArrayList<Client>();

    static {
        clientList.add(new Client(1, "ALex", "111"));
        clientList.add(new Client(2, "Bob", "222"));
        clientList.add(new Client(3, "Martin", "333"));
    }

    @RequestMapping(method = RequestMethod.GET)
    public String start(Model model) {
        LOGGER.debug("start();");
        model.addAttribute(clientList);
        model.addAttribute(new Category(1, "test category"));
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addClient(@RequestParam Integer clientId, @RequestParam String login,
                            @RequestParam String password) {
        Client client = new Client(clientId, login, password);
        System.out.println("CLIENT: " + client);
        clientList.add(client);
        return "redirect:/home";
    }
}

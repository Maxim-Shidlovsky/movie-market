package com.moviemarket.rest;

import com.moviemarket.model.Order;
import com.moviemarket.model.OrderDTO;
import com.moviemarket.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Maxim on 25.7.17.
 */

@Controller
public class OrderController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private OrderService orderService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }


    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({IllegalArgumentException.class})
    public String incorrectDataError() {
        return "{  \"response\" : \"Incorrect Data Error\" }";
    }

    @RequestMapping(value = "/orders/{username}", method = RequestMethod.GET)
    public String getAllOrdersByUsername(@PathVariable(name = "username") String username,
                                         Model model) {
        LOGGER.debug("getAllOrdersByUsername({})", username);
        model.addAttribute(orderService.getAllOrdersByUsername(username));
        return "orders";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public String addOrder(@PathVariable Integer movieId, @RequestParam String username,
                           Model model) {
        LOGGER.debug("addOrder(movieId={}, username={})", movieId, username);
        Order order = new Order(null, null, movieId, new Date());
        orderService.addOrder(order, username);
        model.addAttribute(orderService.getAllOrdersByUsername(username));
        return "orders";
    }

    @RequestMapping(value = "/order", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteOrder(@RequestParam Integer orderId, @RequestParam String username,
                              Model model) {
        LOGGER.debug("deleteOrder(orderId={}, username={})", orderId, username);
        orderService.deleteOrder(orderId);
        model.addAttribute(orderService.getAllOrdersByUsername(username));
        return "orders";
    }
}

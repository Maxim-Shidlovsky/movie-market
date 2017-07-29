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

@CrossOrigin
@RestController
@RequestMapping(value = "/orders")
public class OrderRestController {

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

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public @ResponseBody List<OrderDTO> getAllOrdersByUsername(
            @PathVariable(name = "username") String username) {
        LOGGER.debug("getAllOrdersByUsername({})", username);
        return orderService.getAllOrdersByUsername(username);
    }

    @RequestMapping(value = "/{username}/movie/{id}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addOrder(@PathVariable(name = "username") String username,
                         @PathVariable(name = "id") Integer movieId) {
        LOGGER.debug("addOrder(movieId={}, username={})", movieId, username);
        Order order = new Order(null, null, movieId, new Date());
        orderService.addOrder(order, username);
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteOrder(@PathVariable(name = "id") Integer orderId) {
        LOGGER.debug("deleteOrder(orderId={})", orderId);
        orderService.deleteOrder(orderId);
    }
}

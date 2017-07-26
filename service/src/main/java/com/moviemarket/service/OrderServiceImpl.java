package com.moviemarket.service;

import com.moviemarket.dao.OrderMapper;
import com.moviemarket.model.Order;
import com.moviemarket.model.OrderDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by Maxim on 25.7.17.
 */
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private OrderMapper orderMapper;

    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }


    @Override
    public List<OrderDTO> getAllOrdersByUsername(String username) throws DataAccessException {
        LOGGER.debug("getAllOrdersByUsername({})", username);
        Assert.notNull(username, "Username must not be null.");
        Assert.hasText(username, "Username must have text.");

        return orderMapper.getAllOrdersByUsername(username);
    }

    @Override
    public Integer addOrder(Order order, String username) throws DataAccessException {
        LOGGER.debug("addOrder({}, username={})", order, username);
        Assert.notNull(order, "Order mustn't be null.");
        Assert.isNull(order.getOrderId(), "Order's ID must be null.");
        Assert.notNull(order.getMovieId(), "Movie's ID mustn't be null.");
        Assert.notNull(order.getOrderDate(), "Order's date mustn't be null.");
        Assert.notNull(username, "Username must not be null.");
        Assert.hasText(username, "Username must have text.");

        return orderMapper.addOrder(order, username);
    }

    @Override
    public Integer deleteOrder(Integer orderId) throws DataAccessException {
        LOGGER.debug("deleteOrder({})", orderId);
        Assert.notNull(orderId, "Order's ID mustn't be null.");

        return orderMapper.deleteOrder(orderId);
    }
}

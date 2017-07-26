package com.moviemarket.service;

import com.moviemarket.model.Order;
import com.moviemarket.model.OrderDTO;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by Maxim on 25.7.17.
 */
public interface OrderService {

    public List<OrderDTO> getAllOrdersByUsername(String username) throws DataAccessException;

    public Integer addOrder(Order order, String username) throws DataAccessException;

    public Integer deleteOrder(Integer orderId) throws DataAccessException;
}

package com.moviemarket.dao;

import com.moviemarket.model.Order;
import com.moviemarket.model.OrderDTO;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by Maxim on 16.7.17.
 */
public interface OrderMapper {

    public List<OrderDTO> getAllOrdersByClientId(Integer clientId) throws DataAccessException;

    public Integer addOrder(Order order) throws DataAccessException;

    public Integer deleteOrder(Integer orderId) throws DataAccessException;
}

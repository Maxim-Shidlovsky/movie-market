package com.moviemarket.dao;

import com.moviemarket.model.Order;
import com.moviemarket.model.OrderDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Maxim on 16.7.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-mybatis-spring.xml"})
@Transactional
public class OrderMapperTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private OrderMapper orderMapper;


    private static final int USER3_ORDER_LENGTH = 3;
    private static final Integer TEST_CLIENT_ID = 3;
    private static final String TEST_USERNAME = "user3";
    private static final Order testOrder = new Order(3, 3, 10,
            new Date(117, 0, 1));

    @Test
    public void getAllOrdersByUsername() throws Exception {
        LOGGER.debug("getAllOrdersByUsername()");
        List<OrderDTO> orders = orderMapper.getAllOrdersByUsername(TEST_USERNAME);

        Assert.assertEquals(USER3_ORDER_LENGTH, orders.size());
        Assert.assertEquals(testOrder.getOrderId(), orders.get(0).getOrderId());
        Assert.assertEquals(testOrder.getMovieId(), orders.get(0).getMovieId());
        Assert.assertEquals(testOrder.getClientId(), orders.get(0).getClientId());
    }

    @Test
    public void addOrder() throws Exception {
        LOGGER.debug("addOrder()");
        Order newOrder = new Order();
        newOrder.setMovieId(7);
        newOrder.setOrderDate(new Date(117, 2, 2));
        orderMapper.addOrder(newOrder, TEST_USERNAME);

        List<OrderDTO> orders = orderMapper.getAllOrdersByUsername(TEST_USERNAME);
        for (OrderDTO o : orders)
            System.out.println(o);
        int quantityAfterAdding = orders.size();
        Assert.assertEquals(USER3_ORDER_LENGTH + 1, quantityAfterAdding);

        OrderDTO addedOrder = orders.get(3);
        Assert.assertNotNull(addedOrder);
        Assert.assertEquals((Integer) TEST_CLIENT_ID, addedOrder.getClientId());
        Assert.assertEquals(newOrder.getMovieId(), addedOrder.getMovieId());
        Assert.assertEquals(newOrder.getOrderDate(), addedOrder.getOrderDate());
    }

    @Test
    public void deleteOrder() throws Exception {
        LOGGER.debug("deleteOrder()");
        orderMapper.deleteOrder(3);
        int quantityAfterDeleting = orderMapper.getAllOrdersByUsername(TEST_USERNAME).size();
        Assert.assertEquals(USER3_ORDER_LENGTH - 1, quantityAfterDeleting);
    }
}

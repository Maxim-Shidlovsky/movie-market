package com.moviemarket.service;

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
 * Created by Maxim on 30.7.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-service-spring.xml"})
@Transactional
public class OrderServiceTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private OrderService orderService;


    private static final int USER3_ORDER_LENGTH = 3;
    private static final Integer TEST_CLIENT_ID = 3;
    private static final String TEST_USERNAME = "user3";
    private static final Order TEST_ORDER = new Order(3, 3, 10,
            new Date(117, 0, 1));

    @Test
    public void getAllOrdersByUsernameTest() throws Exception {
        LOGGER.debug("getAllOrdersByUsernameTest()");
        List<OrderDTO> orders = orderService.getAllOrdersByUsername(TEST_USERNAME);

        Assert.assertEquals(USER3_ORDER_LENGTH, orders.size());
        Assert.assertEquals(TEST_ORDER.getOrderId(), orders.get(0).getOrderId());
        Assert.assertEquals(TEST_ORDER.getMovieId(), orders.get(0).getMovieId());
        Assert.assertEquals(TEST_ORDER.getClientId(), orders.get(0).getClientId());
    }

    @Test
    public void addOrderTest() throws Exception {
        LOGGER.debug("addOrderTest()");
        Order newOrder = new Order();
        newOrder.setMovieId(7);
        newOrder.setOrderDate(new Date(117, 2, 2));
        orderService.addOrder(newOrder, TEST_USERNAME);

        List<OrderDTO> orders = orderService.getAllOrdersByUsername(TEST_USERNAME);
        for (OrderDTO o : orders)
            System.out.println(o);
        int quantityAfterAdding = orders.size();
        Assert.assertEquals(USER3_ORDER_LENGTH + 1, quantityAfterAdding);

        OrderDTO addedOrder = orders.get(3);
        Assert.assertNotNull(addedOrder);
        Assert.assertEquals(TEST_CLIENT_ID, addedOrder.getClientId());
        Assert.assertEquals(newOrder.getMovieId(), addedOrder.getMovieId());
        Assert.assertEquals(newOrder.getOrderDate(), addedOrder.getOrderDate());
    }

    @Test
    public void deleteOrderTest() throws Exception {
        LOGGER.debug("deleteOrderTest()");
        orderService.deleteOrder(3);
        int quantityAfterDeleting = orderService.getAllOrdersByUsername(TEST_USERNAME).size();
        Assert.assertEquals(USER3_ORDER_LENGTH - 1, quantityAfterDeleting);
    }
}

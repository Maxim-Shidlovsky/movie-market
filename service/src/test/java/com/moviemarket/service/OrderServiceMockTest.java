package com.moviemarket.service;

import com.moviemarket.dao.OrderMapper;
import com.moviemarket.model.Order;
import com.moviemarket.model.OrderDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Maxim on 30.7.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class OrderServiceMockTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper mockOrderMapper;


    private static final String TEST_USERNAME = "user3";
    private static final Order TEST_ORDER = new Order(3, 3, 10,
            new Date(117, 0, 1));

    @After
    public void clean() {
        LOGGER.debug("clean()");
        EasyMock.verify(mockOrderMapper);
        EasyMock.reset(mockOrderMapper);
    }

    @Test
    public void getAllOrdersByUsernameTest() throws Exception {
        LOGGER.debug("getAllOrdersByUsernameTest()");
        List<OrderDTO> orders = new ArrayList<OrderDTO>();
        EasyMock.expect(mockOrderMapper.getAllOrdersByUsername(TEST_USERNAME)).andReturn(orders);
        EasyMock.replay(mockOrderMapper);
        Assert.assertEquals(orders, orderService.getAllOrdersByUsername(TEST_USERNAME));
    }

    @Test
    public void addOrderTest() throws Exception {
        LOGGER.debug("addOrderTest()");
        Order order = new Order();
        order.setClientId(TEST_ORDER.getClientId());
        order.setMovieId(TEST_ORDER.getMovieId());
        order.setOrderDate(TEST_ORDER.getOrderDate());
        EasyMock.expect(mockOrderMapper.addOrder(order, TEST_USERNAME)).andReturn(TEST_ORDER.getOrderId());

        List<OrderDTO> orders = new ArrayList<OrderDTO>();
        OrderDTO testOrderDTO = new OrderDTO();
        testOrderDTO.setClientId(TEST_ORDER.getClientId());
        testOrderDTO.setMovieId(TEST_ORDER.getMovieId());
        testOrderDTO.setOrderDate(TEST_ORDER.getOrderDate());
        orders.add(testOrderDTO);
        EasyMock.expect(mockOrderMapper.getAllOrdersByUsername(TEST_USERNAME))
                .andReturn(orders);
        EasyMock.replay(mockOrderMapper);
        orderService.addOrder(order, TEST_USERNAME);
        OrderDTO addedOrder = orderService.getAllOrdersByUsername(TEST_USERNAME).get(0);
        Assert.assertEquals(TEST_ORDER.getClientId(), addedOrder.getClientId());
        Assert.assertEquals(TEST_ORDER.getMovieId(), addedOrder.getMovieId());
        Assert.assertEquals(TEST_ORDER.getOrderDate(), addedOrder.getOrderDate());
    }

    @Test
    public void deleteOrderTest() throws Exception {
        LOGGER.debug("deleteOrderTest()");
        EasyMock.expect(mockOrderMapper.deleteOrder(1)).andReturn(1);
        EasyMock.replay(mockOrderMapper);
        Assert.assertEquals(1, orderService.deleteOrder(1).intValue());
    }
}

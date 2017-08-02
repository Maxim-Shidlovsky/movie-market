package com.moviemarket.rest;

import com.moviemarket.model.Order;
import com.moviemarket.model.OrderDTO;
import com.moviemarket.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;

import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 * Created by Maxim on 30.7.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rest-mock-test.xml"})
public class OrderControllerMockTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Resource
    private OrderRestController orderController;

    private MockMvc mockMvc;

    @Autowired
    private OrderService mockOrderService;


    private static final String TEST_USERNAME = "user3";

    @Before
    public void setUp() {
        LOGGER.debug("setUp()");
        mockMvc = standaloneSetup(orderController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @After
    public void tearDown() {
        LOGGER.debug("tearDown()");
        verify(mockOrderService);
        reset(mockOrderService);
    }

    @Test
    public void getAllOrderByUsernameTest() throws Exception {
        LOGGER.debug("getAllOrderByUsernameTest()");
        expect(mockOrderService.getAllOrdersByUsername(TEST_USERNAME)).andReturn(
                Arrays.<OrderDTO>asList(
                        new OrderDTO(1, 1, 1, 1,
                                new Date(), "movieTitle", "categoryTitle",
                                new Date(), 5, 10D)));
        replay(mockOrderService);

        mockMvc.perform(
                get("/orders/" + TEST_USERNAME)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void addOrderTest() throws Exception {
        LOGGER.debug("addOrderTest()");
        expect(mockOrderService.addOrder(anyObject(Order.class), anyObject(String.class))).andReturn(1);
        replay(mockOrderService);

        mockMvc.perform(
                post("/orders/user1/movie/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteCategoryTest() throws Exception {
        LOGGER.debug("deleteCategoryTest()");
        expect(mockOrderService.deleteOrder(anyObject(Integer.class))).andReturn(1);
        replay(mockOrderService);

        mockMvc.perform(
                delete("/orders/order/8")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }
}

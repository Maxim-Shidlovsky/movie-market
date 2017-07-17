package com.moviemarket.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviemarket.model.Coupon;
import com.moviemarket.service.CouponService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by Maxim on 15.7.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:rest-mock-test.xml"})
public class CouponControllerMockTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Resource
    private CouponRestController couponController;

    private MockMvc mockMvc;

    @Autowired
    private CouponService mockCouponService;


    private static final Coupon testCoupon = new Coupon(1, "123",
            20D, new Date(117, 0, 1));

    @Before
    public void setUp() {
        LOGGER.debug("setUp()");
        mockMvc = standaloneSetup(couponController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @After
    public void tearDown() {
        LOGGER.debug("tearDown()");
        verify(mockCouponService);
        reset(mockCouponService);
    }

    @Test
    public void getAllCouponsTest() throws Exception {
        LOGGER.debug("getAllCouponsTest()");
        expect(mockCouponService.getAllCoupons()).andReturn(
                Arrays.<Coupon>asList(testCoupon));
        replay(mockCouponService);

        mockMvc.perform(
                get("/coupons")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getCouponByIdTest() throws Exception {
        LOGGER.debug("getCouponByIdTest()");
        expect(mockCouponService.getCouponById(testCoupon.getCouponId()))
                .andReturn(testCoupon);
        replay(mockCouponService);

        String couponString = new ObjectMapper().writeValueAsString(testCoupon);

        mockMvc.perform(
                get("/coupon/" + testCoupon.getCouponId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(couponString));
    }

    @Test
    public void getCouponByCodeTest() throws Exception {
        LOGGER.debug("getCouponByCodeTest()");
        expect(mockCouponService.getCouponByCode(testCoupon.getCode()))
                .andReturn(testCoupon);
        replay(mockCouponService);

        String couponString = new ObjectMapper().writeValueAsString(testCoupon);

        mockMvc.perform(
                get("/coupon/code/" + testCoupon.getCode())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(couponString));
    }

    @Test
    public void addCouponTest() throws Exception {
        LOGGER.debug("addCouponTest()");
        expect(mockCouponService.addCoupon(anyObject(Coupon.class))).andReturn(3);
        replay(mockCouponService);

        String coupon = new ObjectMapper().writeValueAsString(
                new Coupon(9, "testCode", 10D, new Date()));

        mockMvc.perform(
                post("/coupon")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(coupon))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string( "3"));
    }

    @Test
    public void updateCouponTest() throws Exception {
        LOGGER.debug("updateCouponTest()");
        expect(mockCouponService.updateCoupon(anyObject(Coupon.class))).andReturn(0);
        replay(mockCouponService);

        String coupon = new ObjectMapper().writeValueAsString(
                new Coupon(9, "testCode", 10D, new Date()));


        mockMvc.perform(
                put("/coupon")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(coupon)
        ).andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().string(""));
    }

    @Test
    public void deleteCouponTest() throws Exception {
        LOGGER.debug("deleteCouponTest()");
        expect(mockCouponService.deleteCoupon(anyObject(Integer.class))).andReturn(0);
        replay(mockCouponService);

        mockMvc.perform(
                delete("/coupon/8")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }
}

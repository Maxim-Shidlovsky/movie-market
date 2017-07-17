package com.moviemarket.service;

import com.moviemarket.dao.CouponMapper;
import com.moviemarket.model.Coupon;
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
 * Created by Maxim on 15.7.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class CouponServiceMockTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponMapper mockCouponMapper;


    private static final Coupon testCoupon = new Coupon(1, "123",
            20D, new Date(117, 0, 1));

    @After
    public void clean() {
        LOGGER.debug("clean()");
        EasyMock.verify(mockCouponMapper);
        EasyMock.reset(mockCouponMapper);
    }

    @Test
    public void getAllCouponsTest() throws Exception {
        LOGGER.debug("getAllCouponsTest()");
        List<Coupon> coupons = new ArrayList<Coupon>();
        EasyMock.expect(mockCouponMapper.getAllCoupons()).andReturn(coupons);
        EasyMock.replay(mockCouponMapper);
        Assert.assertEquals(coupons, couponService.getAllCoupons());
    }

    @Test
    public void getCouponByIdTest() throws Exception {
        LOGGER.debug("getCouponByIdTest()");
        EasyMock.expect(mockCouponMapper.getCouponById(testCoupon.getCouponId()))
                .andReturn(testCoupon);
        EasyMock.replay(mockCouponMapper);
        Assert.assertEquals(testCoupon, mockCouponMapper.getCouponById(testCoupon.getCouponId()));
    }

    @Test
    public void getCouponByCodeTest() throws Exception {
        LOGGER.debug("getCouponByCodeTest()");
        EasyMock.expect(mockCouponMapper.getCouponByCode(testCoupon.getCode()))
                .andReturn(testCoupon);
        EasyMock.replay(mockCouponMapper);
        Assert.assertEquals(testCoupon, mockCouponMapper.getCouponByCode(testCoupon.getCode()));
    }

    @Test
    public void addCouponTest() throws Exception {
        LOGGER.debug("addCouponTest()");
        Coupon coupon = new Coupon();
        coupon.setCode(testCoupon.getCode());
        coupon.setDiscount(testCoupon.getDiscount());
        coupon.setReceivingDate(testCoupon.getReceivingDate());
        EasyMock.expect(mockCouponMapper.addCoupon(coupon)).andReturn(testCoupon.getCouponId());
        EasyMock.expect(mockCouponMapper.getCouponById(testCoupon.getCouponId()))
                .andReturn(testCoupon);
        EasyMock.replay(mockCouponMapper);
        Integer newId = couponService.addCoupon(coupon);
        Coupon addedCoupon = couponService.getCouponById(newId);
        Assert.assertEquals(testCoupon.getCode(), addedCoupon.getCode());
        Assert.assertEquals(testCoupon.getDiscount(), addedCoupon.getDiscount());
        Assert.assertEquals(testCoupon.getReceivingDate(), addedCoupon.getReceivingDate());
    }

    @Test
    public void updateCouponTest() throws Exception {
        LOGGER.debug("updateCouponTest()");
        Coupon coupon = new Coupon(1, "updateCode", 55D,
                new Date(110, 4, 4));
        EasyMock.expect(mockCouponMapper.updateCoupon(coupon)).andReturn(1);
        EasyMock.replay(mockCouponMapper);
        Assert.assertEquals(1, couponService.updateCoupon(coupon).intValue());
    }

    @Test
    public void deleteCouponTest() throws Exception {
        LOGGER.debug("deleteCouponTest()");
        EasyMock.expect(mockCouponMapper.deleteCoupon(1)).andReturn(1);
        EasyMock.replay(mockCouponMapper);
        Assert.assertEquals(1, couponService.deleteCoupon(1).intValue());
    }
}

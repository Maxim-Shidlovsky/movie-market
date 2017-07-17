package com.moviemarket.dao;

import com.moviemarket.model.Coupon;
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
 * Created by Maxim on 15.7.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-mybatis-spring.xml"})
@Transactional
public class CouponMapperTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CouponMapper couponMapper;


    private static final int COUPONS_LENGTH = 3;
    private static final Integer TEST_ID = 3;
    private static final String TEST_CODE = "333";
    private static final Coupon testCoupon = new Coupon(1, "123",
            20D, new Date(117, 0, 1));

    @Test
    public void getAllCouponsTest() throws Exception {
        LOGGER.debug("getAllCouponsTest()");
        List<Coupon> coupons = couponMapper.getAllCoupons();

        Assert.assertNotNull(coupons);
        Assert.assertEquals(COUPONS_LENGTH, coupons.size());
        Assert.assertEquals(testCoupon, coupons.get(0));
    }

    @Test
    public void getCouponByIdTest() throws Exception {
        LOGGER.debug("getCouponByIdTest()");
        Coupon coupon = couponMapper.getCouponById(TEST_ID);
        Assert.assertNotNull(coupon);
        Assert.assertEquals(TEST_CODE, coupon.getCode());
    }

    @Test
    public void getCouponByCodeTest() throws Exception {
        LOGGER.debug("getCouponByCodeTest()");
        Coupon coupon = couponMapper.getCouponByCode(TEST_CODE);
        Assert.assertNotNull(coupon);
        Assert.assertTrue(coupon.getCouponId() == TEST_ID);
    }

    @Test
    public void addCouponTest() throws Exception {
        LOGGER.debug("addCouponTest()");

        String testCode = "testCode";
        Double testDiscount = 30D;
        Date testReceivingDate = new Date(117, 1, 1);
        Coupon newCoupon = new Coupon(4, testCode, testDiscount, testReceivingDate);
        couponMapper.addCoupon(newCoupon);

        Coupon addedCoupon = couponMapper.getCouponById(4);
        Assert.assertNotNull(addedCoupon);
        Assert.assertEquals(testCode, addedCoupon.getCode());
        Assert.assertEquals(testDiscount, addedCoupon.getDiscount());
        Assert.assertEquals(testReceivingDate, addedCoupon.getReceivingDate());

        List<Coupon> coupons = couponMapper.getAllCoupons();
        int quantityAfterAdding = coupons.size();
        Assert.assertEquals(COUPONS_LENGTH + 1, quantityAfterAdding);
    }

    @Test
    public void updateCouponTest() throws Exception {
        LOGGER.debug("updateCouponTest()");
        Coupon coupon = new Coupon(TEST_ID, "newCode", 40D, new Date(117, 2, 2));

        couponMapper.updateCoupon(coupon);
        Coupon updatedCoupon = couponMapper.getCouponById(TEST_ID);
        Assert.assertNotNull(updatedCoupon);
        Assert.assertEquals(coupon.getCouponId(), updatedCoupon.getCouponId());
        Assert.assertEquals(coupon.getCode(), updatedCoupon.getCode());
        Assert.assertEquals(coupon.getDiscount(), updatedCoupon.getDiscount());
        Assert.assertEquals(coupon.getReceivingDate(), updatedCoupon.getReceivingDate());
    }

    @Test
    public void deleteCouponTest() throws Exception {
        LOGGER.debug("deleteCouponTest()");
        couponMapper.deleteCoupon(1);
        int quantityAfterDeleting = couponMapper.getAllCoupons().size();
        Assert.assertEquals(COUPONS_LENGTH - 1, quantityAfterDeleting);
    }
}

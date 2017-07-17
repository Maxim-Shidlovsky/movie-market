package com.moviemarket.service;

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
@ContextConfiguration(locations = {"classpath*:test-service-spring.xml"})
@Transactional
public class CouponServiceTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CouponService couponService;


    private static final int COUPONS_LENGTH = 3;
    private static final Integer TEST_ID = 3;
    private static final String TEST_CODE = "333";
    private static final Coupon testCoupon = new Coupon(1, "123",
            20D, new Date(117, 0, 1));

    @Test
    public void getAllCouponsTest() throws Exception {
        LOGGER.debug("getAllCouponsTest()");
        List<Coupon> coupons = couponService.getAllCoupons();

        Assert.assertNotNull(coupons);
        Assert.assertEquals(COUPONS_LENGTH, coupons.size());
        Assert.assertEquals(testCoupon, coupons.get(0));
    }

    @Test
    public void getCouponByIdTest() throws Exception {
        LOGGER.debug("getCouponByIdTest()");
        Coupon coupon = couponService.getCouponById(TEST_ID);
        Assert.assertNotNull(coupon);
        Assert.assertEquals(TEST_CODE, coupon.getCode());
    }

    @Test
    public void getCouponByCodeTest() throws Exception {
        LOGGER.debug("getCouponByCodeTest()");
        Coupon coupon = couponService.getCouponByCode(TEST_CODE);
        Assert.assertNotNull(coupon);
        Assert.assertTrue(coupon.getCouponId() == TEST_ID);
    }

    @Test
    public void addCouponTest() throws Exception {
        LOGGER.debug("addCouponTest()");

        String testCode = "testCode";
        Double testDiscount = 30D;
        Date testReceivingDate = new Date(117, 1, 1);
        Coupon newCoupon = new Coupon(null, testCode, testDiscount, testReceivingDate);
        couponService.addCoupon(newCoupon);

        Coupon addedCoupon = couponService.getCouponById(COUPONS_LENGTH + 1);
        Assert.assertNotNull(addedCoupon);
        Assert.assertEquals(testCode, addedCoupon.getCode());
        Assert.assertEquals(testDiscount, addedCoupon.getDiscount());
        Assert.assertEquals(testReceivingDate, addedCoupon.getReceivingDate());

        List<Coupon> coupons = couponService.getAllCoupons();
        int quantityAfterAdding = coupons.size();
        Assert.assertEquals(COUPONS_LENGTH + 1, quantityAfterAdding);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addCouponWithNotNullIdTest() throws Exception {
        LOGGER.debug("addCouponWithNotNullIdTest()");
        Coupon newCoupon = new Coupon(56, "122344", 1D, new Date());
        couponService.addCoupon(newCoupon);
    }

    @Test
    public void updateCouponTest() throws Exception {
        LOGGER.debug("updateCouponTest()");
        Coupon coupon = new Coupon(TEST_ID, "newCode", 40D, new Date(117, 2, 2));

        couponService.updateCoupon(coupon);
        Coupon updatedCoupon = couponService.getCouponById(TEST_ID);
        Assert.assertNotNull(updatedCoupon);
        Assert.assertEquals(coupon.getCouponId(), updatedCoupon.getCouponId());
        Assert.assertEquals(coupon.getCode(), updatedCoupon.getCode());
        Assert.assertEquals(coupon.getDiscount(), updatedCoupon.getDiscount());
        Assert.assertEquals(coupon.getReceivingDate(), updatedCoupon.getReceivingDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateCouponWithNullIdTest() throws Exception {
        LOGGER.debug("updateCouponWithNullIdTest()");
        Coupon coupon = new Coupon(null, "122344", 1D, new Date());
        couponService.updateCoupon(coupon);
    }

    @Test
    public void deleteCouponTest() throws Exception {
        LOGGER.debug("deleteCouponTest()");
        couponService.deleteCoupon(1);
        int quantityAfterDeleting = couponService.getAllCoupons().size();
        Assert.assertEquals(COUPONS_LENGTH - 1, quantityAfterDeleting);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteCouponByNullIdTest() throws Exception {
        LOGGER.debug("deleteCouponByNullIdTest()");
        couponService.deleteCoupon(null);
    }
}

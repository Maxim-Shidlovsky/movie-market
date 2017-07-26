package com.moviemarket.service;

import com.moviemarket.dao.CouponMapper;
import com.moviemarket.model.Coupon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by Maxim on 15.7.17.
 */
public class CouponServiceImpl implements CouponService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CouponMapper couponMapper;

    public void setCouponMapper(CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
    }


    @Override
    public List<Coupon> getAllCoupons() throws DataAccessException {
        LOGGER.debug("getAllCoupons()");
        return couponMapper.getAllCoupons();
    }

    @Override
    public Coupon getCouponById(Integer couponId) throws DataAccessException {
        LOGGER.debug("getCouponById({})", couponId);
        Assert.notNull(couponId, "Coupon's ID mustn't be null.");

        return couponMapper.getCouponById(couponId);
    }

    @Override
    public Coupon getCouponByCode(String code) throws DataAccessException {
        LOGGER.debug("getCouponByCode({})", code);
        Assert.notNull(code, "Coupon's code mustn't be null.");
        Assert.hasText(code, "Coupon's code must have text.");

        return couponMapper.getCouponByCode(code);
    }

    @Override
    public Integer addCoupon(Coupon coupon) throws DataAccessException {
        LOGGER.debug("addCoupon({})", coupon);
        Assert.notNull(coupon, "Coupon mustn't be null.");
        Assert.isNull(coupon.getCouponId(), "Coupon's ID must be null.");
        Assert.notNull(coupon.getCode(), "Coupon's code mustn't be null.");
        Assert.hasText(coupon.getCode(), "Coupon's code must have text.");
        Assert.notNull(coupon.getDiscount(), "Coupon's discount value mustn't be null.");
        Assert.notNull(coupon.getReceivingDate(), "Coupon's receiving date mustn't be null.");

        return couponMapper.addCoupon(coupon);
    }

    @Override
    public Integer updateCoupon(Coupon coupon) throws DataAccessException {
        LOGGER.debug("updateCoupon({})", coupon);
        Assert.notNull(coupon, "Coupon mustn't be null.");
        Assert.notNull(coupon.getCouponId(), "Coupon's ID mustn't be null.");
        Assert.notNull(coupon.getCode(), "Coupon's code mustn't be null.");
        Assert.hasText(coupon.getCode(), "Coupon's code must have text.");
        Assert.notNull(coupon.getDiscount(), "Coupon's discount value mustn't be null.");
        Assert.notNull(coupon.getReceivingDate(), "Coupon's receiving date mustn't be null.");

        return couponMapper.updateCoupon(coupon);
    }

    @Override
    public Integer deleteCoupon(Integer couponId) throws DataAccessException {
        LOGGER.debug("deleteCoupon({})", couponId);
        Assert.notNull(couponId, "Coupon's ID mustn't be null.");

        return couponMapper.deleteCoupon(couponId);
    }
}

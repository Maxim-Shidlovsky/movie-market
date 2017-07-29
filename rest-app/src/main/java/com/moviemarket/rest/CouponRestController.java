package com.moviemarket.rest;

import com.moviemarket.model.Coupon;
import com.moviemarket.service.CouponService;
import com.moviemarket.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Maxim on 15.7.17.
 */

@CrossOrigin
@RestController
@RequestMapping(value = "/coupons")
public class CouponRestController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CouponService couponService;

    @Autowired
    private OrderService orderService;

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({IllegalArgumentException.class})
    public String incorrectDataError() {
        return "{  \"response\" : \"Incorrect Data Error\" }";
    }


    @RequestMapping(value = "/coupon/{code}/client/{username}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void activateCoupon(@PathVariable(name = "code") String code,
          @PathVariable(name = "username") String username) {
        LOGGER.debug("activateCoupon(code=\"{}\", username=\"{}\")", code, username);
        couponService.activateCoupon(code, username);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Coupon> getAllCoupons() {
        LOGGER.debug("getAllCoupons()");
        return  couponService.getAllCoupons();
    }

    @RequestMapping(value = "/coupon/{id}", method = RequestMethod.GET)
    public @ResponseBody Coupon getCouponById(@PathVariable(value = "id") Integer couponId) {
        LOGGER.debug("getCouponById({})", couponId);
        return couponService.getCouponById(couponId);
    }

    @RequestMapping(value = "/coupon/code/{code}", method = RequestMethod.GET)
    public @ResponseBody Coupon getCouponByCode(@PathVariable(value = "code") String code) {
        LOGGER.debug("getCouponByCode({})", code);
        return couponService.getCouponByCode(code);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addCoupon(@RequestBody Coupon coupon) {
        LOGGER.debug("addCoupon({})", coupon);
        coupon.setReceivingDate(new Date());
        couponService.addCoupon(coupon);
    }

    @RequestMapping(value = "/coupon", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateCoupon(@RequestBody Coupon coupon) {
        LOGGER.debug("updateCoupon({})", coupon);
        couponService.updateCoupon(coupon);
    }

    @RequestMapping(value = "/coupon/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCoupon(@PathVariable(value = "id") Integer couponId) {
        LOGGER.debug("deleteCoupon({})", couponId);
        couponService.deleteCoupon(couponId);
    }
}

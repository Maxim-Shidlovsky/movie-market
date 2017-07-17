package com.moviemarket.rest;

import com.moviemarket.model.Coupon;
import com.moviemarket.service.CouponService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Maxim on 15.7.17.
 */

@CrossOrigin
@RestController
public class CouponRestController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CouponService couponService;

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({IllegalArgumentException.class})
    public String incorrectDataError() {
        return "{  \"response\" : \"Incorrect Data Error\" }";
    }


    @RequestMapping(value = "/coupons", method = RequestMethod.GET)
    public @ResponseBody List<Coupon> getAllCoupons() {
        LOGGER.debug("getAllCoupons()");
        return couponService.getAllCoupons();
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

    @RequestMapping(value = "/coupon", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody Integer addCoupon(@RequestBody Coupon coupon) {
        LOGGER.debug("addCoupon({})", coupon);
        return couponService.addCoupon(coupon);
    }

    @RequestMapping(value = "/coupon", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public @ResponseBody Integer updateCoupon(@RequestBody Coupon coupon) {
        LOGGER.debug("updateCoupon({})", coupon);
        return couponService.updateCoupon(coupon);
    }

    @RequestMapping(value = "/coupon/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Integer deleteCoupon(@PathVariable(value = "id") Integer couponId) {
        LOGGER.debug("deleteCoupon({})", couponId);
        return couponService.deleteCoupon(couponId);
    }
}

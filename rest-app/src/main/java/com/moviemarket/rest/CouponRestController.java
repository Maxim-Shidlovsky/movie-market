package com.moviemarket.rest;

import com.moviemarket.model.Coupon;
import com.moviemarket.service.CouponService;
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

@Controller
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
    public String getAllCoupons(Model model) {
        LOGGER.debug("getAllCoupons()");
        model.addAttribute(couponService.getAllCoupons());
        return "coupons";
    }

    @RequestMapping(value = "/coupon/{id}", method = RequestMethod.GET)
    public String getCouponById(@PathVariable(value = "id") Integer couponId,
                   Model model) {
        LOGGER.debug("getCouponById({})", couponId);
        List<Coupon> couponList = new ArrayList<Coupon>();
        couponList.add(couponService.getCouponById(couponId));
        model.addAttribute(couponList);
        return "coupons";
    }

    @RequestMapping(value = "/coupon/code/{code}", method = RequestMethod.GET)
    public String getCouponByCode(@PathVariable(value = "code") String code, Model model) {
        LOGGER.debug("getCouponByCode({})", code);
        List<Coupon> couponList = new ArrayList<Coupon>();
        couponList.add(couponService.getCouponByCode(code));
        model.addAttribute(couponList);
        return "coupons";
    }

    @RequestMapping(value = "/coupon", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public String addCoupon(@RequestParam String code, @RequestParam Double discount, Model model) {
        LOGGER.debug("addCoupon(code={}, discount={})", code, discount);
        Coupon coupon = new Coupon(null, code, discount, new Date());
        couponService.addCoupon(coupon);
        model.addAttribute(couponService.getAllCoupons());
        return "coupons";
    }

    @RequestMapping(value = "/coupon", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public String updateCoupon(@RequestParam Integer couponId, @RequestParam String code,
          @RequestParam Double discount,
          @RequestParam(name = "receivingDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date receivingDate,
          Model model) {
        LOGGER.debug("updateCoupon(couponId={}, code={}, discount={})", couponId, code, discount);
        Coupon coupon = new Coupon(couponId, code, discount, receivingDate);
        couponService.updateCoupon(coupon);
        model.addAttribute(couponService.getAllCoupons());
        return "coupons";
    }

    @RequestMapping(value = "/coupon/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteCoupon(@PathVariable(value = "id") Integer couponId, Model model) {
        LOGGER.debug("deleteCoupon({})", couponId);
        couponService.deleteCoupon(couponId);
        model.addAttribute(couponService.getAllCoupons());
        return "coupons";
    }
}

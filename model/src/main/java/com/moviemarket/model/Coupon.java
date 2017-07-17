package com.moviemarket.model;

import java.util.Date;
import java.util.Objects;

/**
 * Created by Maxim on 14.7.17.
 */
public class Coupon {

    private Integer couponId;
    private String code;
    private Double discount;
    private Date receivingDate;

    public Coupon() { }

    public Coupon(Integer couponId, String code, Double discount, Date receivingDate) {
        this.couponId = couponId;
        this.code = code;
        this.discount = discount;
        this.receivingDate = receivingDate;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Date getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(Date receivingDate) {
        this.receivingDate = receivingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        Coupon coupon = (Coupon) o;
        return Objects.equals(this.couponId, coupon.getCouponId())
                && Objects.equals(this.code, coupon.getCode())
                && Objects.equals(this.discount, coupon.getDiscount())
                && Objects.equals(this.receivingDate, coupon.getReceivingDate());
    }

    @Override
    public int hashCode() {
            return Objects.hash(couponId, code, discount, receivingDate);
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "couponId=" + couponId +
                ", code='" + code + '\'' +
                ", discount=" + discount +
                ", receivingDate=" + receivingDate +
                '}';
    }
}

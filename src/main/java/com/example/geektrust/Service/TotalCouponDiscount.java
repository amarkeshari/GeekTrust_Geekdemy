package com.example.geektrust.Service;

import com.example.geektrust.Constants.CommonConstants;
import com.example.geektrust.Constants.CouponsType;
import com.example.geektrust.Constants.ProMember;
import com.example.geektrust.Constants.ProgrammeType;
import com.example.geektrust.Model.Coupon;

import java.util.List;

public class TotalCouponDiscount {

    private final SubTotal subTotal;

    private final Coupon coupon;

    private CouponsType finalCouponsType;

    private Float totalCouponDiscount;

    private Integer totalProgrammesInCart;

    public CouponsType getFinalCouponsType() {
        return finalCouponsType;
    }

    public Float getTotalCouponDiscount() {
        return totalCouponDiscount;
    }

    public TotalCouponDiscount(SubTotal subTotal, Coupon coupon) {
        this.subTotal = subTotal;
        this.coupon = coupon;
        this.finalCouponsType = CouponsType.NONE;
        this.totalCouponDiscount = (float) CommonConstants.ZERO;
        this.totalProgrammesInCart = CommonConstants.ZERO;
    }

    public void calculateTotalCouponDiscount() {
        this.subTotal.calculateSubTotal();
        List<CouponsType> couponList = this.coupon.getCouponsApplicable();
        List<ProgrammeType> curCart = this.subTotal.getProgrammesCart();
        this.calculateTotalProgrammesInCart(curCart);
        this.calculateDiscountOnGivenCoupon(couponList);
        this.calculateB4G1Discount(curCart);
    }

    public void calculateDiscountOnGivenCoupon(List<CouponsType> couponList) {
        boolean isG20applicable = false, isG5applicable = false;
        for (CouponsType couponsType : couponList) {
            switch (couponsType) {
                case DEAL_G20:
                    isG20applicable = true;
                    break;
                case DEAL_G5:
                    isG5applicable = true;
                    break;
            }
        }
        if (isG20applicable && subTotal.getSubTotal() >= CommonConstants.MIN_PURCHASE_AMOUNT_DEAL_G20) {
            totalCouponDiscount = this.subTotal.getSubTotal() * CouponsType.DEAL_G20.getDiscountPercentage();
            finalCouponsType = CouponsType.DEAL_G20;
        } else if (isG5applicable && totalProgrammesInCart >= CommonConstants.MIN_COURSE_COUNT_DEAL_G5) {
            totalCouponDiscount = this.subTotal.getSubTotal() * CouponsType.DEAL_G5.getDiscountPercentage();
            finalCouponsType = CouponsType.DEAL_G5;
        }
    }

    public void calculateB4G1Discount(List<ProgrammeType> curCart) {
        if (totalProgrammesInCart >= CommonConstants.MIN_COURSE_COUNT_B4G1) {
            for (ProgrammeType programmeType : curCart) {
                if (programmeType.getProgrammeQuantity() > CommonConstants.ZERO) {
                    totalCouponDiscount = programmeType.getProgrammeCost();
                    if(subTotal.getIsProMember().equals(ProMember.YES)) {
                        totalCouponDiscount*=(CommonConstants.ONE-programmeType.getProDiscount());
                    }
                    break;
                }
            }
            finalCouponsType = CouponsType.B4G1;
        }
    }

    public void calculateTotalProgrammesInCart(List<ProgrammeType> curCart) {
        Integer sumProgrammes = CommonConstants.ZERO;
        for (ProgrammeType programmeType : curCart) {
            sumProgrammes += programmeType.getProgrammeQuantity();
        }
        totalProgrammesInCart = sumProgrammes;
    }
}

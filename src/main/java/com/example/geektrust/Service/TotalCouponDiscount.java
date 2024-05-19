package com.example.geektrust.Service;

import com.example.geektrust.Constants.CommonConstants;
import com.example.geektrust.Constants.CouponsType;
import com.example.geektrust.Constants.ProgrammeType;
import com.example.geektrust.Model.Coupon;

import java.util.List;
import java.util.Set;

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
        Set<CouponsType> couponList = this.coupon.getCouponsApplicable();
        List<ProgrammeType> curCart = this.subTotal.getProgrammesCart();
        this.calculateTotalProgrammesInCart(curCart);
        this.calculateDiscount(couponList,curCart);
    }

    public void calculateDiscount(Set <CouponsType> couponList, List<ProgrammeType> curCart) {
        if (totalProgrammesInCart >= CommonConstants.MIN_COURSE_COUNT_B4G1) {
            for (ProgrammeType programmeType : curCart) {
                if (programmeType.getProgrammeQuantity() > CommonConstants.ZERO) {
                    totalCouponDiscount = programmeType.getProgrammeCost();
                    break;
                }
            }
            finalCouponsType = CouponsType.B4G1;
            return;
        }
        if (couponList.contains(CouponsType.DEAL_G20) && totalProgrammesInCart >= CommonConstants.MIN_COURSE_COUNT_DEAL_G5) {
            totalCouponDiscount = this.subTotal.getSubTotal() * CouponsType.DEAL_G5.getDiscountPercentage();
            finalCouponsType = CouponsType.DEAL_G5;
            return;
        }
        if (couponList.contains(CouponsType.DEAL_G5) && subTotal.getSubTotal() >= CommonConstants.MIN_PURCHASE_AMOUNT_DEAL_G20) {
            totalCouponDiscount = this.subTotal.getSubTotal() * CouponsType.DEAL_G20.getDiscountPercentage();
            finalCouponsType = CouponsType.DEAL_G20;
            return;
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

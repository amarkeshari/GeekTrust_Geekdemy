package com.example.geektrust.Service;

import com.example.geektrust.Constants.CommonConstants;
import com.example.geektrust.Constants.CouponsType;
import com.example.geektrust.Model.Coupon;

public class PrintSummary {

    private final TotalProDiscount totalProDiscount;

    private final Coupon coupon;

    private final SubTotal subTotal;

    private final TotalCouponDiscount totalCouponDiscount;

    private Float totalAmount;

    private Float enrollmentFee;

    public PrintSummary(TotalProDiscount totalProDiscount, Coupon coupon) {
        this.totalProDiscount = totalProDiscount;
        this.coupon = coupon;
        this.subTotal = new SubTotal(totalProDiscount);
        this.totalCouponDiscount = new TotalCouponDiscount(subTotal, coupon);
        enrollmentFee = totalAmount = CommonConstants.ZERO_DECIMAL;
    }

    public void printBill() {
        totalCouponDiscount.calculateTotalCouponDiscount();
        System.out.println("SUB_TOTAL " + getTwoDecimalPlaces(subTotal.getSubTotal()));
        System.out.println("COUPON_DISCOUNT " + totalCouponDiscount.getFinalCouponsType() + " " + getTwoDecimalPlaces(totalCouponDiscount.getTotalCouponDiscount()));
        System.out.println("TOTAL_PRO_DISCOUNT " + getTwoDecimalPlaces(totalProDiscount.getProDiscount()));
        System.out.println("PRO_MEMBERSHIP_FEE " + getTwoDecimalPlaces(totalProDiscount.getProMembershipFee()));
        totalAmount = subTotal.getSubTotal() - totalCouponDiscount.getTotalCouponDiscount();
        if (Float.compare(totalAmount, CommonConstants.ENROLLMENT_FEE_CAP) < 0) {
            enrollmentFee = CommonConstants.ENROLLMENT_FEE;
        }
        totalAmount += enrollmentFee;
        System.out.println("ENROLLMENT_FEE " + getTwoDecimalPlaces(enrollmentFee));
        System.out.println("TOTAL " + getTwoDecimalPlaces(totalAmount));
    }

    public String getTwoDecimalPlaces(Float decimal) {
        return String.format("%.02f", decimal);
    }


}

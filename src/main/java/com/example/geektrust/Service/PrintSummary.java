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
        enrollmentFee = totalAmount = 0.00f;
    }

    public void printBill() {
        totalCouponDiscount.calculateTotalCouponDiscount();
        System.out.println("SUB_TOTAL " + subTotal.getSubTotal());
        if (totalCouponDiscount.getFinalCouponsType().equals(CouponsType.DISCOUNT_NONE)) {
            System.out.println("DISCOUNT NONE 0");
        } else {
            System.out.println("COUPON_DISCOUNT " + totalCouponDiscount.getFinalCouponsType() + " " + totalCouponDiscount.getTotalCouponDiscount());
        }
        System.out.println("TOTAL_PRO_DISCOUNT " + totalProDiscount.getProDiscount());
        System.out.println("PRO_MEMBERSHIP_FEE " + totalProDiscount.getProMembershipFee());
        totalAmount = subTotal.getSubTotal() - totalCouponDiscount.getTotalCouponDiscount();
        if (Float.compare(totalAmount, CommonConstants.ENROLLMENT_FEE_CAP) < 0) {
            enrollmentFee = CommonConstants.ENROLLMENT_FEE;
        }
        totalAmount += enrollmentFee;
        System.out.println("ENROLLMENT_FEE " + enrollmentFee);
        System.out.println("TOTAL " + totalAmount);
    }
}

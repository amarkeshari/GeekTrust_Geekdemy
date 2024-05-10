package com.example.geektrust.Model;

import com.example.geektrust.Constants.CouponsType;

import java.util.ArrayList;
import java.util.List;

public class Coupon {

    private final List<CouponsType> couponsApplicable;

    public Coupon() {
        this.couponsApplicable = new ArrayList<>();
    }

    public String addCoupon(String coupon) {
        switch (coupon) {
            case "DEAL_G20":
                couponsApplicable.add(CouponsType.DEAL_G20);
                break;
            case "DEAL_G5":
                couponsApplicable.add(CouponsType.DEAL_G5);
                break;
        }
        return "Coupon added successfully";
    }

    public List<CouponsType> getCouponsApplicable() {
        return this.couponsApplicable;
    }
}

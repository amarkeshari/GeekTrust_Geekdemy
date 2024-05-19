package com.example.geektrust.Model;

import com.example.geektrust.Constants.CouponsType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Coupon {

    private final Set<CouponsType> couponsApplicable;

    public Coupon() {
        this.couponsApplicable = new HashSet<>();
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

    public Set<CouponsType> getCouponsApplicable() {
        return this.couponsApplicable;
    }
}

package com.example.geektrust.Constants;

public enum CouponsType {
    DEAL_G20(0.2f),
    DEAL_G5(0.05f),
    B4G1(0.00f),
    NONE(0.00f);

    private final Float discountPercentage;

    CouponsType(Float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Float getDiscountPercentage() {
        return this.discountPercentage;
    }
}

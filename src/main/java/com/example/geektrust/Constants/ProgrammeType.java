package com.example.geektrust.Constants;

public enum ProgrammeType {
    DIPLOMA(2500.00f, 0, 0.01f),
    CERTIFICATION(3000.00f, 0, 0.02f),
    DEGREE(5000.00f, 0, 0.03f);

    private Float programmeCost;

    private Integer programmeQuantity;

    private final Float proDiscount;

    ProgrammeType(Float programmeCost, int programmeQuantity, Float proDiscount) {
        this.programmeCost = programmeCost;
        this.programmeQuantity = programmeQuantity;
        this.proDiscount = proDiscount;
    }

    public void increaseQuantity(Integer quantity) {
        this.programmeQuantity += quantity;
    }

    public void changeProgrammeCost(Float discount) {
        this.programmeCost-=discount;
    }

    public Float getProDiscount() {
        return this.proDiscount;
    }

    public Float getProgrammeCost() {
        return this.programmeCost;
    }

    public Integer getProgrammeQuantity() {
        return this.programmeQuantity;
    }

}

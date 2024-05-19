package com.example.geektrust.Service;

import com.example.geektrust.Constants.CommonConstants;
import com.example.geektrust.Constants.ProMember;
import com.example.geektrust.Constants.ProgrammeType;

import java.util.List;

public class SubTotal {
    private final TotalProDiscount totalProDiscount;

    private Float subTotal;

    public SubTotal(TotalProDiscount totalProDiscount) {
        this.totalProDiscount = totalProDiscount;
        this.subTotal = CommonConstants.ZERO_DECIMAL;
    }

    public List<ProgrammeType> getProgrammesCart() {
        return this.totalProDiscount.getProgrammesCart();
    }

    public Float getSubTotal() {
        return subTotal;
    }

    public ProMember getIsProMember() {
        return this.totalProDiscount.getIsProMember();
    }

    public void calculateSubTotal() {
        totalProDiscount.calculateProDiscount();
        List<ProgrammeType> curCart = totalProDiscount.getProgrammesCart();
        for (ProgrammeType programmeType : curCart) {
            Float programCost = programmeType.getProgrammeCost();
            Integer courseCount = programmeType.getProgrammeQuantity();
            float curProgramProDiscount = programCost * courseCount;
            subTotal += curProgramProDiscount;
        }
        subTotal -= (totalProDiscount.getProDiscount() - totalProDiscount.getProMembershipFee());
    }
}

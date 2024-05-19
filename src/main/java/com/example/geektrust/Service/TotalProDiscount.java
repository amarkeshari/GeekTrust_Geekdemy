package com.example.geektrust.Service;

import com.example.geektrust.Constants.CommonConstants;
import com.example.geektrust.Constants.ProMember;
import com.example.geektrust.Constants.ProgrammeType;
import com.example.geektrust.Model.Programme;

import java.util.List;

public class TotalProDiscount {

    private final Programme programme;

    private Float proDiscount;

    private ProMember isProMember;

    private Float proMembershipFee;

    public TotalProDiscount(Programme programme) {
        this.programme = programme;
        this.proDiscount = CommonConstants.ZERO_DECIMAL;
        this.proMembershipFee = CommonConstants.ZERO_DECIMAL;
        this.isProMember = ProMember.NO;
    }

    public ProMember getIsProMember() {
        return isProMember;
    }

    public void setIsProMember() {
        this.isProMember = ProMember.YES;
    }

    public List<ProgrammeType> getProgrammesCart() {
        return this.programme.getProgrammesCart();
    }

    public Float getProDiscount() {
        return proDiscount;
    }

    public Float getProMembershipFee() {
        return proMembershipFee;
    }

    public void calculateProDiscount() {
        if (this.getIsProMember().equals(ProMember.YES)) {
            this.proMembershipFee = CommonConstants.PRO_MEMBERSHIP_FEE;
            List<ProgrammeType> curCart = programme.getProgrammesCart();
            for (ProgrammeType programmeType : curCart) {
                Float programCost = programmeType.getProgrammeCost();
                Integer courseCount = programmeType.getProgrammeQuantity();
                Float curProgramProDiscountPercentage = programmeType.getProDiscount();
                float curProgramProDiscount = programCost * courseCount * curProgramProDiscountPercentage;
                programmeType.changeProgrammeCost(curProgramProDiscount);
                proDiscount += curProgramProDiscount;
            }
        }
    }

}

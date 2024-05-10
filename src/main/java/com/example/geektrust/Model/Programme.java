package com.example.geektrust.Model;

import com.example.geektrust.Constants.CommonConstants;
import com.example.geektrust.Constants.ProgrammeType;

import java.util.Arrays;
import java.util.List;

public class Programme {

    private final List<ProgrammeType> programmesCart;

    public Programme() {
        this.programmesCart = Arrays.asList(ProgrammeType.DIPLOMA, ProgrammeType.CERTIFICATION, ProgrammeType.DEGREE);
    }

    public String addProgramme(String programme, Integer programQuantity) {
        switch (programme) {
            case "DIPLOMA":
                programmesCart.get(CommonConstants.ZERO).increaseQuantity(programQuantity);
                break;
            case "CERTIFICATION":
                programmesCart.get(CommonConstants.ONE).increaseQuantity(programQuantity);
                break;
            case "DEGREE":
                programmesCart.get(CommonConstants.TWO).increaseQuantity(programQuantity);
                break;
        }
        return "Program added successfully!!";
    }

    public List<ProgrammeType> getProgrammesCart() {
        return programmesCart;
    }
}

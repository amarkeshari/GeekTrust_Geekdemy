package com.example.geektrust;

import com.example.geektrust.Constants.CommonConstants;
import com.example.geektrust.Model.Coupon;
import com.example.geektrust.Model.Programme;
import com.example.geektrust.Service.PrintSummary;
import com.example.geektrust.Service.TotalProDiscount;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GenerateBill {

    private final Programme programme = new Programme();
    private final Coupon coupon = new Coupon();
    private final TotalProDiscount totalProDiscount = new TotalProDiscount(programme);
    private final PrintSummary printSummary = new PrintSummary(totalProDiscount, coupon);

    public void generateBill(String path) throws FileNotFoundException {
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                String curLine = scanner.nextLine();
                List<String> curList = Arrays.asList(curLine.split("\\s+"));
                switch (curList.get(CommonConstants.ZERO)) {
                    case "ADD_PROGRAMME":
                        programme.addProgramme(curList.get(CommonConstants.ONE), Integer.parseInt(curList.get(CommonConstants.TWO)));
                        break;
                    case "APPLY_COUPON":
                        coupon.addCoupon(curList.get(CommonConstants.ONE));
                        break;
                    case "ADD_PRO_MEMBERSHIP":
                        totalProDiscount.setIsProMember();
                        break;
                    case "PRINT_BILL":
                        printSummary.printBill();
                        break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File Not Found Exception");
        }
    }

}



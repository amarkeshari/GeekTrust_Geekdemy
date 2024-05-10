package com.example.geektrust;


import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        GenerateBill generateBill=new GenerateBill();
        generateBill.generateBill(args[0]);
	}
}

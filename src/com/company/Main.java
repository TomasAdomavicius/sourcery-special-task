package com.company;

import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Main{

    public static void main(String []args){
        printBonusDatesBetween(2010, 2015);
    }

    static void printBonusDatesBetween(int fromYear, int toYear) {
        if(fromYear < 0 || toYear < 0) {
            System.out.println("Invalid parameters, years can not be negative");
            return;
        }
        if(fromYear > toYear) {
            System.out.println("Invalid parameters, second parameter should be greater than first");
            return;
        }

        for(int i=fromYear; i<toYear; i++) {
            String days;
            String months;
            String years = String.valueOf(i);
            String reversedYears = new StringBuilder(years).reverse().toString();

            int yearsLength = reversedYears.length();

            //check if years contains more than 5 digits
            if(i>9999) {
                String extraDigits = years.substring(4, yearsLength);
                String reversedExtraDigits = new StringBuilder(extraDigits).reverse().toString();
                //sequence of digits from 4 to last position should be palindrome
                if(extraDigits != reversedExtraDigits) continue;
            }

            //check if years contains 4 digits
            if(i>999) {
                months = reversedYears.substring(yearsLength - 4, yearsLength - 2);
                days = reversedYears.substring(yearsLength - 2);
                if(isValidDate(years, months, days)) System.out.println(String.join("-", years, months, days));
                continue;
            }

            //check if years contains 3 digits
            if(i>99) {
                days = reversedYears.substring(yearsLength - 2);
                months = "0" + reversedYears.substring(yearsLength - 3, yearsLength - 2);
                if(isValidDate(years, months, days)) System.out.println(String.join("-", years, months, days));
                months = "1" + reversedYears.substring(yearsLength - 3, yearsLength - 2);
                if(isValidDate(years, months, days)) System.out.println(String.join("-", years, months, days));
                continue;
            }

            //check if years contains 2 digits
            if(i>9) {
                days = reversedYears.substring(yearsLength - 2);
                //month can only be November
                months = "11";
                if(isValidDate(years, months, days)) System.out.println(String.join("-", years, months, days));
                continue;
            }

            //years contains 1 digit, so month can be any value
            for(int j=0; j<10; j++) {
                months = "0" + String.valueOf(j);
                days = "0" + years;
                if(isValidDate(years, months, days)) System.out.println(String.join("-", years, months, days));
            }

            for(int j=10; j<13; j++) {
                months = String.valueOf(j);
                days = "1" + years;
                if(isValidDate(years, months, days)) System.out.println(String.join("-", years, months, days));
            }
        }
    }

    static boolean isValidDate(String years, String months, String days) {
        String strDate = String.join("-", years, months, days);
        SimpleDateFormat sdfrmt = new SimpleDateFormat("y".repeat(years.length()) + "-MM-dd");

        sdfrmt.setLenient(false);
        try
        {
            sdfrmt.parse(strDate);
        }
        catch (ParseException e)
        {
            return false;
        }
        return true;
    }
}
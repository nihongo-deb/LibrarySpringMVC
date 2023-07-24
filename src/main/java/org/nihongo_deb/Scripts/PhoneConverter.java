package org.nihongo_deb.Scripts;

import java.util.LinkedList;
import java.util.List;

/**
 * @author KAWAIISHY
 * @project LibrarySpringMVC
 * @created 23.07.2023
 */
public class PhoneConverter {
    public static String convert(String number){
        int numberLength = number.length();

        if (numberLength < 10){
            System.out.println("phone number " + number +  " too short");
            return number;
        }

        String invertedNumber = new StringBuilder(number).reverse().toString();
        StringBuilder formattedNumber = new StringBuilder();
        return formattedNumber
                .append(invertedNumber.substring(0, 2))
                .append("-")
                .append(invertedNumber.substring(2, 4))
                .append("-")
                .append(invertedNumber.substring(4, 7))
                .append(")")
                .append(invertedNumber.substring(7, numberLength))
                .append("(")
                .reverse()
                .toString();
    }
}

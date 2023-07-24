package org.nihongo_deb.Test;

import org.nihongo_deb.Scripts.PhoneConverter;

import java.util.Random;

/**
 * @author KAWAIISHY
 * @project LibrarySpringMVC
 * @created 23.07.2023
 */
public class PhoneConverterManualTest {
    public static void main(String[] args) {
//        System.out.println(PhoneConverter.convert("8005553535"));

        Random random = new Random();
        for (int i = 0; i < 32; i++){
            StringBuilder stringBuilder = new StringBuilder();
            for (int k = 0; k < 10; k++){
                stringBuilder.append(random.nextInt(0, 10));
            }
            String phone = stringBuilder.toString();
            String convertedPhone = PhoneConverter.convert(phone);
            System.out.println(convertedPhone);
        }

    }
}

package org.nihongo_deb.Test;

import org.nihongo_deb.Scripts.LanguageLetterConverter;

/**
 * @author KAWAIISHY
 * @project LibrarySpringMVC
 * @created 23.07.2023
 */
public class LanguageLetterConverterManualTest {
    public static void main(String[] args) {
        String rusString = "Морозов Александр Тимурович";
        String engString = LanguageLetterConverter.russianToEnglish(rusString);

        System.out.println(rusString);
        System.out.println(engString);
    }
}

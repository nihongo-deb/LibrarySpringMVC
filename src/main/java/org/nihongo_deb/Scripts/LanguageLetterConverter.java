package org.nihongo_deb.Scripts;

import java.util.*;

/**
 * @author KAWAIISHY
 * @project LibrarySpringMVC
 * @created 23.07.2023
 */
public class LanguageLetterConverter {
    private static Map<String, String> russianToEnglishMap;

    //russian to english letter map
    static {
        russianToEnglishMap = new HashMap<>();

        //upper case
        russianToEnglishMap.put("А", "A");
        russianToEnglishMap.put("Б", "B");
        russianToEnglishMap.put("В", "V");
        russianToEnglishMap.put("Г", "G");
        russianToEnglishMap.put("Д", "D");
        russianToEnglishMap.put("Е", "E");
        russianToEnglishMap.put("Ё", "YO");
        russianToEnglishMap.put("Ж", "J");
        russianToEnglishMap.put("З", "Z");
        russianToEnglishMap.put("И", "EE");
        russianToEnglishMap.put("Й", "E");
        russianToEnglishMap.put("К", "K");
        russianToEnglishMap.put("Л", "L");
        russianToEnglishMap.put("М", "M");
        russianToEnglishMap.put("Н", "N");
        russianToEnglishMap.put("О", "O");
        russianToEnglishMap.put("П", "P");
        russianToEnglishMap.put("Р", "R");
        russianToEnglishMap.put("С", "S");
        russianToEnglishMap.put("Т", "T");
        russianToEnglishMap.put("У", "U");
        russianToEnglishMap.put("Ф", "F");
        russianToEnglishMap.put("Х", "H");
        russianToEnglishMap.put("Ц", "C");
        russianToEnglishMap.put("Ч", "CH");
        russianToEnglishMap.put("Ш", "SH");
        russianToEnglishMap.put("Щ", "SHI");
        russianToEnglishMap.put("Ъ", "");
        russianToEnglishMap.put("Ы", "I");
        russianToEnglishMap.put("Ь", "");
        russianToEnglishMap.put("Э", "A");
        russianToEnglishMap.put("Ю", "YU");
        russianToEnglishMap.put("Я", "YA");

        //lower case
        russianToEnglishMap.put("а", "a");
        russianToEnglishMap.put("б", "b");
        russianToEnglishMap.put("в", "v");
        russianToEnglishMap.put("г", "g");
        russianToEnglishMap.put("д", "d");
        russianToEnglishMap.put("е", "e");
        russianToEnglishMap.put("ё", "yo");
        russianToEnglishMap.put("ж", "j");
        russianToEnglishMap.put("з", "z");
        russianToEnglishMap.put("и", "ee");
        russianToEnglishMap.put("й", "e");
        russianToEnglishMap.put("к", "k");
        russianToEnglishMap.put("л", "l");
        russianToEnglishMap.put("м", "m");
        russianToEnglishMap.put("н", "n");
        russianToEnglishMap.put("о", "o");
        russianToEnglishMap.put("п", "p");
        russianToEnglishMap.put("р", "r");
        russianToEnglishMap.put("с", "s");
        russianToEnglishMap.put("т", "t");
        russianToEnglishMap.put("у", "u");
        russianToEnglishMap.put("ф", "f");
        russianToEnglishMap.put("х", "h");
        russianToEnglishMap.put("ц", "c");
        russianToEnglishMap.put("ч", "ch");
        russianToEnglishMap.put("ш", "sh");
        russianToEnglishMap.put("щ", "shi");
        russianToEnglishMap.put("ъ", "");
        russianToEnglishMap.put("ы", "i");
        russianToEnglishMap.put("ь", "");
        russianToEnglishMap.put("э", "a");
        russianToEnglishMap.put("ю", "yu");
        russianToEnglishMap.put("я", "ya");
    }


    public static String russianToEnglish(String str){
        char[] chars = str.toCharArray();
        StringBuilder engStr = new StringBuilder();

        for (Character c : chars){
            if (c != ' ')
                engStr.append(russianToEnglishMap.get(c.toString()));
            else
                engStr.append(" ");

        }
        return engStr.toString();
    }
}

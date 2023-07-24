package org.nihongo_deb.Test;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.nihongo_deb.Scripts.LanguageLetterConverter;
import org.nihongo_deb.Scripts.PhoneConverter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author KAWAIISHY
 * @project LibrarySpringMVC
 * @created 23.07.2023
 */
public class App {
    public static void main(String[] args) throws IOException {
        String fileName = "src/main/resources/CSV/readers.csv";
        String[] columns = {"reader_fio", "reader_email", "reader_phone", "reader_birthday"};

        List<String> fio = new ArrayList<>();

        fio.add(LanguageLetterConverter.russianToEnglish("Рыжов Арсений Владимирович"));
        fio.add(LanguageLetterConverter.russianToEnglish("Сергеев Сергей Савельевич"));
        fio.add(LanguageLetterConverter.russianToEnglish("Петрова Мария Мироновна"));
        fio.add(LanguageLetterConverter.russianToEnglish("Леонова Маргарита Дмитриевна"));
        fio.add(LanguageLetterConverter.russianToEnglish("Глухов Кирилл Михайлович"));
        fio.add(LanguageLetterConverter.russianToEnglish("Грибова Вероника Георгиевна"));
        fio.add(LanguageLetterConverter.russianToEnglish("Захаров Роман Артемьевич"));
        fio.add(LanguageLetterConverter.russianToEnglish("Хохлова Зоя Никитична"));
        fio.add(LanguageLetterConverter.russianToEnglish("Петрова Ева Андреевна"));
        fio.add(LanguageLetterConverter.russianToEnglish("Мухина Мира Тимофеевна"));
        fio.add(LanguageLetterConverter.russianToEnglish("Леонова Ярослава Владимировна"));
        fio.add(LanguageLetterConverter.russianToEnglish("Демьянов Юрий Антонович"));
        fio.add(LanguageLetterConverter.russianToEnglish("Федорова Ева Николаевна"));
        fio.add(LanguageLetterConverter.russianToEnglish("Беляев Владислав Русланович"));
        fio.add(LanguageLetterConverter.russianToEnglish("Елисеев Никита Львович"));
        fio.add(LanguageLetterConverter.russianToEnglish("Кулешова Злата Александровна"));
        fio.add(LanguageLetterConverter.russianToEnglish("Попов Андрей Платонович"));
        fio.add(LanguageLetterConverter.russianToEnglish("Зайцев Никита Андреевич"));
        fio.add(LanguageLetterConverter.russianToEnglish("Казакова Елизавета Петровна"));
        fio.add(LanguageLetterConverter.russianToEnglish("Михайлов Александр Егорович"));
        fio.add(LanguageLetterConverter.russianToEnglish("Пантелеева Александра Кирилловна"));
        fio.add(LanguageLetterConverter.russianToEnglish("Беляков Лев Саввич"));
        fio.add(LanguageLetterConverter.russianToEnglish("Белоусов Тимофей Михайлович"));
        fio.add(LanguageLetterConverter.russianToEnglish("Сергеева Александра Егоровна"));
        fio.add(LanguageLetterConverter.russianToEnglish("Крылов Семён Захарович"));
        fio.add(LanguageLetterConverter.russianToEnglish("Скворцов Марк Максимович"));
        fio.add(LanguageLetterConverter.russianToEnglish("Блохина Арина Марковна"));
        fio.add(LanguageLetterConverter.russianToEnglish("Казаков Андрей Маркович"));
        fio.add(LanguageLetterConverter.russianToEnglish("Калинин Фёдор Максимович"));
        fio.add(LanguageLetterConverter.russianToEnglish("Игнатьев Михаил Матвеевич"));
        fio.add(LanguageLetterConverter.russianToEnglish("Кольцов Александр Даниилович"));
        fio.add(LanguageLetterConverter.russianToEnglish("Борисов Илья Егорович"));

        List<String> emails = new ArrayList<>();

        fio.stream().forEach(f -> emails.add(f.replaceAll("\\s+", "-").toLowerCase()+"@smail.som"));

        List<String> phones = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 32; i++){
            StringBuilder stringBuilder = new StringBuilder();
            for (int k = 0; k < 10; k++){
                stringBuilder.append(random.nextInt(0, 10));
            }
            String phone = stringBuilder.toString();
            String convertedPhone = PhoneConverter.convert(phone);

            phones.add(convertedPhone);

            System.out.println(convertedPhone);
        }

        List<String> birthday = new ArrayList<>();
        for (int i = 0; i < 32; i++){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder
                    .append(random.nextInt(1950, 2011))
                    .append('-')
                    .append(random.nextInt(1, 13))
                    .append('-')
                    .append(random.nextInt(1, 29));
            birthday.add(stringBuilder.toString());
        }

        List<String[]> lines = new ArrayList<>();
        lines.add(columns);

        for (int i = 0; i < 32; i++){
            lines.add(new String[]{fio.get(i), emails.get(i), phones.get(i), birthday.get(i)});
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            for (String[] line : lines) {
                writer.writeNext(line);
            }
        }
    }

}

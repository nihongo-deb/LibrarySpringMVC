package org.nihongo_deb.Test;

import com.opencsv.exceptions.CsvException;
import org.nihongo_deb.Scripts.CSVToDatabaseLoader;
import org.nihongo_deb.Scripts.LoaderMapper;
import org.nihongo_deb.Settings.PostgresSQLConnectionData;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author KAWAIISHY
 * @project LibrarySpringMVC
 * @created 23.07.2023
 */
public class CSVToDatabaseManualTestReaders {
    public static void main(String[] args) throws SQLException, IOException, CsvException {
        String SQL = "insert into readers(reader_fio, reader_email, reader_phone, reader_birthday) VALUES (?, ?, ?, ?)";

        LoaderMapper loaderMapper = (PreparedStatement statement, String[] values) -> {
            try {
                statement.setString(1, values[0]);
                statement.setString(2, values[1]);
                statement.setString(3, values[2]);
                statement.setDate(4, Date.valueOf(values[3]));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (NumberFormatException e){

            }
        };

        CSVToDatabaseLoader loader = CSVToDatabaseLoader.builder()
                .databaseUrl(PostgresSQLConnectionData.DB_URL)
                .databaseUsername(PostgresSQLConnectionData.USERNAME)
                .databasePassword(PostgresSQLConnectionData.PASSWORD)
                .throwables(new Class[]{NumberFormatException.class})
                .sql(SQL)
                .mapper(loaderMapper)
                .csv("CSV/readers.csv")
                .build();

        loader.loadInDatabase();
    }
}

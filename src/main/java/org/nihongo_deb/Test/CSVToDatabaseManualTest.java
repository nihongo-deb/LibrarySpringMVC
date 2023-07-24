package org.nihongo_deb.Test;

import com.opencsv.exceptions.CsvException;
import org.nihongo_deb.Scripts.CSVToDatabaseLoader;
import org.nihongo_deb.Scripts.LoaderMapper;
import org.nihongo_deb.Settings.PostgresSQLConnectionData;

import java.io.IOException;
import java.sql.*;

/**
 * @author KAWAIISHY
 * @project LibrarySpringMVC
 * @created 08.07.2023
 */
public class CSVToDatabaseManualTest {
    public static void main(String[] args) throws SQLException, IOException, CsvException {
//        Connection connection = DriverManager.getConnection(
//                PostgresSQLConnectionData.DB_URL,
//                PostgresSQLConnectionData.USERNAME,
//                PostgresSQLConnectionData.PASSWORD
//        );
        String SQL = "insert into books(book_title, book_author, book_publisher, book_publication_year) values (?, ?, ?, ?)";

        LoaderMapper loaderMapper = (PreparedStatement statement, String[] values) -> {
            try {
                statement.setString(1, values[1]);
                statement.setString(2, values[2]);
                statement.setString(3, values[4]);
                statement.setInt(4, Integer.parseInt(values[3]));
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
                .csv("CSV/books.csv")
                .build();

        loader.loadInDatabase(100);
    }
}

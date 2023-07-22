package org.nihongo_deb.Scripts;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author KAWAIISHY
 * @project LibrarySpringMVC
 * @created 05.07.2023
 */
public class CSVToDatabaseLoader {
    private List<String[]> allCSV;
    private String SQL;
    private Connection connection;
    private LoaderMapper mapper;
    private Class[] throwables;

    public CSVToDatabaseLoader(List<String[]> allCSV, String databaseUrl, String databaseUsername, String databasePassword, String SQL, LoaderMapper loaderMapper, Class[] throwables) throws IOException, CsvException, SQLException {
        this.allCSV = allCSV;
        this.connection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword);
        this.SQL = SQL;
        this.mapper = loaderMapper;
        this.throwables = throwables;
    }

    public CSVToDatabaseLoader(String csvFilePath, Connection connection, String SQL, LoaderMapper loaderMapper, Class[] throwables) throws IOException, CsvException {
        this.allCSV = loadCSV(csvFilePath);
        this.connection = connection;
        this.SQL = SQL;
        this.mapper = loaderMapper;
        this.throwables = throwables;
    }

    public CSVToDatabaseLoader(String csvFilePath, String databaseUrl, String databaseUsername, String databasePassword, String SQL, LoaderMapper loaderMapper, Class[] throwables) throws SQLException, IOException, CsvException {
        this.allCSV = loadCSV(csvFilePath);
        this.throwables = throwables;
        this.connection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword);
        this.SQL = SQL;
        this.mapper = loaderMapper;
    }

    public void loadInDatabase(long capacity) throws SQLException {
        double delta = (double) (allCSV.size() - 1) / capacity;
        if(delta < 1){
            System.out.println("capacity is higher then CSV table size");
            return;
        }

        PreparedStatement statement = connection.prepareStatement(SQL);
        for (int i = 1; i < capacity; i++){
            int csvIndex = (int) (i + delta);
            String[] newRaw = removeDoubleQuotes(allCSV.get(csvIndex)[0].split(";"));
            try {
                mapper.map(statement, newRaw);
            } catch (Throwable e){
                for (Class exeptionClass : throwables){
                    if (e.getClass().equals(exeptionClass)){
                        continue;
                    }
                }
            }
            statement.executeUpdate();
        }
    }

    public static List<String[]> loadCSV(String fileNameInResourcesPath) throws IOException, CsvException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileNameInResourcesPath);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        try (CSVReader reader = new CSVReader(inputStreamReader)) {
            return reader.readAll();
        }
    }

    public static String[] removeDoubleQuotes(String[] strs){
        if (strs == null || strs.length == 0){
            return null;
        }

        String[] strsToReturn = new String[strs.length];
        for (int i = 0; i < strs.length; i++){
            strsToReturn[i] = strs[i].replace("\"", "");
        }

        return strsToReturn;
    }

    public static CSVToDatabaseLoaderBuilder builder(){
        return new CSVToDatabaseLoaderBuilder();
    }

    public static class CSVToDatabaseLoaderBuilder {
        private List<String[]> csvList = null;
        private String sql = "";
        private String databaseUrl;
        private String databaseUsername;
        private String databasePassword;
        private LoaderMapper mapper = null;
        private Class[] throwables = null;



        public CSVToDatabaseLoaderBuilder csv(String path) throws IOException, CsvException {
            csvList = loadCSV(path);
            return this;
        }

        public CSVToDatabaseLoaderBuilder sql(String sql){
            this.sql = sql;
            return this;
        }

        public CSVToDatabaseLoaderBuilder databaseUrl(String databaseUrl){
            this.databaseUrl = databaseUrl;
            return this;
        }

        public CSVToDatabaseLoaderBuilder databaseUsername(String databaseUsername){
            this.databaseUsername = databaseUsername;
            return this;
        }

        public CSVToDatabaseLoaderBuilder databasePassword(String databasePassword){
            this.databasePassword = databasePassword;
            return this;
        }

        public CSVToDatabaseLoaderBuilder mapper(LoaderMapper loaderMapper){
            this.mapper = loaderMapper;
            return this;
        }

        public CSVToDatabaseLoaderBuilder throwables(Class[] throwables){
            this.throwables = throwables;
            return this;
        }

        public CSVToDatabaseLoader build() throws IOException, CsvException, SQLException {
            return new CSVToDatabaseLoader(
                    this.csvList,
                    this.databaseUrl,
                    this.databaseUsername,
                    this.databasePassword,
                    this.sql,
                    this.mapper,
                    this.throwables
            );
        }
    }
}

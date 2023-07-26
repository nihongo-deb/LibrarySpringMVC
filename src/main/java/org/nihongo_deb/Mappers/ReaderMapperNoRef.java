package org.nihongo_deb.Mappers;

import org.nihongo_deb.Models.Reader;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author KAWAIISHY
 * @project LibrarySpringMVC
 * @created 24.07.2023
 */
@Component
public class ReaderMapperNoRef implements RowMapper<Reader> {
    @Override
    public Reader mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reader reader = Reader.builder()
                .id(UUID.fromString(rs.getString("reader_id")))
                .fio(rs.getString("reader_fio"))
                .email(rs.getString("reader_email"))
                .phone(rs.getString("reader_phone"))
                .birthday(String.valueOf(rs.getDate("reader_birthday")))
                .build();

        return reader;
    }
}

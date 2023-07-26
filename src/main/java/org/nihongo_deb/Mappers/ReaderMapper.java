package org.nihongo_deb.Mappers;

import org.nihongo_deb.Models.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author KAWAIISHY
 * @project LibrarySpringMVC
 * @created 24.07.2023
 */
@Component
public class ReaderMapper implements RowMapper<Reader> {
    private BookMapper bookMapper;
    private JdbcTemplate jdbcTemplate;


    @Autowired
    public ReaderMapper(BookMapper bookMapper, JdbcTemplate jdbcTemplate) {
        this.bookMapper = bookMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Reader mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reader reader = Reader.builder()
                .id(UUID.fromString(rs.getString("reader_id")))
                .fio(rs.getString("reader_fio"))
                .email(rs.getString("reader_email"))
                .phone(rs.getString("reader_phone"))
                .birthday(String.valueOf(rs.getDate("reader_birthday")))
                .build();

        String sql = "select * from books where reader_id=?";
        reader.setBooks(jdbcTemplate.query(sql, new Object[]{reader.getId()}, bookMapper));
        return reader;
    }
}

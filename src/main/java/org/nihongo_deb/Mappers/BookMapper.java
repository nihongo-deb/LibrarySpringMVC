package org.nihongo_deb.Mappers;

import org.nihongo_deb.Models.Book;
import org.nihongo_deb.Models.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
public class BookMapper implements RowMapper<Book> {
    private JdbcTemplate jdbcTemplate;
    private ReaderMapperNoRef readerMapper;

    @Autowired
    public BookMapper(JdbcTemplate jdbcTemplate, ReaderMapperNoRef readerMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.readerMapper = readerMapper;
    }

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = Book.builder()
                .id(UUID.fromString(rs.getString("book_id")))
                .title(rs.getString("book_title"))
                .author(rs.getString("book_author"))
                .publisher(rs.getString("book_publisher"))
                .publicationYear(rs.getInt("book_publication_year"))
                .build();

        String sql = "select * from readers where reader_id = (select reader_id from books where readers.reader_id=books.reader_id AND book_id=?);";
        Reader reader = jdbcTemplate.query(
                sql,
                new Object[]{book.getId()},
                readerMapper).stream().findAny().orElse(null);

        book.setReader(reader);
        return book;
    }
}

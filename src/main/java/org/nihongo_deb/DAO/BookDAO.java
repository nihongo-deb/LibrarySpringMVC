package org.nihongo_deb.DAO;

import org.nihongo_deb.Mappers.BookMapper;
import org.nihongo_deb.Models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author KAWAIISHY
 * @project LibrarySpringMVC
 * @created 24.07.2023
 */
@Component
public class BookDAO implements DAO <Book, UUID>{
    private JdbcTemplate jdbcTemplate;
    private BookMapper bookMapper;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate, BookMapper bookMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<Book> getAll() {
        String sql = "select * from books order by book_title";
//        jdbcTemplate.query(sql, bookMapper).forEach(System.out::println);
        return jdbcTemplate.query(sql, bookMapper);
    }

    @Override
    public Optional<Book> getById(UUID id) {
        String sql = "select * from books where book_id=?";
        Optional<Book> book = jdbcTemplate.query(sql, new Object[]{id}, bookMapper).stream().findAny();

        return book;
    }

    @Override
    public void addOne(Book entity) {
        Book book = entity;
        String sql = """
                        insert into books(book_title, book_author, book_publisher, book_publication_year)
                        VALUES(?,?,?,?)
                     """;
        jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPublicationYear());
    }

    @Override
    public void update(UUID id, Book entity) {
        Book book = entity;

        String sql = """
                        update books set
                        book_title=?,
                        book_author=?,
                        book_publisher=?,
                        book_publication_year=?,
                        reader_id=?
                        where book_id=?
                     """;
        jdbcTemplate.update(
                sql,
                entity.getTitle(),
                entity.getAuthor(),
                entity.getPublisher(),
                entity.getPublicationYear(),
                entity.getReader() == null ? null : entity.getReader().getReaderId(),
                id
        );
    }

    @Override
    public void delete(UUID id) {
        String sql = "delete from books where book_id=?";
        jdbcTemplate.update(sql, id);
    }
}

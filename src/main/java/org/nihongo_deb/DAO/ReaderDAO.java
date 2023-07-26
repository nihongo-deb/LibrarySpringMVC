package org.nihongo_deb.DAO;

import org.nihongo_deb.Mappers.ReaderMapper;
import org.nihongo_deb.Models.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author KAWAIISHY
 * @project LibrarySpringMVC
 * @created 24.07.2023
 */
@Component
public class ReaderDAO implements DAO<Reader, UUID>{
    private JdbcTemplate jdbcTemplate;
    private ReaderMapper readerMapper;

    @Autowired
    public ReaderDAO(JdbcTemplate jdbcTemplate, ReaderMapper readerMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.readerMapper = readerMapper;
    }

    @Override
    public List<Reader> getAll() {
        String sql = "select * from readers order by reader_fio";
        return jdbcTemplate.query(sql, readerMapper);
    }

    @Override
    public Optional<Reader> getById(UUID id) {
        String sql = "select * from readers where reader_id=?";
        Optional<Reader> reader = jdbcTemplate.query(sql, new Object[]{id}, readerMapper).stream().findAny();
        return reader;
    }

    @Override
    public void addOne(Reader entity) {
        String sql = """
                insert into readers (reader_fio, reader_email, reader_phone, reader_birthday) values (?,?,?,?);
                """;

        jdbcTemplate.update(sql, entity.getFio(), entity.getEmail(), entity.getPhone(), Date.valueOf(entity.getBirthday()));
    }

    @Override
    public void update(UUID id, Reader entity) {
        String sql = """
                update readers set 
                reader_fio=?, 
                reader_email=?, 
                reader_phone=?, 
                reader_birthday=?
                where reader_id=?;
                """;

        jdbcTemplate.update(sql, entity.getFio(), entity.getEmail(), entity.getPhone(), Date.valueOf(entity.getBirthday()), id);
    }

    @Override
    public void delete(UUID id) {
        String sql = "delete from readers where reader_id=?";
        jdbcTemplate.update(sql, id);
    }
}

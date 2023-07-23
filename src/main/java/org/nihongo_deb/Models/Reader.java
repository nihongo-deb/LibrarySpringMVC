package org.nihongo_deb.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author KAWAIISHY
 * @project LibrarySpringMVC
 * @created 05.07.2023
 */
public class Reader {
    private UUID id;
    private String fio;
    private String email;
    private String phone;
    private String birthday;
    private List<Book> books;

    public Reader(String fio, String email, String phone, String birthday, List<Book> books) {
        this.fio = fio;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.books = books;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return id.equals(reader.id) && fio.equals(reader.fio) && email.equals(reader.email) && phone.equals(reader.phone) && birthday.equals(reader.birthday) && books.equals(reader.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, email, phone, birthday, books);
    }

    public static ReaderBuilder builder(){
        return new ReaderBuilder();
    }

    public static class ReaderBuilder {
        private String fio = "LastName FirstName Patronymic";
        private String email = "null@null.null";
        private String phone = "+00000000000";
        private String birthday = "01.01.2500";
        private List<Book> books = new ArrayList<>();

        public ReaderBuilder fio(String fio){
            this.fio = fio;
            return this;
        }

        public ReaderBuilder email(String email){
            this.email = email;
            return this;
        }

        public ReaderBuilder phone(String phone){
            this.phone = phone;
            return this;
        }

        public ReaderBuilder birthday(String birthday){
            this.birthday = birthday;
            return this;
        }

        public ReaderBuilder books (List<Book> books){
            this.books = books;
            return this;
        }

        public Reader build(){
            return new Reader(fio, email, phone, birthday, books);
        }
    }
}

package org.nihongo_deb.Models;

/**
 * @author KAWAIISHY
 * @project LibrarySpringMVC
 * @created 05.07.2023
 */
public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private String publisher;
    private Reader reader;

    public Book() {
    }

    public Book(String title, String author, int publicationYear, String publisher, Reader reader) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.reader = reader;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", publisher='" + publisher + '\'' +
                ", reader=" + reader +
                '}';
    }

    public static BookBuilder builder(){
        return new BookBuilder();
    }

    public static class BookBuilder {
        private String title = "no title";
        private String author = "no author";
        private int publicationYear = -1;
        private String publisher = "no publisher";
        private Reader reader = null;

        public BookBuilder title(String title){
            this.title = title;
            return this;
        }

        public BookBuilder author(String author){
            this.author = author;
            return this;
        }

        public BookBuilder publicationYear(int publicationYear){
            this.publicationYear = publicationYear;
            return this;
        }

        public BookBuilder publisher(String publisher){
            this.publisher = publisher;
            return this;
        }

        public BookBuilder reader(Reader reader){
            this.reader = reader;
            return this;
        }

        public Book build(){
            return new Book(title, author, publicationYear, publisher, reader);
        }
    }
}

package org.nihongo_deb.Controllers;

import org.nihongo_deb.DAO.BookDAO;
import org.nihongo_deb.Models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author KAWAIISHY
 * @project LibrarySpringMVC
 * @created 24.07.2023
 */
@Controller
@RequestMapping("library/books")
public class BookController {
    private BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String showAllBooks(Model model){
        model.addAttribute("books", bookDAO.getAll());

        return "BookController/show-all";
    }

    @GetMapping("/{id}")
    public String showOne(@PathVariable("id") String id, Model model){
        Book book;
        if (bookDAO.getById(UUID.fromString(id)).isPresent()) {
            book = bookDAO.getById(UUID.fromString(id)).get();
            model.addAttribute("book", book);
            System.out.println(book);
            return "BookController/show-book";
        }
        else {
            String error = "no such book exists";
            model.addAttribute("error", error);
            return "error-page";
        }
    }

    @GetMapping("/new")
    public String createNewBook(Model model){
        Book book = Book.builder().build();
        model.addAttribute("newBook", book);
        return "BookController/new-book";
    }

    @PostMapping()
    public String addNewBook(@ModelAttribute("newBook") Book book){
        bookDAO.addOne(book);
        return "redirect:/library/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") String id){
        bookDAO.delete(UUID.fromString(id));
        return "redirect:/library/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") String id){
        Book book = bookDAO.getById(UUID.fromString(id)).get();
        model.addAttribute("book", book);
        System.out.println(bookDAO.getById(UUID.fromString(id)));
        return "BookController/edit-book";
    }

    @PatchMapping("/{id}")
    public String updateSimpleExample(@PathVariable("id") String id, @ModelAttribute("book") Book book){
        bookDAO.update(UUID.fromString(id), book);
        return "redirect:/library/books";
    }
}

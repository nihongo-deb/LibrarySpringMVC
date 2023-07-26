package org.nihongo_deb.Controllers;

import org.nihongo_deb.DAO.ReaderDAO;
import org.nihongo_deb.Models.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author KAWAIISHY
 * @project LibrarySpringMVC
 * @created 25.07.2023
 */
@Controller
@RequestMapping("library/readers")
public class ReaderController {
    private ReaderDAO readerDAO;

    @Autowired
    public ReaderController(ReaderDAO readerDAO) {
        this.readerDAO = readerDAO;
    }

    @GetMapping()
    public String showAllReaders(Model model){
        List<Reader> readers = readerDAO.getAll();
        System.out.println(readers);
        model.addAttribute("readers", readers);
        return "ReaderController/show-all";
    }

    @GetMapping("/{id}")
    public String shoeOne(@PathVariable("id") String id, Model model){
        Optional<Reader> reader = readerDAO.getById(UUID.fromString(id));
        if (reader.isPresent()){
            model.addAttribute("reader", reader.get());
            return "ReaderController/show-reader";
        }

        else {
            model.addAttribute("error", "no such reader exists");
            return "error-page";
        }
    }

    @GetMapping("/new")
    public String newReader(Model model){
        Reader reader = Reader.builder().build();
        model.addAttribute("reader", reader);
        return "ReaderController/new-reader";
    }

    @PostMapping()
    public String addNewReader(@ModelAttribute("reader") Reader reader){
        readerDAO.addOne(reader);
        return "redirect:/library/readers";
    }

    @DeleteMapping("/{id}")
    public String deleteReader(@PathVariable("id") String id){
        readerDAO.delete(UUID.fromString(id));
        return "redirect:/library/readers";
    }

    @GetMapping("/{id}/edit")
    public String editReader(Model model, @PathVariable("id") String id){
        Optional<Reader> reader = readerDAO.getById(UUID.fromString(id));
        if (reader.isPresent()){
            model.addAttribute("reader", reader.get());

            System.out.println(reader.get().getBirthday());
            System.out.println(Date.valueOf(reader.get().getBirthday()));
            return "ReaderController/edit-reader";
        }
        else {
            model.addAttribute("error", "no such reader exists");
            return "error-page";
        }
    }

    @PatchMapping("/{id}")
    public String updateReader(@PathVariable("id") String id, @ModelAttribute("reader") Reader reader){
        readerDAO.update(UUID.fromString(id), reader);
        return "redirect:/library/readers";
    }
}

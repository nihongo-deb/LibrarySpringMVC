package org.nihongo_deb.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author KAWAIISHY
 * @project LibrarySpringMVC
 * @created 02.07.2023
 */
@Controller
@RequestMapping("/")
public class FirstPageController {
    @GetMapping()
    public String showFirstPage(){
        return "FirstPageController/first-page";
    }
}

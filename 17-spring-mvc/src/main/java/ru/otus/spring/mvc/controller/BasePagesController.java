package ru.otus.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasePagesController {

    @GetMapping("/")
    public String listPage(Model model) {
        model.addAttribute("keywords", "list books");
        return "list-books";
    }
}

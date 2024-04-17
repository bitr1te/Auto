package com.project.auto.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class mainController {

    @GetMapping("/")
    public String getMain(Model model) {
        model.addAttribute("address", "Краснодар, ул.Красная 135");
        model.addAttribute("owner", "Дмитрий Попков");
        return "index";
    }

    @GetMapping("/about")
    public String getAbout(Model model) {

        return "about";
    }
}

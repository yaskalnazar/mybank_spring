package ua.testing.demo_jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/")
    public String mainPage(){
        return "login";
    }

    @RequestMapping("/all_users")
    public String userPage(){
        return "all_users";
    }

    @RequestMapping("/form")
    public String regForm(){
        return "reg_form";
    }
}

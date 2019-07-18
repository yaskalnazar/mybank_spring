package ua.testing.demo_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.testing.demo_jpa.service.UserService;


@Controller
@PreAuthorize("hasAuthority('ROLE_USER')")
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/home")
    public String home(Model model){
        model.addAttribute("user", userService.getCurrentUser());
        return "user_home";
    }

    @RequestMapping(value = "/account")
    public String account(Model model){
        model.addAttribute("user", userService.getCurrentUser());
        return "user_home";
    }
}

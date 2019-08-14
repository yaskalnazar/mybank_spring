package ua.yaskal.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class PageController {

    @RequestMapping("/")
    public String helloPage(){
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        } else {
            return "redirect:/home";
        }
    }

    @RequestMapping("/form")
    public String regForm(){
        return "guest/regForm";
    }

    @RequestMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);

        return "login";
    }



}
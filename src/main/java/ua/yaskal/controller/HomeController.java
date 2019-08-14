/*
package ua.yaskal.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ua.yaskal.model.entity.User;
import ua.yaskal.model.service.UserService;

@Slf4j
@Controller
@PreAuthorize("hasAuthority('USER') or hasAuthority('AMDIN')")
public class HomeController {
    @Autowired
    private UserService userService;
    @GetMapping("/home")
    public String home(){
        User user = userService.getCurrentUser();
        if (user.getAuthorities().contains(User.Role.ADMIN)){
            return "redirect:/admin/home";
        } else if (user.getAuthorities().contains(User.Role.USER)){
            return "redirect:/user/home";
        } else {
            return "redirect:/logout";
        }
    }
}
*/

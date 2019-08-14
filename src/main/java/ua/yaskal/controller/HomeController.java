package ua.yaskal.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ua.yaskal.controller.handler.service.UserService;
import ua.yaskal.model.entity.User;

@Slf4j
@Controller
@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
public class HomeController {
    @Autowired
    private UserService userService;
    @GetMapping("/home")
    public String home(){
        User user = userService.getCurrentUser();
        if (user.getAuthorities().contains(User.RoleType.ROLE_ADMIN)){
            return "redirect:/admin/home";
        } else if (user.getAuthorities().contains(User.RoleType.ROLE_USER)){
            return "redirect:/user/home";
        } else {
            return "redirect:/logout";
        }
    }
}

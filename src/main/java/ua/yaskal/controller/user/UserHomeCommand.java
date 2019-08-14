package ua.yaskal.controller.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.yaskal.controller.JspPath;
import ua.yaskal.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * This command used to get home page for USER.
 *
 * @author Nazar Yaskal
 */
@Controller
@PreAuthorize("hasAuthority('USER')")
@RequestMapping(value = "/api/user")
public class UserHomeCommand {
    private UserService userService;

    public UserHomeCommand(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/home")
    public String execute(HttpServletRequest request) {
        request.setAttribute("user", userService.getCurrentUser());
        return JspPath.USER_HOME;
    }
}

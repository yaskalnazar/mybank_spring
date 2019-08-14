package ua.yaskal.controller.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.yaskal.controller.JspPath;

import javax.servlet.http.HttpServletRequest;

/**
 * This command used to get home page for USER.
 *
 * @author Nazar Yaskal
 */
@RestController
@PreAuthorize("hasAuthority('USER')")
@RequestMapping(value = "/mybank/api/user")
public class UserHomeCommand {

    @PostMapping(value = "/home")
    public String execute(HttpServletRequest request) {
        return JspPath.USER_HOME;
    }
}

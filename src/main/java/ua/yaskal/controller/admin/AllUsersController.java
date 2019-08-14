package ua.yaskal.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.yaskal.controller.JspPath;
import ua.yaskal.model.service.DepositService;
import ua.yaskal.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * This command used to get all_credits page for ADMIN.
 * Required @param currentPage if pagination used;
 *
 * @author Nazar Yaskal
 */
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = "/api/admin")
public class AllUsersController {
    private UserService userService;

    public AllUsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/all_users")
    public String execute(HttpServletRequest request) {
        request.setAttribute("users", userService.getAll());
        return JspPath.ADMIN_ALL_USERS;
    }


}

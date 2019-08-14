package ua.yaskal.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.yaskal.controller.JspPath;

/**
 * This command used to get home page for ADMIN.
 *
 * @author Nazar Yaskal
 */
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = "/api/admin")
public class AdminHomeController {

    @GetMapping(value = "/home")
    public String execute() {
        return JspPath.ADMIN_HOME;
    }
}

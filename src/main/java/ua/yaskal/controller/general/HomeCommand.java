package ua.yaskal.controller.general;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.yaskal.model.entity.User;
import ua.yaskal.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * This command used to redirect to the appropriate page depending on the role of user.
 *
 * @author Nazar Yaskal
 */
@Controller
@RequestMapping(value = "/api")
public class HomeCommand {
    private final static Logger logger = Logger.getLogger(HomeCommand.class);
    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String execute() {
        User user = userService.getCurrentUser();
        /*HttpSession session = request.getSession();

        User user = (User) Optional.ofNullable(session.getAttribute("user"))
                .orElse(User.getBuilder().setUserRole(User.Role.GUEST).build());*/
        logger.trace("User " + user.getId() + " with role " + user.getRole() + " redirect to home page");
        switch (user.getRole()) {
            case USER:
                return "redirect:/api/user/home";
            case ADMIN:
                return "redirect:/api/admin/home";
            default:
                return "redirect:/api/guest/login";
        }
    }
}

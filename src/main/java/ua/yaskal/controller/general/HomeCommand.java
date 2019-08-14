package ua.yaskal.controller.general;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.yaskal.model.entity.User;
import ua.yaskal.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

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
    public String execute(HttpServletRequest request) {
        User user = userService.getCurrentUser();

        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("userId", user.getId());
        request.getSession().setAttribute("email", user.getEmail());

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

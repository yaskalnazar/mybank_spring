package ua.yaskal.controller.general;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.yaskal.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This command used for logout with the preservation of the language.
 *
 * @author Nazar Yaskal
 */
@Controller
@RequestMapping(value = "/api")
public class LogOutController {
    private final static Logger logger = Logger.getLogger(LogOutController.class);


    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        logger.debug("User " + user.getId() + " logout");


        String locale = (String) request.getSession().getAttribute("locale");
        request.getSession().invalidate();
        request.getSession().setAttribute("locale", locale);
        return "redirect/mybank/api/guest/login";

    }
}

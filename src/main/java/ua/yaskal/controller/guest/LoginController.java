package ua.yaskal.controller.guest;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.yaskal.controller.JspPath;
import ua.yaskal.controller.util.ValidationUtil;
import ua.yaskal.model.service.UserService;

/**
 * This command used for getting login_form for QUEST and process input.
 * Required params: email and password if QUEST SingIn;
 *
 * @author Nazar Yaskal
 */

@Controller
@RequestMapping(value = "/api/guest")
public class LoginController {
    private final static Logger logger = Logger.getLogger(LoginController.class);
    private ValidationUtil validationUtil;
    private UserService userService;

    public LoginController(ValidationUtil validationUtil, UserService userService) {
        this.validationUtil = validationUtil;
        this.userService = userService;
    }

    @RequestMapping(value = "/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            RedirectAttributes redirectAttributes,
                            Model model) {
        model.addAllAttributes(redirectAttributes.getFlashAttributes());
        model.addAttribute("error", error!=null);
        model.addAttribute("logoutSuccessfully", logout !=null);

        return JspPath.LOGIN_FORM;
    }
  /*  public String execute(HttpServletRequest request) {
        if (!validationUtil.isContains(request, Arrays.asList("email", "password"))) {
            return JspPath.LOGIN_FORM;
        }

        if (!validationUtil.isRequestValid(request, Arrays.asList("email", "password"))) {
            request.setAttribute("wrongInput", true);
            return JspPath.LOGIN_FORM;
        }

        UserLoginDTO userLoginDTO = new UserLoginDTO(
                request.getParameter("email"),
                request.getParameter("password"));
        User user;
        try {
            user = userService.loginUser(userLoginDTO);
        } catch (NoSuchUserException e) {
            logger.warn("Login attempt with nonexistent email " + userLoginDTO.getEmail());
            request.setAttribute("wrongInput", true);
            return JspPath.LOGIN_FORM;
        } catch (WrongPasswordException e) {
            logger.warn("Login attempt with wrong password (email: " + userLoginDTO.getEmail() + ")");
            request.setAttribute("wrongInput", true);
            return JspPath.LOGIN_FORM;
        }


        signInUser(request, user);
        return "redirect/mybank/api/home";

    }*/

   /* private void signInUser(HttpServletRequest request, User user) {
        if (Objects.nonNull(request.getServletContext().getAttribute(user.getEmail()))) {
            ((HttpSession) request.getServletContext().getAttribute(user.getEmail())).invalidate();
            request.getServletContext().removeAttribute(user.getEmail());
            logger.warn("Remove another session of user " + user.getEmail());
        }

        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("userId", user.getId());
        request.getSession().setAttribute("email", user.getEmail());
        request.getServletContext().setAttribute(user.getEmail(), request.getSession());
        logger.debug("User " + user.getId() + " successfully login");
    }

    public void setValidationUtil(ValidationUtil validationUtil) {
        this.validationUtil = validationUtil;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }*/
}

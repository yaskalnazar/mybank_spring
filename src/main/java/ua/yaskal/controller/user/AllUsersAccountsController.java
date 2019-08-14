package ua.yaskal.controller.user;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.yaskal.controller.JspPath;
import ua.yaskal.model.service.CreditService;
import ua.yaskal.model.service.DepositService;
import ua.yaskal.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * This command used for getting all users accounts for USER.
 *
 * @author Nazar Yaskal
 */
@Controller
@PreAuthorize("hasAuthority('USER')")
@RequestMapping(value = "/api/user")
public class AllUsersAccountsController {
    private final static Logger logger = Logger.getLogger(AllUsersAccountsController.class);
    private DepositService depositService;
    private CreditService creditService;
    private UserService userService;

    public AllUsersAccountsController(DepositService depositService, CreditService creditService, UserService userService) {
        this.depositService = depositService;
        this.creditService = creditService;
        this.userService = userService;
    }

    //TODO witch all to model
    @GetMapping(value = "/account/all")
    public String execute(HttpServletRequest request) {
        request.setAttribute("deposits", depositService.getAllByOwnerId(
                userService.getCurrentUser().getId()));
        request.setAttribute("credits", creditService.getAllByOwnerId(
                userService.getCurrentUser().getId()));
        return JspPath.USER_ALL_ACCOUNTS;
    }

}

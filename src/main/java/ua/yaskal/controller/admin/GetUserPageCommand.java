package ua.yaskal.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yaskal.controller.JspPath;
import ua.yaskal.model.entity.User;
import ua.yaskal.model.service.CreditService;
import ua.yaskal.model.service.DepositService;
import ua.yaskal.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * This command used to get user page for ADMIN.
 * Required params: id;
 *
 * @author Nazar Yaskal
 */

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = "/api/admin")
public class GetUserPageCommand  {
    private final static Logger logger = Logger.getLogger(GetUserPageCommand.class);
    private UserService userService;
    private CreditService creditService;
    private DepositService depositService;

    public GetUserPageCommand(UserService userService, CreditService creditService, DepositService depositService) {
        this.userService = userService;
        this.creditService = creditService;
        this.depositService = depositService;
    }

    @GetMapping(value = "/user_page")
    public String execute(@RequestParam(value = "id") long id,
                          HttpServletRequest request) {

        User requestUser = userService.getById(id);

        request.setAttribute("requestUser", requestUser);
        request.setAttribute("credits", creditService.getAllByOwnerId(requestUser.getId()));
        request.setAttribute("deposits", depositService.getAllByOwnerId(requestUser.getId()));

        return JspPath.ADMIN_USER_PAGE;
    }


}

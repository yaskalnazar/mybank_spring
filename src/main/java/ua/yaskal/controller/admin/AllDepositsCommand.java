package ua.yaskal.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.yaskal.controller.JspPath;
import ua.yaskal.model.service.CreditService;
import ua.yaskal.model.service.DepositService;

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
public class AllDepositsCommand {
    private DepositService depositService;

    public AllDepositsCommand(DepositService depositService) {
        this.depositService = depositService;
    }

    @GetMapping(value = "/account/all/deposits")
    public String execute(HttpServletRequest request) {
        request.setAttribute("deposits", depositService.getAll());
        return JspPath.ADMIN_ALL_DEPOSITS;
    }


}

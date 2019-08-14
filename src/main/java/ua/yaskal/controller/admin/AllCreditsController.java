package ua.yaskal.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.yaskal.controller.JspPath;
import ua.yaskal.model.service.CreditService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

/**
 * This command used to get all_credits page for ADMIN.
 * Required @param currentPage if pagination used;
 *
 * @author Nazar Yaskal
 */
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = "/api/admin")
public class AllCreditsController {
    private CreditService creditService;

    public AllCreditsController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping(value = "/account/all/credits")
    public String execute(HttpServletRequest request) {
        request.setAttribute("credits", creditService.getAll());
        return JspPath.ADMIN_ALL_CREDITS;
    }


}

package ua.yaskal.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yaskal.controller.JspPath;
import ua.yaskal.controller.util.ValidationUtil;
import ua.yaskal.model.entity.CreditRequest;
import ua.yaskal.model.service.CreditRequestService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Objects;

/**
 * This command used to get all credit requests page for ADMIN.
 * Required @requestStatus for filtering answers;
 * @author Nazar Yaskal
 */

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = "/api/admin")
public class GetCreditRequestsCommand{
    private final static Logger logger = Logger.getLogger(GetCreditRequestsCommand.class);
    private ValidationUtil validationUtil;
    private CreditRequestService creditRequestService;

    public GetCreditRequestsCommand(ValidationUtil validationUtil, CreditRequestService creditRequestService) {
        this.validationUtil = validationUtil;
        this.creditRequestService = creditRequestService;
    }

    @GetMapping(value = "/credit_request/all")
    public String execute(@RequestParam(value = "requestStatus",required = false) String requestStatus,
                          HttpServletRequest request) {
        if (Objects.isNull(requestStatus)) {
            request.setAttribute("creditRequests",
                    creditRequestService.getAllByStatus(CreditRequest.CreditRequestStatus.PENDING));
            request.setAttribute("status", "pending");
            return JspPath.ADMIN_ALL_CREDIT_REQUESTS;
        }

        switch (requestStatus) {
            case "all":
                request.setAttribute("creditRequests", creditRequestService.getAll());
                request.setAttribute("status", "all");
                break;
            case "rejected":
                request.setAttribute("creditRequests",
                        creditRequestService.getAllByStatus(CreditRequest.CreditRequestStatus.REJECTED));
                request.setAttribute("status", "rejected");
                break;
            case "approved":
                request.setAttribute("creditRequests",
                        creditRequestService.getAllByStatus(CreditRequest.CreditRequestStatus.APPROVED));
                request.setAttribute("status", "approved");
                break;
            default:
                request.setAttribute("creditRequests",
                        creditRequestService.getAllByStatus(CreditRequest.CreditRequestStatus.PENDING));
                request.setAttribute("status", "pending");
        }

        return JspPath.ADMIN_ALL_CREDIT_REQUESTS;

    }


}

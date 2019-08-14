package ua.yaskal.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yaskal.controller.JspPath;
import ua.yaskal.model.entity.CreditAccount;
import ua.yaskal.model.entity.CreditRequest;
import ua.yaskal.model.entity.User;
import ua.yaskal.model.service.CreditRequestService;
import ua.yaskal.model.service.CreditService;
import ua.yaskal.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * This command used to get and process credit request page for ADMIN.
 * Required params: id;
 * answer currentPage if request has been approved or rejected;
 * @author Nazar Yaskal
 */

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = "/api/admin")
public class CreditRequestController {
    private final static Logger logger = Logger.getLogger(CreditRequestController.class);
    private CreditRequestService creditRequestService;
    private UserService userService;
    private CreditService creditService;

    public CreditRequestController(CreditRequestService creditRequestService, UserService userService, CreditService creditService) {
        this.creditRequestService = creditRequestService;
        this.userService = userService;
        this.creditService = creditService;
    }

    @GetMapping(value = "/credit_request")
    public String execute(@RequestParam(value = "id") long requestId,
                          HttpServletRequest request) {

        CreditRequest creditRequest = creditRequestService.getById(
                requestId);
        User applicant = userService.getById(creditRequest.getApplicantId());
        request.setAttribute("creditRequest", creditRequest);
        request.setAttribute("applicant", applicant);
        request.setAttribute("credits", creditService.getAllByOwnerId(applicant.getId()));

        return JspPath.ADMIN_CREDIT_REQUEST;
    }

    @PostMapping(value = "/credit_request")
    public String processAnswer(@RequestParam(value = "id") long requestId,
                               @RequestParam(value = "answer") String answer,
                               HttpServletRequest request) {
        CreditRequest creditRequest = creditRequestService.getById(requestId);

        if (answer.equals("approved")) {
            creditRequestService.changeStatus(CreditRequest.CreditRequestStatus.APPROVED,
                    creditRequest.getId());

            creditRequest.setCreditRequestStatus(CreditRequest.CreditRequestStatus.APPROVED);

            CreditAccount creditAccount = creditService.addNew(creditRequest);
            //scheduledService.scheduleAccounts(Collections.singletonList(creditAccount));

            logger.debug("Credit account " + creditAccount.getId() + " open");
            request.setAttribute("answer", "approved");
        } else if (answer.equals("rejected")) {
            creditRequestService.changeStatus(CreditRequest.CreditRequestStatus.REJECTED,
                    creditRequest.getId());

            creditRequest.setCreditRequestStatus(CreditRequest.CreditRequestStatus.REJECTED);
            request.setAttribute("answer", "rejected");
        }
        User applicant = userService.getById(creditRequest.getApplicantId());
        request.setAttribute("creditRequest", creditRequest);
        request.setAttribute("applicant", applicant);
        request.setAttribute("credits", creditService.getAllByOwnerId(applicant.getId()));
        return JspPath.ADMIN_CREDIT_REQUEST;
    }

}

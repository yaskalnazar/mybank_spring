package ua.yaskal.controller.user;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yaskal.controller.JspPath;
import ua.yaskal.controller.util.ValidationUtil;
import ua.yaskal.model.dto.CreditRequestDTO;
import ua.yaskal.model.entity.Account;
import ua.yaskal.model.entity.CreditAccount;
import ua.yaskal.model.entity.CreditRequest;
import ua.yaskal.model.service.CreditRequestService;
import ua.yaskal.model.service.CreditService;
import ua.yaskal.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * This command used for checking if the user can have a new credit,
 * notification result and process new request.
 * Required params: creditLimit, creditRate if new request has been sent;
 *
 * @author Nazar Yaskal
 */

@Controller
@PreAuthorize("hasAuthority('USER')")
@RequestMapping(value = "/api/user")
public class NewCreditRequestCommand {
    private final static Logger logger = Logger.getLogger(NewCreditRequestCommand.class);
    private ValidationUtil validationUtil;
    private CreditRequestService creditRequestService;
    private CreditService creditService;
    private UserService userService;

    public NewCreditRequestCommand(ValidationUtil validationUtil, CreditRequestService creditRequestService, CreditService creditService, UserService userService) {
        this.validationUtil = validationUtil;
        this.creditRequestService = creditRequestService;
        this.creditService = creditService;
        this.userService = userService;
    }

    @GetMapping(value = "/account/credit/open")
    public String getPage(HttpServletRequest request) {
        isUserCanRequestCredit(request);

        return JspPath.USER_CREDIT_OPEN;
    }

    @RequestMapping(value = "/account/credit/open")
    public String execute(@RequestParam(value = "creditLimit", required = false) BigDecimal creditLimit,
                          @RequestParam(value = "creditRate", required = false) BigDecimal creditRate,
                          @RequestParam(value = "deleteId", required = false) Long deleteId,
                          HttpServletRequest request) {


        if (Objects.nonNull(deleteId)) {
            creditRequestService.delete(deleteId);
            return JspPath.USER_CREDIT_OPEN;
        }
        if (isUserCanRequestCredit(request)) {
            CreditRequestDTO creditRequestDTO = new CreditRequestDTO(
                    userService.getCurrentUser().getId(),
                    creditRate,
                    creditLimit,
                    LocalDateTime.now());

            CreditRequest creditRequest = creditRequestService.createNew(creditRequestDTO);

            logger.debug("Credit request successfully (id:" + creditRequest.getId() + ")");
            request.setAttribute("creditRequestSuccess", creditRequest);
        }
        return JspPath.USER_CREDIT_OPEN;
    }

    private boolean isUserCanRequestCredit(HttpServletRequest request) {
        long userId = (long) request.getSession().getAttribute("userId");

        return (isUserDontHaveActiveCredit(request, userId)
                && isUserDontHaveActiveRequests(request, userId));
    }

    private boolean isUserDontHaveActiveCredit(HttpServletRequest request, long userId) {
        List<CreditAccount> activeCreditAccounts = creditService.getAllByOwnerIdAndStatus(userId, Account.AccountStatus.ACTIVE);
        if (!activeCreditAccounts.isEmpty()) {
            logger.warn("Credit open attempt with activeCreditAccounts userId(" + userId + ")");
            request.setAttribute("activeCreditAccounts", activeCreditAccounts);
            return false;
        }
        return true;
    }

    private boolean isUserDontHaveActiveRequests(HttpServletRequest request, long userId) {
        List<CreditRequest> activeCreditRequests = creditRequestService.getAllByApplicantIdAndStatus(
                userId, CreditRequest.CreditRequestStatus.PENDING);
        if (!activeCreditRequests.isEmpty()) {
            logger.warn("Credit open attempt with activeCreditRequests userId(" + userId + ")");
            request.setAttribute("activeCreditRequests", activeCreditRequests);
            return false;
        }
        return true;
    }
}

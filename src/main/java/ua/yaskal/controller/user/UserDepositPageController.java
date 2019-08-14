package ua.yaskal.controller.user;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yaskal.controller.JspPath;
import ua.yaskal.model.entity.DepositAccount;
import ua.yaskal.model.entity.Transaction;
import ua.yaskal.model.exeptions.key.AccessDeniedException;
import ua.yaskal.model.exeptions.key.no.such.NoSuchAccountException;
import ua.yaskal.model.service.AccountService;
import ua.yaskal.model.service.DepositService;
import ua.yaskal.model.service.TransactionService;
import ua.yaskal.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * This command used to get credit page for USER. Required params: id;
 * currentPage - for showing transactions with pagination.
 *
 * @author Nazar Yaskal
 */

@Controller
@PreAuthorize("hasAuthority('USER')")
@RequestMapping(value = "/api/user")
public class UserDepositPageController {
    private final static Logger logger = Logger.getLogger(UserDepositPageController.class);
    private DepositService depositService;
    private TransactionService transactionService;
    private AccountService accountService;
    private UserService userService;

    public UserDepositPageController(DepositService depositService, TransactionService transactionService, AccountService accountService, UserService userService) {
        this.depositService = depositService;
        this.transactionService = transactionService;
        this.accountService = accountService;
        this.userService = userService;
    }

    @GetMapping(value = "/account/deposit_page")
    public String execute(@RequestParam(value = "id") long creditId,
                          HttpServletRequest request) {

        long userId = userService.getCurrentUser().getId();

        DepositAccount depositAccount;
        try {
            depositAccount = depositService.getById(creditId);
        } catch (NoSuchAccountException e) {
            throw new AccessDeniedException();
        }

        if (depositAccount.getOwnerId() != userId) {
            logger.error("User " + userId + " attempt to access account" + creditId + " without permission");
            throw new AccessDeniedException();
        }

        List<Transaction> transactions = transactionService.getAllByAccountId(creditId);
        transactions.stream().forEachOrdered(x -> {
            if (x.getSenderAccountId() == creditId) {
                x.setTransactionAmount(x.getTransactionAmount().negate());
            }
        });

        request.setAttribute("transactions", transactions);
        request.setAttribute("credit", depositAccount);
        /*request.setAttribute("activeUserAccounts",
                accountService.getAllByOwnerIdAndStatus(userId, Account.AccountStatus.ACTIVE));*/
        return JspPath.USER_CREDIT_PAGE;
    }


}

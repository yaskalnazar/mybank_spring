package ua.yaskal.controller.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yaskal.controller.JspPath;
import ua.yaskal.controller.util.ValidationUtil;
import ua.yaskal.model.entity.Account;
import ua.yaskal.model.entity.Transaction;
import ua.yaskal.model.service.AccountService;
import ua.yaskal.model.service.TransactionService;
import ua.yaskal.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * This command used for getting page for replenishing account and process result.
 * Required params: accountId, amount if new replenishment has been done;
 *
 * @author Nazar Yaskal
 */
@Controller
@PreAuthorize("hasAuthority('USER')")
@RequestMapping(value = "/api/user")
public class ReplenishAccountCommand {
    private ValidationUtil validationUtil;
    private AccountService accountService;
    private TransactionService transactionService;
    private UserService userService;
    private final static long WORKAROUND_ACCOUNT_ID = 12;

    public ReplenishAccountCommand(ValidationUtil validationUtil, AccountService accountService,
                                   TransactionService transactionService, UserService userService) {
        this.validationUtil = validationUtil;
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @GetMapping(value = "/account/replenish")
    public String getPage(HttpServletRequest request) {
        long userId = userService.getCurrentUser().getId();

        request.setAttribute("activeUserAccounts", accountService.getAllByOwnerIdAndStatus(
                userId, Account.AccountStatus.ACTIVE));

        return JspPath.USER_REPLENISH_ACCOUNT;
    }

    @PostMapping(value = "/account/replenish")
    public String execute(@RequestParam(value = "accountId") long accountId,
                          @RequestParam(value = "amount") BigDecimal amount,
                          HttpServletRequest request) {
        long userId = userService.getCurrentUser().getId();


        Transaction transaction = Transaction.getBuilder()
                .setReceiverAccount(accountId)
                .setTransactionAmount(amount)
                .setSenderAccount(WORKAROUND_ACCOUNT_ID)
                .setDate(LocalDateTime.now())
                .build();

        transactionService.makeNewTransaction(transaction);
        request.setAttribute("replenishSuccess", true);

        request.setAttribute("activeUserAccounts",
                accountService.getAllByOwnerIdAndStatus(userId, Account.AccountStatus.ACTIVE));
        return JspPath.USER_REPLENISH_ACCOUNT;
    }


}

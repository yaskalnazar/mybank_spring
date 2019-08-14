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
import ua.yaskal.model.entity.Account;
import ua.yaskal.model.entity.Transaction;
import ua.yaskal.model.exeptions.key.AccessDeniedException;
import ua.yaskal.model.exeptions.key.NotEnoughMoneyException;
import ua.yaskal.model.exeptions.key.no.such.NoSuchActiveAccountException;
import ua.yaskal.model.service.AccountService;
import ua.yaskal.model.service.TransactionService;
import ua.yaskal.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * This command used for getting page for sending transaction from one account to another.
 * Required params: senderAccountId, receiverAccountId,
 * amount if new transaction has been sent;
 *
 * @author Nazar Yaskal
 */
@Controller
@PreAuthorize("hasAuthority('USER')")
@RequestMapping(value = "/api/user")
public class MakeTransactionCommand {
    private final static Logger logger = Logger.getLogger(MakeTransactionCommand.class);
    private AccountService accountService;
    private TransactionService transactionService;
    private UserService userService;

    public MakeTransactionCommand(AccountService accountService, TransactionService transactionService, UserService userService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @GetMapping(value = "/account/make_transaction")
    public String getPage(Model model) {
        model.addAttribute("activeUserAccounts",
                accountService.getAllByOwnerIdAndStatus(userService.getCurrentUser().getId(),
                        Account.AccountStatus.ACTIVE));
        return JspPath.USER_MAKE_TRANSACTION;
    }

    @PostMapping(value = "/account/make_transaction")
    public String execute(@RequestParam(value = "senderAccountId") long senderAccountId,
                          @RequestParam(value = "receiverAccountId") long receiverAccountId,
                          @RequestParam(value = "amount") BigDecimal amount,
                          HttpServletRequest request) {
        long userId = userService.getCurrentUser().getId();


        if (accountService.getById(senderAccountId).getOwnerId() != userId) {
            logger.error("User " + userId + " attempt to access account" + senderAccountId + " without permission");
            throw new AccessDeniedException();
        }

        Transaction transaction = Transaction.getBuilder()
                .setReceiverAccount(receiverAccountId)
                .setTransactionAmount(amount)
                .setSenderAccount(senderAccountId)
                .setDate(LocalDateTime.now())
                .build();

        try {
            transactionService.makeNewTransaction(transaction);
            request.setAttribute("transactionSuccess", true);
        } catch (NotEnoughMoneyException e) {
            request.setAttribute("transactionError", e.getMessageKey());
        } catch (NoSuchActiveAccountException e) {
            request.setAttribute("transactionError", e.getMessageKey());
        }


        request.setAttribute("activeUserAccounts", accountService.getAllByOwnerIdAndStatus(userId, Account.AccountStatus.ACTIVE));
        return JspPath.USER_MAKE_TRANSACTION;

    }


}

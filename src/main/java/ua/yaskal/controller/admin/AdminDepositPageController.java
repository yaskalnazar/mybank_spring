package ua.yaskal.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.yaskal.controller.JspPath;
import ua.yaskal.model.entity.Account;
import ua.yaskal.model.entity.DepositAccount;
import ua.yaskal.model.entity.Transaction;
import ua.yaskal.model.service.AccountService;
import ua.yaskal.model.service.DepositService;
import ua.yaskal.model.service.TransactionService;

import java.util.List;


/**
 * This command used to get credit page for USER. Required params: id;
 * currentPage - for showing transactions with pagination.
 *
 * @author Nazar Yaskal
 */

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping(value = "/api/admin")
public class AdminDepositPageController {
    private final static Logger logger = Logger.getLogger(AdminDepositPageController.class);
    private DepositService depositService;
    private TransactionService transactionService;
    private AccountService accountService;

    public AdminDepositPageController(DepositService depositService, TransactionService transactionService, AccountService accountService) {
        this.depositService = depositService;
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    @GetMapping(value = "/account/deposit_page")
    public String execute(@RequestParam(value = "id") long creditId,
                          Model model, RedirectAttributes redirectAttributes) {
        model.addAllAttributes(redirectAttributes.getFlashAttributes());
        DepositAccount depositAccount  = depositService.getById(creditId);

        List<Transaction> transactions = transactionService.getAllByAccountId(creditId);
        transactions.stream().forEachOrdered(x -> {
            if (x.getSenderAccountId() == creditId) {
                x.setTransactionAmount(x.getTransactionAmount().negate());
            }
        });

        model.addAttribute("transactions", transactions);
        model.addAttribute("deposit", depositAccount);
        return JspPath.ADMIN_DEPOSIT_PAGE;
    }

    @PostMapping(value = "/account/deposit_page")
    public String processAnswer(@RequestParam(value = "id") long creditId,
                                @RequestParam(value = "answer") String answer,
                                @RequestParam(value = "blockingReason", required = false) String blockingReason,
                                RedirectAttributes redirectAttributes) {
        if (answer.equals("block")) {
            logger.debug("Blocking credit account " + creditId + " reason: " + blockingReason);
            accountService.updateAccountStatus(creditId, Account.AccountStatus.BLOCKED);
            redirectAttributes.addFlashAttribute("answer", answer);
        } else if (answer.equals("unblock")) {
            logger.debug("Unblocking credit account " + creditId);
            accountService.updateAccountStatus(creditId, Account.AccountStatus.ACTIVE);
            redirectAttributes.addFlashAttribute("answer", answer);
        }
        redirectAttributes.addAttribute("id", creditId);

        return "redirect:/api/admin/account/deposit_page";
    }


}

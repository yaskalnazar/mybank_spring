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
import ua.yaskal.model.entity.CreditAccount;
import ua.yaskal.model.entity.Transaction;
import ua.yaskal.model.service.AccountService;
import ua.yaskal.model.service.CreditService;
import ua.yaskal.model.service.TransactionService;

import javax.servlet.http.HttpServletRequest;
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
public class AdminCreditPageCommand {
    private final static Logger logger = Logger.getLogger(AdminCreditPageCommand.class);
    private CreditService creditService;
    private AccountService accountService;
    private TransactionService transactionService;


    public AdminCreditPageCommand(CreditService creditService, AccountService accountService, TransactionService transactionService) {
        this.creditService = creditService;
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping(value = "/account/credit_page")
    public String execute(@RequestParam(value = "id") long creditId,
                          Model model, RedirectAttributes redirectAttributes) {
        model.addAllAttributes(redirectAttributes.getFlashAttributes());
        CreditAccount creditAccount  = creditService.getById(creditId);

        List<Transaction> transactions = transactionService.getAllByAccountId(creditId);
        transactions.stream().forEachOrdered(x -> {
            if (x.getSenderAccountId() == creditId) {
                x.setTransactionAmount(x.getTransactionAmount().negate());
            }
        });

        model.addAttribute("transactions", transactions);
        model.addAttribute("credit", creditAccount);
        return JspPath.ADMIN_CREDIT_PAGE;
    }

    @PostMapping(value = "/account/credit_page")
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

        return "redirect:/api/admin/account/credit_page";
    }

}

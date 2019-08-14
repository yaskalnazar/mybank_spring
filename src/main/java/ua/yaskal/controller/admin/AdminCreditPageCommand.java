package ua.yaskal.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yaskal.controller.JspPath;
import ua.yaskal.model.entity.CreditAccount;
import ua.yaskal.model.entity.Transaction;
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
    private TransactionService transactionService;


    public AdminCreditPageCommand(CreditService creditService, TransactionService transactionService) {
        this.creditService = creditService;
        this.transactionService = transactionService;
    }

    @GetMapping(value = "/account/credit_page")
    public String execute(@RequestParam(value = "id") long creditId,
                          HttpServletRequest request) {

        CreditAccount creditAccount  = creditService.getById(creditId);

        List<Transaction> transactions = transactionService.getAllByAccountId(creditId);
        transactions.stream().forEachOrdered(x -> {
            if (x.getSenderAccountId() == creditId) {
                x.setTransactionAmount(x.getTransactionAmount().negate());
            }
        });

        request.setAttribute("transactions", transactions);
        request.setAttribute("credit", creditAccount);
        return JspPath.ADMIN_CREDIT_PAGE;
    }


}

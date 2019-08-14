package ua.yaskal.controller.admin;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yaskal.controller.JspPath;
import ua.yaskal.model.entity.DepositAccount;
import ua.yaskal.model.entity.Transaction;
import ua.yaskal.model.service.DepositService;
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
public class AdminDepositPageCommand {
    private final static Logger logger = Logger.getLogger(AdminDepositPageCommand.class);
    private DepositService depositService;
    private TransactionService transactionService;

    public AdminDepositPageCommand(DepositService depositService, TransactionService transactionService) {
        this.depositService = depositService;
        this.transactionService = transactionService;
    }

    @GetMapping(value = "/account/deposit_page")
    public String execute(@RequestParam(value = "id") long creditId,
                          HttpServletRequest request) {


        DepositAccount depositAccount  = depositService.getById(creditId);

        List<Transaction> transactions = transactionService.getAllByAccountId(creditId);
        transactions.stream().forEachOrdered(x -> {
            if (x.getSenderAccountId() == creditId) {
                x.setTransactionAmount(x.getTransactionAmount().negate());
            }
        });

        request.setAttribute("transactions", transactions);
        request.setAttribute("deposit", depositAccount);
        return JspPath.ADMIN_CREDIT_PAGE;
    }


}

package ua.yaskal.controller.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yaskal.controller.JspPath;
import ua.yaskal.model.entity.Payment;
import ua.yaskal.model.entity.Transaction;
import ua.yaskal.model.exeptions.key.AccessDeniedException;
import ua.yaskal.model.exeptions.key.NotEnoughMoneyException;
import ua.yaskal.model.exeptions.key.no.such.NoSuchAccountException;
import ua.yaskal.model.service.AccountService;
import ua.yaskal.model.service.PaymentService;
import ua.yaskal.model.service.TransactionService;
import ua.yaskal.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This command used for getting all users payments for USER and process answer.
 * Required params: answer if payments has been paid or rejected;
 *
 * @author Nazar Yaskal
 */


@Controller
@PreAuthorize("hasAuthority('USER')")
@RequestMapping(value = "/api/user")
public class AllUsersPaymentController {
    private final static Logger logger = Logger.getLogger(AllUsersPaymentController.class);
    @Autowired
    private PaymentService paymentService;
    private AccountService accountService;
    private TransactionService transactionService;
    private UserService userService;

    public AllUsersPaymentController(PaymentService paymentService, AccountService accountService, TransactionService transactionService, UserService userService) {
        this.paymentService = paymentService;
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.userService = userService;
    }


    @GetMapping(value = "/payment/all")
    public String execute(@RequestParam(value = "user_status", required = false) String user_status,
                          HttpServletRequest request) {


        getPayments(request,user_status);

        return JspPath.USER_ALL_PAYMENTS;
    }

    @PostMapping(value = "/payment/all")
    public String processAnswer(@RequestParam(value = "paymentId") long paymentId,
                                 @RequestParam(value = "answer") String answer,
                                 HttpServletRequest request) {

        if (answer.equals("paid")) {
            Payment payment = paymentService.getById(paymentId);

            long userId = userService.getCurrentUser().getId();

            if (accountService.getById(payment.getPayerAccountId()).getOwnerId() != userId) {
                logger.error("User " + userId + " attempt to access account" + payment.getPayerAccountId() + " without permission");
                throw new AccessDeniedException();
            }

            Transaction transaction = Transaction.getBuilder()
                    .setReceiverAccount(payment.getRequesterAccountId())
                    .setTransactionAmount(payment.getAmount())
                    .setSenderAccount(payment.getPayerAccountId())
                    .setDate(LocalDateTime.now())
                    .build();

            try {
                transactionService.makeNewTransaction(transaction);
                paymentService.updateStatusById(Payment.PaymentStatus.PAID, payment.getId());
                request.setAttribute("paymentSuccess", true);
            } catch (NotEnoughMoneyException e) {
                request.setAttribute("paymentError", e.getMessageKey());
            } catch (NoSuchAccountException e){
                request.setAttribute("paymentError", e.getMessageKey());
            }

        } else if (answer.equals("rejected")) {
            paymentService.updateStatusById(Payment.PaymentStatus.REJECTED,
                    paymentId);
        }
        getPayments(request,"payer");
        return JspPath.USER_ALL_PAYMENTS;
    }

    public void getPayments(HttpServletRequest request, String user_status){
        long userId = userService.getCurrentUser().getId();

        if (Objects.isNull(user_status)) {
            request.setAttribute("payments",
                    paymentService.getAllByPayerId(userId));
            request.setAttribute("user_status", "received");
            return;
        }

        switch (user_status) {
            case "requester":
                request.setAttribute("payments",
                        paymentService.getAllByRequesterId(userId));
                request.setAttribute("user_status", "sent");
                break;
            default:
                request.setAttribute("payments",
                        paymentService.getAllByPayerId(userId));
                request.setAttribute("user_status", "received");
        }
    }

}

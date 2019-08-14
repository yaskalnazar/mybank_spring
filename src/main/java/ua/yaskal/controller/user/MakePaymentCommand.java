package ua.yaskal.controller.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.yaskal.controller.JspPath;
import ua.yaskal.controller.util.ValidationUtil;
import ua.yaskal.model.entity.Account;
import ua.yaskal.model.entity.Payment;
import ua.yaskal.model.exeptions.key.no.such.NoSuchActiveAccountException;
import ua.yaskal.model.service.AccountService;
import ua.yaskal.model.service.PaymentService;
import ua.yaskal.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * This command used for getting page for sending payment from one account to another.
 * Required params: payerAccountId, requesterAccountId,
 * amount if new payment has been sent;
 *
 * @author Nazar Yaskal
 */

@Controller
@PreAuthorize("hasAuthority('USER')")
@RequestMapping(value = "/api/user")
public class MakePaymentCommand {
    private ValidationUtil validationUtil;
    private PaymentService paymentService;
    private AccountService accountService;
    private UserService userService;

    public MakePaymentCommand(ValidationUtil validationUtil, PaymentService paymentService, AccountService accountService, UserService userService) {
        this.validationUtil = validationUtil;
        this.paymentService = paymentService;
        this.accountService = accountService;
        this.userService = userService;
    }

    @GetMapping(value = "/payment/make_new")
    public String getPage(Model model) {
        model.addAttribute("activeUserAccounts",
                accountService.getAllByOwnerIdAndStatus(userService.getCurrentUser().getId(),
                        Account.AccountStatus.ACTIVE));
        return JspPath.USER_MAKE_PAYMENT;
    }


    @PostMapping(value = "/payment/make_new")
    public String execute(@RequestParam(value = "payerAccountId") long payerAccountId,
                          @RequestParam(value = "requesterAccountId") long requesterAccountId,
                          @RequestParam(value = "amount") BigDecimal amount,
                          @RequestParam(value = "message", required = false) String message,
                          HttpServletRequest request) {

        Payment payment = Payment.getBuilder()
                .setAmount(amount)
                .setPayerAccountId(payerAccountId)
                .setRequesterAccountId(requesterAccountId)
                .setPaymentStatus(Payment.PaymentStatus.PENDING)
                .setDate(LocalDateTime.now())
                .setMessage(message)
                .build();

        try {
            paymentService.addNew(payment);
            request.setAttribute("paymentSuccess", true);
        } catch (NoSuchActiveAccountException e) {
            request.setAttribute("paymentError", e.getMessageKey());
        }


        request.setAttribute("activeUserAccounts", accountService.getAllByOwnerIdAndStatus(
                userService.getCurrentUser().getId(), Account.AccountStatus.ACTIVE));
        return JspPath.USER_MAKE_PAYMENT;
    }

}

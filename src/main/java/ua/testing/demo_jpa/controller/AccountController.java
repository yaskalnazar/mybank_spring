package ua.testing.demo_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.testing.demo_jpa.dto.CreditRequestDTO;
import ua.testing.demo_jpa.dto.DepositDTO;
import ua.testing.demo_jpa.entity.Account;
import ua.testing.demo_jpa.entity.CreditAccount;
import ua.testing.demo_jpa.entity.CreditRequest;
import ua.testing.demo_jpa.entity.DepositAccount;
import ua.testing.demo_jpa.service.AccountService;
import ua.testing.demo_jpa.service.CreditRequestService;
import ua.testing.demo_jpa.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j

@Controller
@PreAuthorize("hasAuthority('ROLE_USER')")
@RequestMapping(value = "/user/account")
public class AccountController {
    private AccountService accountService;
    private UserService userService;
    private CreditRequestService creditRequestService;

    public AccountController(AccountService accountService, UserService userService,
                             CreditRequestService creditRequestService) {
        this.accountService = accountService;
        this.userService = userService;
        this.creditRequestService = creditRequestService;
    }

    @GetMapping(value = "/deposit/open/")
    public String openDeposit(){
        return "user/deposit_open";
    }

    @PostMapping(value = "/deposit/open/")
    public String openDeposit(DepositDTO depositDTO, Model model){
        log.error(depositDTO.toString());
        Account result = accountService.saveNewAccount(DepositAccount.builder()
                .balance(new BigDecimal(0))
                .closingDate(LocalDate.now().plusYears(5))
                .owner(userService.getCurrentUser())
                .accountStatus(Account.AccountStatus.ACTIVE)
                //.transactions(new ArrayList<>())
                .depositAmount(depositDTO.getDepositAmount())
                .depositEndDate(LocalDate.now().plusMonths(depositDTO.getMonthsAmount()))
                .depositRate(depositDTO.getDepositRate())
                .build());
        if (Objects.nonNull(result)) {
            model.addAttribute("success", true);
        } else {
            model.addAttribute("error", true);
        }
        return "user/deposit_open";
    }

    @GetMapping(value = "/all_accounts/")
    public String allUserAccounts(Map<String, Object> model){
        Iterable<Account> accounts = accountService.getUserAccounts(userService.getCurrentUser()).getAccounts();
        List<DepositAccount> depositAccounts = new ArrayList<>();
        List<CreditAccount> creditAccounts = new ArrayList<>();
        for (Account i:accounts){
            if (i.getAccountType().equals(Account.AccountType.DEPOSIT.name())){
                depositAccounts.add((DepositAccount) i);
            } else  if (i.getAccountType().equals(Account.AccountType.CREDIT.name())){
                creditAccounts.add((CreditAccount) i);
            }
        }
        model.put("accounts", accounts);
        model.put("depositAccounts", depositAccounts);
        model.put("creditAccounts", creditAccounts);
        return "user/all_accounts";
    }

    @GetMapping(value = "/credit/open/")
    public String openCredit(Map<String, Object> model){
        Iterable<Account> accounts = accountService.getUserAccounts(userService.getCurrentUser()).getAccounts();
        List<CreditAccount> activeCreditAccounts = new ArrayList<>();
        for (Account i:accounts){
           if (i.getAccountType().equals(Account.AccountType.CREDIT.name())
                   && i.getAccountStatus().equals(Account.AccountStatus.ACTIVE)){
                activeCreditAccounts.add((CreditAccount) i);
            }
        }
        if (!activeCreditAccounts.isEmpty()) {
            model.put("activeCreditAccounts", activeCreditAccounts);
        }
        return "user/credit_open";
    }

    @PostMapping(value = "/credit/open/")
    public String openDeposit(CreditRequestDTO creditRequestDTO, Model model) {
        log.info(creditRequestDTO.toString());
        CreditRequest result = creditRequestService.saveNewCreditRequest(
                CreditRequest.builder()
                        .creditRequestStatus(CreditRequest.CreditRequestStatus.PENDING)
                        .applicant(userService.getCurrentUser())
                        .creationDate(LocalDate.now())
                        .creditLimit(creditRequestDTO.getCreditLimit())
                        .creditRate(creditRequestDTO.getCreditRate())
                        .build()
        );
        if (Objects.nonNull(result)) {
            model.addAttribute("success", true);
        } else {
            model.addAttribute("error", true);
        }
        return "user/credit_open";
    }

}

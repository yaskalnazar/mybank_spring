package ua.testing.demo_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.testing.demo_jpa.dto.DepositDTO;
import ua.testing.demo_jpa.entity.Account;
import ua.testing.demo_jpa.entity.DepositAccount;
import ua.testing.demo_jpa.service.AccountService;
import ua.testing.demo_jpa.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@Slf4j
@Controller
@PreAuthorize("hasAuthority('ROLE_USER')")
@RequestMapping(value = "/user/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/deposit/open/")
    public String openDeposit(Model model){
        return "deposit_open";
    }

    @PostMapping(value = "/deposit/open/")
    public String openDeposit(DepositDTO depositDTO, Model model){
        log.error("FUCK"+depositDTO.toString());
        Account result = accountService.saveNewAccount(DepositAccount.builder()
                .balance(new BigDecimal(0))
                .closingDate(LocalDate.of(2024,01,01))
                .ownerId(userService.getCurrentUser().getId())
                .status(Account.AccountStatus.ACTIVE)
                .transactions(new ArrayList<>())
                .depositAmount(depositDTO.getDepositAmount())
                .depositEndDate(LocalDate.now().plusMonths(depositDTO.getMonthsAmount()))
                .depositRate(depositDTO.getDepositRate())
                .build());
        if (result != null) {
            model.addAttribute("success", true);
        } else {
            model.addAttribute("error", true);
        }
        return "deposit_open";

    }
}

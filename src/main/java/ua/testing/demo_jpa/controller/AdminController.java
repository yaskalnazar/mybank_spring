package ua.testing.demo_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.testing.demo_jpa.dto.UsersDTO;
import ua.testing.demo_jpa.entity.*;
import ua.testing.demo_jpa.service.AccountService;
import ua.testing.demo_jpa.service.CreditRequestService;
import ua.testing.demo_jpa.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping(value = "/admin")
public class AdminController {
    private UserService userService;
    private AccountService accountService;
    private CreditRequestService creditRequestService;

    public AdminController(UserService userService, AccountService accountService, CreditRequestService creditRequestService) {
        this.userService = userService;
        this.accountService = accountService;
        this.creditRequestService = creditRequestService;
    }

    @RequestMapping(value = "/home")
    public String home(Model model){
        model.addAttribute("user", userService.getCurrentUser());
        return "admin/admin_home";
    }

    @GetMapping("/all_users")
    public String getAllUser(Map<String, Object> model){
        UsersDTO userDTO =  userService.getAllUsers();
        log.info("{}", userDTO);
        model.put("users", userDTO.getUsers());
        return "admin/all_users";
    }

    @RequestMapping("/all_accounts")
    public String allAccounts(Map<String, Object> model) {
        Iterable<Account> accounts = accountService.getAllAccounts().getAccounts();
        model.put("accounts", accounts);
        return "admin/all_accounts";
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("{\"message\": \"" + ex.getMessage() + "\"}");
    }

    @GetMapping(value = "/user/{id}")
    public String getUser(Model model, @PathVariable Long id){
        model.addAttribute("user",  userService.loadUserById(id));
        return "admin/user_info";
    }

    @GetMapping(value = "/credit_request/{id}")
    public String processingCreditRequest(Map<String, Object> model, @PathVariable Long id){
        CreditRequest creditRequest = creditRequestService.loadCreditRequestById(id);
        User applicant = creditRequest.getApplicant();
        Iterable<Account> accounts = applicant.getAccounts();
        List<CreditAccount> creditHisory = new ArrayList<>();
        for (Account i:accounts){
            if (i.getAccountType().equals(Account.AccountType.CREDIT.name())){
                creditHisory.add((CreditAccount) i);
            }
        }
        model.put("creditRequest",  creditRequestService.loadCreditRequestById(id));
        model.put("applicant", applicant);
        model.put("creditHistory", creditHisory);

        return "admin/credit_request";
    }

    @PostMapping(value = "/credit_request/{id}")
    public String processingCreditRequest(Map<String, Object> model, @PathVariable Long id, @RequestParam String action){
        log.error(action);
        CreditRequest creditRequest = creditRequestService.loadCreditRequestById(id);
        if (action.equals(CreditRequest.CreditRequestStatus.APPROVED.name())){
            creditRequest.setCreditRequestStatus(CreditRequest.CreditRequestStatus.APPROVED);
            Account result = accountService.saveNewAccount(CreditAccount.builder()
                    .balance(new BigDecimal(0))
                    .closingDate(LocalDate.now().plusYears(5))
                    .owner(creditRequest.getApplicant())
                    .accountStatus(Account.AccountStatus.ACTIVE)
                    //.transactions(new ArrayList<>())
                    .creditRate(creditRequest.getCreditRate())
                    .creditLimit(creditRequest.getCreditLimit())
                    .accruedInterest(new BigDecimal(0))
                    .build());
        } else if(action.equals(CreditRequest.CreditRequestStatus.REJECTED.name())){
            creditRequest.setCreditRequestStatus(CreditRequest.CreditRequestStatus.REJECTED);
        }
        creditRequestService.saveCreditRequest(creditRequest);
        return "redirect:/home";
    }
}

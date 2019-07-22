package ua.testing.demo_jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.testing.demo_jpa.dto.UsersDTO;
import ua.testing.demo_jpa.entity.Account;
import ua.testing.demo_jpa.entity.CreditRequest;
import ua.testing.demo_jpa.service.AccountService;
import ua.testing.demo_jpa.service.CreditRequestService;
import ua.testing.demo_jpa.service.UserService;

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
        return "all_users";
    }

    @RequestMapping("/all_accounts")
    public String allAccounts(Map<String, Object> model) {
        Iterable<Account> accounts = accountService.getAllAccounts().getAccounts();
        model.put("accounts", accounts);
        return "admin/all_accounts";
    }

    @RequestMapping("/all_credit_requests")
    public String allCreditRequests(Map<String, Object> model){
        Iterable<CreditRequest> creditRequests = creditRequestService.getAllCreditRequests();
        model.put("creditRequests", creditRequests);
        return "admin/all_credit_requests";
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("{\"message\": \"" + ex.getMessage() + "\"}");
    }
}

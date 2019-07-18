package ua.testing.demo_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.testing.demo_jpa.entity.Account;
import ua.testing.demo_jpa.service.AccountService;
import ua.testing.demo_jpa.service.UserService;

import java.util.Map;


@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/home")
    public String home(Model model){
        model.addAttribute("user", userService.getCurrentUser());
        return "admin/admin_home";
    }

    @RequestMapping("/all_users")
    public String userPage(){
        return "admin/all_users";
    }

    @RequestMapping("/all_accounts")
    public String all_accounts(Map<String, Object> model){
        Iterable<Account> accounts = accountService.getAllAccounts().getAccounts();
        model.put("accounts", accounts);
        return "admin/all_accounts";
    }
}

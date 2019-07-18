package ua.testing.demo_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.experimental.SuperBuilder;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.testing.demo_jpa.dto.AccountsDTO;
import ua.testing.demo_jpa.entity.Account;
import ua.testing.demo_jpa.entity.CreditAccount;
import ua.testing.demo_jpa.entity.DepositAccount;
import ua.testing.demo_jpa.service.AccountService;
import ua.testing.demo_jpa.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;


@Controller
@PreAuthorize("hasAuthority('ROLE_USER')")
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/home")
    public String home(Model model){
        model.addAttribute("user", userService.getCurrentUser());
        return "user_home";
    }



}

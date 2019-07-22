package ua.testing.demo_jpa.service;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.testing.demo_jpa.dto.AccountsDTO;
import ua.testing.demo_jpa.entity.Account;
import ua.testing.demo_jpa.entity.User;
import ua.testing.demo_jpa.repository.AccountRepository;

import java.util.List;

@Slf4j
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountsDTO getAllAccounts(){
        List<Account> accounts = accountRepository.findAll();
        return new AccountsDTO(accounts);
    }

    public Account saveNewAccount(@NonNull Account account) {
        return accountRepository.save(account);
    }

    public AccountsDTO getUserAccounts(User user){
        return new AccountsDTO(accountRepository.findAllByOwner(user));
    }
}

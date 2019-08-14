package ua.yaskal.model.service;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yaskal.model.dto.AccountsDTO;
import ua.yaskal.model.entity.Account;
import ua.yaskal.model.entity.User;
import ua.yaskal.model.repository.AccountRepository;

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

   /* public AccountsDTO getUserAccounts(User user){
        return new AccountsDTO(accountRepository.findAllByOwner(user));
    }*/
}

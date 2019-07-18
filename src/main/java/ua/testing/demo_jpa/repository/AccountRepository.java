package ua.testing.demo_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.testing.demo_jpa.dto.AccountsDTO;
import ua.testing.demo_jpa.entity.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountById(String id);

    List<Account> findAllByOwnerId(Long id);
}

package ua.yaskal.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.yaskal.model.entity.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByOwnerId(long ownerId);
    List<Account> findAllByOwnerIdAndAccountStatus (long ownerId, Account.AccountStatus status);
}

package ua.yaskal.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.yaskal.model.entity.Account;
import ua.yaskal.model.entity.CreditAccount;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<CreditAccount, Long> {
    List<CreditAccount> findAllByOwnerId(long ownerId);
    List<CreditAccount> findAllByOwnerIdAndAccountStatus(long ownerId, Account.AccountStatus status);

}

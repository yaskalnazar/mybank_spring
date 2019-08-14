package ua.yaskal.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.yaskal.model.entity.Account;
import ua.yaskal.model.entity.DepositAccount;


import java.util.List;

@Repository
public interface DepositRepository extends JpaRepository<DepositAccount, Long> {
    List<DepositAccount> findAllByOwnerId(long ownerId);
    List<DepositAccount> findAllByOwnerIdAndAccountStatus (long ownerId, Account.AccountStatus status);

}

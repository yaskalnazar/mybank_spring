package ua.yaskal.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.yaskal.model.entity.Account;
import ua.yaskal.model.entity.User;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}

package ua.yaskal.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.yaskal.model.entity.Payment;
import ua.yaskal.model.entity.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> getAllBySenderAccountId(long id);
    List<Transaction> getAllByReceiverAccountId(long id);
    List<Transaction> getAllBySenderAccountIdOrReceiverAccountId(long senderId, long receiverId);

}

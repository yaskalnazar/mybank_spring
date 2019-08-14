package ua.yaskal.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.yaskal.model.entity.Payment;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query(nativeQuery = true,
            value = "select * from payments right join accounts on owner_id=:id where payer_account_id=account_id ORDER BY date DESC")
    List<Payment> getAllByPayerId(@Param("id") long id);
    @Query(nativeQuery = true,
            value = "select * from payments right join accounts on owner_id=:id where requester_account_id=account_id ORDER BY date DESC")
    List<Payment> getAllByRequesterId(@Param("id")long id);
}

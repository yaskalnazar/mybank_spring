package ua.yaskal.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.yaskal.model.entity.CreditRequest;

@Repository
public interface CreditRequestRepository extends JpaRepository<CreditRequest, Long> {
}

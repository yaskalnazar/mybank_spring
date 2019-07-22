package ua.testing.demo_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.testing.demo_jpa.entity.CreditRequest;

@Repository
public interface CreditRequestRepository extends JpaRepository<CreditRequest, Long> {
}

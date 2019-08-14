package ua.yaskal.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.yaskal.model.entity.CreditRequest;

import java.util.List;

@Repository
public interface CreditRequestRepository extends JpaRepository<CreditRequest, Long> {
    List<CreditRequest> findAllByCreditRequestStatus(CreditRequest.CreditRequestStatus status);
    List<CreditRequest> findAllByApplicantId(long applicantId);
    List<CreditRequest> findAllByApplicantIdAndCreditRequestStatus(long applicantId, CreditRequest.CreditRequestStatus status);

}

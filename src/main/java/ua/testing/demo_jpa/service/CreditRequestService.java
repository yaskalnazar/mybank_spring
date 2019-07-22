package ua.testing.demo_jpa.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.testing.demo_jpa.entity.CreditRequest;
import ua.testing.demo_jpa.exeptions.NoSuchCreditRequestException;
import ua.testing.demo_jpa.exeptions.NoSuchUserException;
import ua.testing.demo_jpa.repository.CreditRequestRepository;

@Slf4j
@Service
public class CreditRequestService {
    private CreditRequestRepository creditRequestRepository;

    public CreditRequestService(CreditRequestRepository creditRequestRepository) {
        this.creditRequestRepository = creditRequestRepository;
    }


    public Iterable<CreditRequest> getAllCreditRequests() {
        return creditRequestRepository.findAll();
    }

    public CreditRequest loadCreditRequestById(Long id) {
        return creditRequestRepository.findById(id)
                .orElseThrow(() -> new NoSuchCreditRequestException(id.toString()));
    }

    public CreditRequest saveCreditRequest(@NonNull CreditRequest creditRequest){
        return creditRequestRepository.save(creditRequest);
    }
}

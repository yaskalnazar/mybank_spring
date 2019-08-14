package ua.yaskal.model.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.yaskal.model.entity.CreditRequest;
import ua.yaskal.model.exeptions.NoSuchCreditRequestException;
import ua.yaskal.model.repository.CreditRequestRepository;

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

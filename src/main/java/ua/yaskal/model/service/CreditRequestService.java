package ua.yaskal.model.service;

import org.springframework.stereotype.Service;
import ua.yaskal.model.dto.CreditRequestDTO;
import ua.yaskal.model.entity.CreditRequest;
import ua.yaskal.model.exeptions.key.no.such.NoSuchCreditRequestException;
import ua.yaskal.model.repository.CreditRequestRepository;

import java.util.List;

/**
 * This service used for working with credit request instance.
 *
 * @author Nazar Yaskal
 */
@Service
public class CreditRequestService {
    private CreditRequestRepository creditRequestRepository;

    public CreditRequestService(CreditRequestRepository creditRequestRepository) {
        this.creditRequestRepository = creditRequestRepository;
    }

    public CreditRequest createNew(CreditRequestDTO creditRequestDTO) {
        CreditRequest creditRequest = CreditRequest.getBuilder()
                .setApplicantId(creditRequestDTO.getApplicantId())
                .setCreditRate(creditRequestDTO.getCreditRate())
                .setCreditLimit(creditRequestDTO.getCreditLimit())
                .setCreditRequestStatus(CreditRequest.CreditRequestStatus.PENDING)
                .setCreationDate(creditRequestDTO.getCreationDate())
                .build();

        return creditRequestRepository.save(creditRequest);
    }

    public List<CreditRequest> getAll() {
        return creditRequestRepository.findAll();
    }


    public List<CreditRequest> getAllByStatus(CreditRequest.CreditRequestStatus status) {
        return creditRequestRepository.findAllByCreditRequestStatus(status);
    }

    public CreditRequest getById(long id) {
        return creditRequestRepository.findById(id).orElseThrow(NoSuchCreditRequestException::new);
    }

    /*public boolean changeStatus(CreditRequest.CreditRequestStatus status, long id) {
        return daoFactory.createCreditRequestDAO().updateStatusById(status, id);
    }*/

    public List<CreditRequest> getAllByApplicantId(long applicantId){
        return creditRequestRepository.findAllByApplicantId(applicantId);
    }

    public  List<CreditRequest> getAllByApplicantIdAndStatus(long applicantId, CreditRequest.CreditRequestStatus status){
        return creditRequestRepository.findAllByApplicantIdAndCreditRequestStatus(applicantId,status);
    }

    public void delete(long id){
         creditRequestRepository.deleteById(id);
    }


}

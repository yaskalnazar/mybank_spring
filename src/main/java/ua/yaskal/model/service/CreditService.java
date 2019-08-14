package ua.yaskal.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yaskal.model.entity.Account;
import ua.yaskal.model.entity.CreditAccount;
import ua.yaskal.model.entity.CreditRequest;
import ua.yaskal.model.exeptions.key.no.such.NoSuchAccountException;
import ua.yaskal.model.repository.CreditRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * This service used for working with credit accounts instance.
 *
 * @author Nazar Yaskal
 */
@Service
public class CreditService {
    private static final long NUMBER_YEARS_OF_CARD_VALID = 5;
    @Autowired
    private CreditRepository creditRepository;


    public List<CreditAccount> getAll() {
        return creditRepository.findAll();
    }

    public List<CreditAccount> getAllByOwnerId(long ownerId) {
        return creditRepository.findAllByOwnerId(ownerId);
    }

    public CreditAccount addNew(CreditRequest creditRequest) {
        CreditAccount creditAccount = CreditAccount.getBuilder()
                .setBalance(BigDecimal.ZERO)
                .setClosingDate(LocalDate.now().plusYears(NUMBER_YEARS_OF_CARD_VALID))
                .setOwnerId(creditRequest.getApplicantId())
                .setAccountStatus(Account.AccountStatus.ACTIVE)
                .setCreditLimit(creditRequest.getCreditLimit())
                .setCreditRate(creditRequest.getCreditRate())
                .setAccruedInterest(BigDecimal.ZERO)
                .build();

        return creditRepository.save(creditAccount);

    }

    public List<CreditAccount> getAllByOwnerIdAndStatus(long ownerId, Account.AccountStatus status) {
        return creditRepository.findAllByOwnerIdAndAccountStatus(ownerId,status);
    }

    public CreditAccount getById(long id) {
        return creditRepository.findById(id).orElseThrow(NoSuchAccountException::new);
    }

    /*public void increaseAccruedInterestById(long id, BigDecimal accruedInterest) {
        daoFactory.createCreditDAO().reduceAccruedInterestById(id, accruedInterest);
    }*/

   /* public void reduceAccruedInterestById(long id, BigDecimal accruedInterest) {
        daoFactory.createCreditDAO().reduceAccruedInterestById(id, accruedInterest);
    }*/

   /* public PaginationDTO<CreditAccount> getAllPage(long itemsPerPage, long currentPage) {
        return daoFactory.createCreditDAO().getAllPage(itemsPerPage,currentPage);
    }*/


}

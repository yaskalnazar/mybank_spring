package ua.yaskal.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yaskal.model.dto.DepositDTO;
import ua.yaskal.model.entity.Account;
import ua.yaskal.model.entity.DepositAccount;
import ua.yaskal.model.exeptions.key.no.such.NoSuchAccountException;
import ua.yaskal.model.repository.DepositRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * This service used for working with deposit accounts instance.
 *
 * @author Nazar Yaskal
 */
@Service
public class DepositService {
    private static final long NUMBER_YEARS_OF_CARD_VALID = 5;
    @Autowired
    private DepositRepository depositRepository;


    public DepositAccount openNewDeposit(DepositDTO depositDTO) {
        DepositAccount depositAccount = DepositAccount.getBuilder()
                .setBalance(BigDecimal.ZERO)
                .setClosingDate(LocalDate.now().plusYears(NUMBER_YEARS_OF_CARD_VALID))
                .setOwnerId(depositDTO.getOwnerId())
                .setAccountStatus(Account.AccountStatus.ACTIVE)
                .setDepositAmount(depositDTO.getDepositAmount())
                .setDepositRate(depositDTO.getDepositRate())
                .setDepositEndDate(LocalDate.now().plusMonths(depositDTO.getMonthsAmount()))
                .build();

        return depositRepository.save(depositAccount);
    }

    public List<DepositAccount> getAll() {
        return depositRepository.findAll();
    }

    public List<DepositAccount> getAllByOwnerId(long ownerId) {
        return depositRepository.findAllByOwnerId(ownerId);
    }

    public List<DepositAccount> getAllByOwnerIdAndStatus(long ownerId, Account.AccountStatus status) {
        return depositRepository.findAllByOwnerIdAndAccountStatus(ownerId,status);
    }

    public DepositAccount getById(long id) {
        return depositRepository.findById(id).orElseThrow(
                NoSuchAccountException::new);
    }

   /* public PaginationDTO<DepositAccount> getAllPage(long itemsPerPage, long currentPage) {
        return daoFactory.createDepositDAO().getAllPage(itemsPerPage, currentPage);
    }*/


  /*  public void newDepositContribution(NewDepositContributionDTO contributionDTO) {
        daoFactory.createDepositDAO().newDepositContribution(contributionDTO);
    }*/
}

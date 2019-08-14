package ua.yaskal.model.repository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.yaskal.model.entity.Account;
import ua.yaskal.model.entity.CreditAccount;
import ua.yaskal.model.entity.Transaction;
import ua.yaskal.model.exeptions.key.NotEnoughMoneyException;
import ua.yaskal.model.exeptions.key.no.such.NoSuchAccountException;
import ua.yaskal.model.exeptions.key.no.such.NoSuchActiveAccountException;

import java.math.BigDecimal;

@Repository
public class TransactionDAO {
    private final static Logger logger = Logger.getLogger(TransactionDAO.class);

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;


    //TODO Credit Work;
    @Transactional(propagation = Propagation.MANDATORY )
    public void addAmount(Long id, BigDecimal amount) throws NotEnoughMoneyException, NoSuchAccountException {
        Account account = accountRepository.findById(id).orElseThrow(NoSuchActiveAccountException::new);
        if (!account.getAccountStatus().equals(Account.AccountStatus.ACTIVE)){
            throw new NoSuchActiveAccountException();
        }

        BigDecimal newBalance = account.getBalance().add(amount);
        logger.warn("dd "+account.getId()+account.getAccountStatus());

        if (account.getAccountType().equals(Account.AccountType.CREDIT)){
            CreditAccount creditAccount = (CreditAccount) account;
            if (creditAccount.getBalance().add(amount).compareTo(creditAccount.getCreditLimit()) < 0) {
                throw new NotEnoughMoneyException();
            }
        } else {
            if (account.getBalance().add(amount).compareTo(BigDecimal.ZERO) < 0) {
                throw new NotEnoughMoneyException();
            }
        }

        if (account.getBalance().add(amount).compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughMoneyException();
        }

        account.setBalance(newBalance);
        accountRepository.save(account);
    }
    @Transactional(propagation = Propagation.MANDATORY)
    public Transaction saveTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = Exception.class)
    public Transaction sendMoney(Transaction transaction) throws NotEnoughMoneyException, NoSuchAccountException {
        logger.warn("fuck");

        addAmount(transaction.getReceiverAccountId(), transaction.getTransactionAmount());
        addAmount(transaction.getSenderAccountId(), transaction.getTransactionAmount().negate());
        return saveTransaction(transaction);
    }



}

package ua.yaskal.model.service;

import org.springframework.stereotype.Service;
import ua.yaskal.model.entity.Transaction;
import ua.yaskal.model.repository.AccountDAO;
import ua.yaskal.model.repository.TransactionRepository;

import java.util.List;

/**
 * This service used for working with transactions instance.
 *
 * @author Nazar Yaskal
 */
@Service
public class TransactionService {
    private AccountDAO accountDAO;
    private TransactionRepository transactionRepository;

    public TransactionService(AccountDAO accountDAO, TransactionRepository transactionRepository) {
        this.accountDAO = accountDAO;
        this.transactionRepository = transactionRepository;
    }

    public Transaction makeNewTransaction(Transaction transaction) {
        accountDAO.sendMoney(transaction.getSenderAccountId(),transaction.getSenderAccountId(),transaction.getTransactionAmount());
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllByReceiverId(long id) {
        return transactionRepository.getAllByReceiverAccountId(id);
    }

    public List<Transaction> getAllBySenderId(long id) {
        return transactionRepository.getAllBySenderAccountId(id);
    }

    public List<Transaction> getAllByAccountId(long id) {
        return transactionRepository.getAllBySenderAccountIdOrReceiverAccountId(id,id);
    }


    /*public PaginationDTO<Transaction> getPageByAccountId(long id, long itemsPerPage, long currentPage) {
        return daoFactory.createTransactionDAO().getPageByAccountId(id, itemsPerPage, currentPage);
    }*/

}

package ua.yaskal.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.yaskal.model.entity.Transaction;
import ua.yaskal.model.exeptions.key.NotEnoughMoneyException;
import ua.yaskal.model.exeptions.key.no.such.NoSuchActiveAccountException;
import ua.yaskal.model.repository.TransactionDAO;
import ua.yaskal.model.repository.TransactionRepository;

import java.util.List;

/**
 * This service used for working with transactions instance.
 *
 * @author Nazar Yaskal
 */
@Service
public class TransactionService {
    private TransactionDAO transactionDAO;
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionDAO accountDAO, TransactionRepository transactionRepository) {
        this.transactionDAO = accountDAO;
        this.transactionRepository = transactionRepository;
    }

    public Transaction makeNewTransaction(Transaction transaction) {
        return transactionDAO.sendMoney(transaction);
    }

    public List<Transaction> getAllByReceiverId(long id) {
        return transactionRepository.getAllByReceiverAccountId(id);
    }

    public List<Transaction> getAllBySenderId(long id) {
        return transactionRepository.getAllBySenderAccountId(id);
    }

    public List<Transaction> getAllByAccountId(long id) {
        return transactionRepository.getAllBySenderAccountIdOrReceiverAccountId(id, id);
    }


    /*public PaginationDTO<Transaction> getPageByAccountId(long id, long itemsPerPage, long currentPage) {
        return daoFactory.createTransactionDAO().getPageByAccountId(id, itemsPerPage, currentPage);
    }*/

}

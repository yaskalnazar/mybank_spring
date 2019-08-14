package ua.yaskal.model.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.yaskal.model.entity.Account;
import ua.yaskal.model.entity.Transaction;
import ua.yaskal.model.exeptions.key.NotEnoughMoneyException;
import ua.yaskal.model.exeptions.key.no.such.NoSuchAccountException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class AccountDAO {

    @Autowired
    private EntityManager entityManager;

    public Account findById(long id){
        return this.entityManager.find(Account.class, id);
    }

/*    public List<Account> listBankAccountInfo() {
        String sql = "Select new " + Account.class.getName() //
                + "(e.id,e.balance) " //
                + " from " + Account.class.getName() + " e ";
        Query query = entityManager.createQuery(sql, Account.class);
        return query.getResultList();
    }*/
    //TODO Credit Work;
    @Transactional(propagation = Propagation.MANDATORY )
    public void addAmount(Long id, BigDecimal amount) throws NotEnoughMoneyException, NoSuchAccountException {
        Account account = this.findById(id);
        if (account == null) {
            throw new NoSuchAccountException();
        }
        BigDecimal newBalance = account.getBalance().add(amount);
        //if (account.getBalance().add(amount).compareTo(BigDecimal.ZERO) < 0) {
        if (account.getBalance().add(amount).compareTo(BigDecimal.ZERO) < 0) {
            throw new NotEnoughMoneyException();
        }
        account.setBalance(newBalance);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = {NoSuchAccountException.class, NotEnoughMoneyException.class})
    public void sendMoney(Long fromAccountId, Long toAccountId, BigDecimal amount) throws NotEnoughMoneyException, NoSuchAccountException {

        addAmount(toAccountId, amount);
        addAmount(fromAccountId, amount.negate());
    }
}

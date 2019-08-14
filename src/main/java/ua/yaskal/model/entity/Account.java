package ua.yaskal.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * This entity is general template for all accounts.
 *
 * @author Nazar Yaskal
 * @see CreditAccount
 * @see DepositAccount
 */

@Entity
@Inheritance
@SuppressWarnings("ACCOUNT")
@DiscriminatorColumn(name = "accountType",
        discriminatorType = DiscriminatorType.STRING)
@Table( name="accounts",
        uniqueConstraints={@UniqueConstraint(columnNames={"account_id"})})
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "account_id", nullable = false)
    private long id;
    @Column(nullable = false)
    private BigDecimal balance;
    @Column(nullable = false)
    private LocalDate closingDate;
    @Column(nullable = false)
    private long ownerId;
    @Column(nullable = false)
    private AccountStatus accountStatus;

    public abstract AccountType getAccountType();


    public enum AccountStatus {
        ACTIVE,
        CLOSED,
        BLOCKED;
    }

    public enum AccountType {
        CREDIT,
        DEPOSIT;
    }

    public Account(long id, BigDecimal balance, LocalDate closingDate, long ownerId, AccountStatus accountStatus) {
        this.id = id;
        this.balance = balance;
        this.closingDate = closingDate;
        this.ownerId = ownerId;
        this.accountStatus = accountStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}

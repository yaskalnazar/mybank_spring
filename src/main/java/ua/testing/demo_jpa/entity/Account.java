package ua.testing.demo_jpa.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance
@SuperBuilder
@SuppressWarnings("ACCOUNT")
@DiscriminatorColumn(name = "accountType",
        discriminatorType = DiscriminatorType.STRING)
@Table( name="accounts",
        uniqueConstraints={@UniqueConstraint(columnNames={"account_id"})})
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "account_id", nullable = false)
    private long accountId;
    @Column(nullable = false)
    private BigDecimal balance;
    @Column(name = "closing_date", nullable = false)
    private LocalDate closingDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;
    /*@ManyToMany(fetch = FetchType.LAZY)
    private List<Transaction> transactions;*/
    @Column(nullable = false)
    private AccountStatus accountStatus;


    public abstract String getAccountType();


    public enum AccountStatus {
        ACTIVE,
        CLOSED,
        BLOCKED;
    }

    public enum AccountType {
        CREDIT,
        DEPOSIT;
    }


}

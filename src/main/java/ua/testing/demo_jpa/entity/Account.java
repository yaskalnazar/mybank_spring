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
@DiscriminatorColumn(name="accountType",
        discriminatorType = DiscriminatorType.STRING)
/*@Table( name="account",
        uniqueConstraints={@UniqueConstraint(columnNames={"id"})})*/
public abstract class Account {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(nullable = false)
    private BigDecimal balance;
    @Column(name = "closing_date",nullable = false)
    private LocalDate closingDate;
    @Column(nullable = false)
    private long ownerId;
    @Column(nullable = false)
    @ElementCollection(targetClass = Transaction.class, fetch = FetchType.EAGER)
    private List<Transaction> transactions;
    @Column(nullable = false)
    private AccountStatus status;


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

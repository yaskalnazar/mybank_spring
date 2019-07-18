package ua.testing.demo_jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance
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
    private User owner;
    @Column(nullable = false)
    @ElementCollection(targetClass = Transaction.class, fetch = FetchType.EAGER)
    private List<Transaction> transactions;
    @Column(nullable = false)
    private AccountStatus status;



    public enum AccountStatus {
        ACTIVE,
        CLOSED,
        BLOCKED;
    }


}

package ua.yaskal.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table( name="transactions",
        uniqueConstraints={@UniqueConstraint(columnNames={"transaction_id"})})
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "transaction_id", nullable = false)
    private long transactionId;
    @OneToOne
    private Account senderAccount;
    @OneToOne
    private Account receiverAccount;
    @Column(nullable = false)
    private BigDecimal transactionAmount;
    @Column(nullable = false)
    private LocalDate date;
}

package ua.testing.demo_jpa.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table( name="transaction",
        uniqueConstraints={@UniqueConstraint(columnNames={"id"})})
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(nullable = false)
    private long senderAccountId;
    @Column(nullable = false)
    private long receiverAccountId;
    @Column(nullable = false)
    private BigDecimal transactionAmount;
    @Column(nullable = false)
    private LocalDate date;
}

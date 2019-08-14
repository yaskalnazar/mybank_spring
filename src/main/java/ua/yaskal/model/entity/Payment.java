package ua.yaskal.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table( name="payments",
        uniqueConstraints={@UniqueConstraint(columnNames={"payment_id"})})
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "payment_id", nullable = false)
    private long paymentId;
    @OneToOne
    private Account requesterAccount;
    @OneToOne
    private Account payerAccount;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private PaymentStatus paymentStatus;



    public enum PaymentStatus {
        PENDING,
        PAID,
        REJECTED;
    }


}

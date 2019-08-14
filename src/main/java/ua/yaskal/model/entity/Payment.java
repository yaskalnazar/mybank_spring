package ua.yaskal.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * This entity represents the requests from one account to another that USER can send.
 * If the payer agrees a new transaction is created otherwise payment change status to REJECTED.
 * USER can send unlimited number of payments
 * The class has POJO structure.
 *
 * @author Nazar Yaskal
 */
@Entity
@Table(name = "payments",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"payment_id"})})
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "payment_id", nullable = false)
    private long id;
    @Column(nullable = false)
    private long requesterAccountId;
    @Column(nullable = false)
    private long payerAccountId;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    private PaymentStatus paymentStatus;
    private String message;

    public enum PaymentStatus {
        PENDING,
        PAID,
        REJECTED;
    }

    public Payment(long id, long requesterAccountId, long payerAccountId, BigDecimal amount, LocalDateTime date, PaymentStatus paymentStatus, String message) {
        this.id = id;
        this.requesterAccountId = requesterAccountId;
        this.payerAccountId = payerAccountId;
        this.amount = amount;
        this.date = date;
        this.paymentStatus = paymentStatus;
        this.message = message;
    }

    public static PaymentBuilder getBuilder() {
        return new PaymentBuilder();
    }

    /**
     * This class realize pattern Builder for class {@link Payment}
     *
     * @author Nazar Yaskal
     * @see Payment
     */
    public static class PaymentBuilder {
        private long id;
        private long requesterAccountId;
        private long payerAccountId;
        private BigDecimal amount;
        private LocalDateTime date;
        private PaymentStatus paymentStatus;
        private String message;


        public PaymentBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public PaymentBuilder setRequesterAccountId(long requesterAccountId) {
            this.requesterAccountId = requesterAccountId;
            return this;
        }

        public PaymentBuilder setPayerAccountId(long payerAccountId) {
            this.payerAccountId = payerAccountId;
            return this;
        }

        public PaymentBuilder setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public PaymentBuilder setDate(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public PaymentBuilder setPaymentStatus(PaymentStatus paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        public PaymentBuilder setMessage(String message) {
            this.message = message;
            return this;
        }


        public Payment build() {
            return new Payment(id, requesterAccountId, payerAccountId, amount, date, paymentStatus, message);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRequesterAccountId() {
        return requesterAccountId;
    }

    public void setRequesterAccountId(long requesterAccountId) {
        this.requesterAccountId = requesterAccountId;
    }

    public long getPayerAccountId() {
        return payerAccountId;
    }

    public void setPayerAccountId(long payerAccountId) {
        this.payerAccountId = payerAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

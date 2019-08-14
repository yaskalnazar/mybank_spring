package ua.yaskal.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * This entity represents the requests for credit account creation that USER can make to ADMIN.
 * User can have only one active request or active credit account.
 * ADMIN, regardless of the applicant credit history, decides to approve or reject the request
 * The class has POJO structure.
 *
 * @author Nazar Yaskal
 */

@Entity
@Table( name="credit_requests",
        uniqueConstraints={@UniqueConstraint(columnNames={"request_id"})})
public class CreditRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "request_id", nullable = false)
    private long id;
    @Column(nullable = false)
    private long applicantId;
    @Column(nullable = false)
    private BigDecimal creditRate;
    @Column(nullable = false)
    private BigDecimal creditLimit;
    @Column(nullable = false)
    private LocalDateTime creationDate;
    @Column(nullable = false)
    private CreditRequestStatus creditRequestStatus;

    public enum CreditRequestStatus {
        PENDING,
        APPROVED,
        REJECTED;
    }

    public CreditRequest(long id, long applicantId, BigDecimal creditRate, BigDecimal creditLimit,
                         LocalDateTime creationDate, CreditRequestStatus creditRequestStatus) {
        this.id = id;
        this.applicantId = applicantId;
        this.creditRate = creditRate;
        this.creditLimit = creditLimit;
        this.creationDate = creationDate;
        this.creditRequestStatus = creditRequestStatus;
    }

    public static CreditRequestBuilder getBuilder() {
        return new CreditRequestBuilder();
    }


    /**
     * This class realize pattern Builder for class {@link CreditRequest}
     *
     * @author Nazar Yaskal
     * @see CreditRequest
     */
    public static class CreditRequestBuilder {
        private long id;
        private long applicantId;
        private BigDecimal creditRate;
        private BigDecimal creditLimit;
        private LocalDateTime creationDate;
        private CreditRequestStatus creditRequestStatus;

        public CreditRequestBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public CreditRequestBuilder setApplicantId(long applicantId) {
            this.applicantId = applicantId;
            return this;
        }

        public CreditRequestBuilder setCreditRate(BigDecimal creditRate) {
            this.creditRate = creditRate;
            return this;
        }

        public CreditRequestBuilder setCreditLimit(BigDecimal creditLimit) {
            this.creditLimit = creditLimit;
            return this;
        }

        public CreditRequestBuilder setCreationDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public CreditRequestBuilder setCreditRequestStatus(CreditRequestStatus creditRequestStatus) {
            this.creditRequestStatus = creditRequestStatus;
            return this;
        }

        public CreditRequest build() {
            return new CreditRequest(id, applicantId, creditRate, creditLimit, creationDate, creditRequestStatus);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
    }

    public BigDecimal getCreditRate() {
        return creditRate;
    }

    public void setCreditRate(BigDecimal creditRate) {
        this.creditRate = creditRate;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public CreditRequestStatus getCreditRequestStatus() {
        return creditRequestStatus;
    }

    public void setCreditRequestStatus(CreditRequestStatus creditRequestStatus) {
        this.creditRequestStatus = creditRequestStatus;
    }
}

package ua.yaskal.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table( name="credit_requests",
        uniqueConstraints={@UniqueConstraint(columnNames={"request_id"})})
public class CreditRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "request_id", nullable = false)
    private long requestId;
    @ManyToOne(fetch = FetchType.LAZY)
    private User applicant;
    @Column(nullable = false)
    private BigDecimal creditRate;
    @Column(nullable = false)
    private BigDecimal creditLimit;
    @Column(nullable = false)
    private CreditRequestStatus creditRequestStatus;
    @Column(nullable = false)
    private LocalDate creationDate;


    public enum CreditRequestStatus {
        PENDING,
        APPROVED,
        REJECTED;
    }

}

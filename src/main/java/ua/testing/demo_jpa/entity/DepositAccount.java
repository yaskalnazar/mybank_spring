package ua.testing.demo_jpa.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@Entity
@SuppressWarnings("ACCOUNT")
@DiscriminatorValue("DEPOSIT")
public class DepositAccount extends Account {
    private BigDecimal depositAmount;
    private BigDecimal depositRate;
    private LocalDate depositEndDate;
}

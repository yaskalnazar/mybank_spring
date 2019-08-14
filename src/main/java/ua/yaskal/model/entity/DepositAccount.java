package ua.yaskal.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@SuppressWarnings("ACCOUNT")
@DiscriminatorValue("DEPOSIT")
public class DepositAccount extends Account {
    private BigDecimal depositAmount;
    private BigDecimal depositRate;
    private LocalDate depositEndDate;

    @Override
    public String getAccountType() {
        return AccountType.DEPOSIT.name();
    }
}

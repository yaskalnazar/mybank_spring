package ua.testing.demo_jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@SuppressWarnings("ACCOUNT")
@DiscriminatorValue("CREDIT")
public class CreditAccount extends Account {
    private BigDecimal creditRate;
    private BigDecimal creditLimit;
    private BigDecimal accruedInterest;


    @Override
    public String getAccountType() {
        return AccountType.CREDIT.name();
    }
}

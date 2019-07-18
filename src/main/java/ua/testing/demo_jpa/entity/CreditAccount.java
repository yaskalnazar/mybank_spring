package ua.testing.demo_jpa.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@Entity
@SuppressWarnings("ACCOUNT")
@DiscriminatorValue("CREDIT")
public class CreditAccount extends Account {
    private BigDecimal creditRate;
    private BigDecimal creditLimit;
    private BigDecimal accruedInterest;


}

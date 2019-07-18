package ua.testing.demo_jpa.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DepositDTO {
    private BigDecimal depositAmount;
    private BigDecimal depositRate;
    private int monthsAmount;
}

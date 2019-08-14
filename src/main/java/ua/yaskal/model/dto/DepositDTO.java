package ua.yaskal.model.dto;

import lombok.*;

import java.math.BigDecimal;

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

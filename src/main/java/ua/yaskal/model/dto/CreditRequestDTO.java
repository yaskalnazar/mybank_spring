package ua.yaskal.model.dto;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreditRequestDTO {
    private BigDecimal creditRate;
    private BigDecimal creditLimit;
}

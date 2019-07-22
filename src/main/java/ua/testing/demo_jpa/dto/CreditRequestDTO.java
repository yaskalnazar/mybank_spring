package ua.testing.demo_jpa.dto;


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

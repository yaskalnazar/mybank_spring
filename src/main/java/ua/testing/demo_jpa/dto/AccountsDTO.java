package ua.testing.demo_jpa.dto;

import lombok.*;
import ua.testing.demo_jpa.entity.Account;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AccountsDTO {
    List<Account> accounts;
}
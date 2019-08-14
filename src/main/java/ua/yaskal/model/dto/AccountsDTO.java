package ua.yaskal.model.dto;

import lombok.*;
import ua.yaskal.model.entity.Account;

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

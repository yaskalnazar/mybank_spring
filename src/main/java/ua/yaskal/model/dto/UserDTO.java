package ua.yaskal.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDTO {
    private String email;
    private String password;
}

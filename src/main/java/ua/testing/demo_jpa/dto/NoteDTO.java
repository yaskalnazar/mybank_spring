package ua.testing.demo_jpa.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class NoteDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

}

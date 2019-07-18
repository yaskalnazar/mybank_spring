package ua.testing.demo_jpa.controller;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.testing.demo_jpa.configuration.SecurityConfiguration;
import ua.testing.demo_jpa.dto.NoteDTO;
import ua.testing.demo_jpa.entity.User;
import ua.testing.demo_jpa.service.UserService;

import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping(value = "/api")
public class RegFormController {

    private final UserService userService;

    @Autowired
    public RegFormController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private SecurityConfiguration securityConfig;


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/reg_form", method = RequestMethod.POST)
    public void registrationFormController(@NonNull NoteDTO note){
        log.info("{}", note);
            userService.saveNewUser(User.builder()
                    .firstName(note.getFirstName())
                    .lastName(note.getLastName())
                    .email(note.getEmail())
                    .password(securityConfig.bcryptPasswordEncoder().encode(note.getPassword()))
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .authorities(Arrays.asList(User.RoleType.ROLE_USER))
                    .build());


    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("{\"message\": \"" + ex.getMessage() + "\"}");
    }
}

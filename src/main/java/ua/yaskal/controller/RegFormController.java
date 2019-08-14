/*
package ua.yaskal.controller;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.yaskal.controller.configuration.SecurityConfiguration;
import ua.yaskal.model.service.UserService;
import ua.yaskal.model.dto.NoteDTO;
import ua.yaskal.model.entity.User;

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
                    .authorities(Arrays.asList(User.RoleType.ROLE_ADMIN))
                    .build());


    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("{\"message\": \"" + ex.getMessage() + "\"}");
    }
}
*/

package ua.yaskal.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.yaskal.controller.handler.service.UserService;
import ua.yaskal.model.dto.UsersDTO;

@Slf4j
@RestController
@RequestMapping(value = "/")
public class AllUsersController {

    private final UserService userService;

    @Autowired
    public AllUsersController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "user", method = RequestMethod.GET)
    public UsersDTO getAllUser(){
        log.info("{}",userService.getAllUsers());
        return userService.getAllUsers();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("{\"message\": \"" + ex.getMessage() + "\"}");
    }


}

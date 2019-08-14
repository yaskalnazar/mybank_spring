package ua.yaskal.controller.guest;


import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.yaskal.controller.JspPath;
import ua.yaskal.controller.configuration.SecurityConfiguration;
import ua.yaskal.controller.util.ValidationUtil;
import ua.yaskal.model.dto.UserRegistrationDTO;
import ua.yaskal.model.entity.User;
import ua.yaskal.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * This command used for getting registration_form for QUEST and process input.
 * Required params: email, name, surname, patronymic if QUEST SingUp;
 *
 * @author Nazar Yaskal
 */

@Controller
@RequestMapping(value = "/api/guest")
public class RegistrationCommand {
    private final static Logger logger = Logger.getLogger(RegistrationCommand.class);
    private SecurityConfiguration securityConfig;
    private ValidationUtil validationUtil;
    private UserService userService;

    public RegistrationCommand(SecurityConfiguration securityConfig, ValidationUtil validationUtil, UserService userService) {
        this.securityConfig = securityConfig;
        this.validationUtil = validationUtil;
        this.userService = userService;
    }
    @GetMapping(value = "/registration")
    public String getForm() {
        return JspPath.REG_FORM;
    }

    @PostMapping(value = "/registration")
    public String execute(UserRegistrationDTO userDTO, Model model, RedirectAttributes redirectAttributes) {
        if (!isRequestValid(userDTO, model)){
            return JspPath.REG_FORM;
        }

        userService.saveNewUser(User.getBuilder()
                .setEmail(userDTO.getEmail())
                .setPassword(securityConfig.bcryptPasswordEncoder().encode(userDTO.getPassword()))
                .setName(userDTO.getName())
                .setSurname(userDTO.getSurname())
                .setPatronymic(userDTO.getPatronymic())
                .setUserRole(User.Role.USER)
                .build());

        redirectAttributes.addFlashAttribute("regSuccessfully", true);
        redirectAttributes.addFlashAttribute("email", userDTO.getEmail());
        return "redirect:/api/guest/login";
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("{\"message\": \"" + ex.getMessage() + "\"}");
    }

    private boolean isRequestValid(UserRegistrationDTO userDTO, Model model) {
        boolean flag = true;
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();
        String name = userDTO.getName();
        String surname = userDTO.getSurname();
        String patronymic = userDTO.getSurname();

        if (validationUtil.isParamValid(email, "email")) {
            model.addAttribute("email", email);
        } else {
            logger.warn("Registration attempt with incorrect email");
            model.addAttribute("wrongEmail", "wrong.email");
            flag = false;
        }

        if (!validationUtil.isParamValid(password, "password")) {
            logger.warn("Registration attempt with incorrect password");
            model.addAttribute("wrongPassword", "wrong.password");
            flag = false;
        }

        if (validationUtil.isParamValid(name, "name")) {
            model.addAttribute("name", name);
        } else {
            logger.warn("Registration attempt with incorrect name");
            model.addAttribute("wrongName", "wrong.name");
            flag = false;
        }

        if (validationUtil.isParamValid(surname, "surname")) {
            model.addAttribute("surname", surname);
        } else {
            logger.warn("Registration attempt with incorrect surname");
            model.addAttribute("wrongSurname", "wrong.surname");
            flag = false;
        }

        if (validationUtil.isParamValid(patronymic, "patronymic")) {
            model.addAttribute("patronymic", patronymic);
        } else {
            logger.warn("Registration attempt with incorrect patronymic");
            model.addAttribute("wrongPatronymic", "wrong.patronymic");
            flag = false;
        }
        return flag;
    }

}

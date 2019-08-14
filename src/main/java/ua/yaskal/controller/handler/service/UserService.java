package ua.yaskal.controller.handler.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.yaskal.model.dto.UserDTO;
import ua.yaskal.model.dto.UsersDTO;
import ua.yaskal.model.entity.User;
import ua.yaskal.model.exeptions.EmailAlreadyUsedException;
import ua.yaskal.model.exeptions.IncorrectPasswordException;
import ua.yaskal.model.exeptions.NoSuchUserException;
import ua.yaskal.model.exeptions.NoUsersInBdExeption;
import ua.yaskal.model.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;


@Slf4j
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(@NonNull UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UsersDTO getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()){
            log.warn("NoUsersInBdExeption");
            throw new NoUsersInBdExeption("There are no registered users yet.");
        }
        return new UsersDTO(users);
    }

    public User findByUserEmail(@NonNull UserDTO userDTO) {
        return userRepository.findByEmail(userDTO.getEmail());
    }

    public User userAuthorization(@NonNull UserDTO userDTO){
        log.warn("NoSuchUserException");
        User user = findByUserEmail(userDTO);
        if (user == null) {
            log.warn("NoSuchUserException");
            throw new NoSuchUserException("Incorrect data, please try again.");
        } else if (!user.getPassword().equals(userDTO.getPassword())) {
            log.warn("IncorrectPasswordException");
            throw new IncorrectPasswordException("Incorrect data, please try again.");
        }
        log.info("{}", "Authorized"+user);
        return user;
    }

    public User saveNewUser(@NonNull User user) {
        try {
            return userRepository.save(user);
        } catch (Exception ex) {
            int errorCode = 0;
            Throwable specificException = NestedExceptionUtils.getMostSpecificCause(ex);
            if (specificException instanceof SQLException) {
                SQLException sqlException = (SQLException)specificException;
                errorCode = sqlException.getErrorCode();
            }
            if (errorCode == 1062) {
                log.warn("Email already used");
                throw new EmailAlreadyUsedException("This email already used, please try another");
            }
            throw ex;
        }

    }


    @Override
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException(email);
        }
    }

    public User getCurrentUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public User loadUserById(@NonNull Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchUserException(id.toString()));
    }


}

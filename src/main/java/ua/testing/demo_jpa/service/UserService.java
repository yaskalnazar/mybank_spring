package ua.testing.demo_jpa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Service;
import ua.testing.demo_jpa.dto.UserDTO;
import ua.testing.demo_jpa.dto.UsersDTO;
import ua.testing.demo_jpa.entity.User;
import ua.testing.demo_jpa.exeptions.EmailAlreadyUsedException;
import ua.testing.demo_jpa.exeptions.IncorrectPasswordException;
import ua.testing.demo_jpa.exeptions.NoSuchUserException;
import ua.testing.demo_jpa.exeptions.NoUsersInBdExeption;
import ua.testing.demo_jpa.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;


@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
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

    public User findByUserEmail(UserDTO userDTO) {
        return userRepository.findByEmail(userDTO.getEmail());
    }

    public User userAuthorization(UserDTO userDTO){
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

    public void saveNewUser(User user) {
        try {
            userRepository.save(user);
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


}

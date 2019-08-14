package ua.yaskal.model.dto;

/**
 * This DTO used for login user in system
 *
 * @author Nazar Yaskal
 * @see ua.yaskal.controller.guest.LoginController
 */
public class UserLoginDTO {
    private String email;
    private String password;

    public UserLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package ua.yaskal.model.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * This entity represents user in system.
 * The class has POJO structure.
 *
 * @author Nazar Yaskal
 */

@Entity
@Table( name="users",
        uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id", nullable = false)
    private long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String patronymic;
    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "ownerId")
    @OneToMany(fetch = FetchType.LAZY)
    private List<Account> accounts;
    private Role role;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public enum Role implements GrantedAuthority {
        USER,
        ADMIN,
        GUEST;

        public String getStringRole(){
            return name().toLowerCase();
        }

        @Override
        public String getAuthority() {
            return name();
        }
    }

    public static UserBuilder getBuilder() {
        return new UserBuilder();
    }

    public User(long id, String email, String password, String name, String surname, String patronymic, List<Account> accounts, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.accounts = accounts;
        this.role = role;

        //TODO fix
        accountNonExpired=true;
        accountNonLocked=true;
        credentialsNonExpired=true;
        enabled=true;
    }

    /**
     * This class realize pattern Builder for class {@link User}
     *
     * @author Nazar Yaskal
     * @see User
     */
    public static class UserBuilder {
        private long id;
        private String email;
        private String password;
        private String name;
        private String surname;
        private String patronymic;
        private List<Account> accounts;
        private Role role;

        public UserBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public UserBuilder setPatronymic(String patronymic) {
            this.patronymic = patronymic;
            return this;
        }

        public UserBuilder setAccounts(List<Account> accounts) {
            this.accounts = accounts;
            return this;
        }

        public UserBuilder setUserRole(Role role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(id,email,password,name,surname,patronymic,accounts,role);
        }

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

package ua.yaskal.model.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table( name="users",
        uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class User implements UserDetails {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    List<Account> accounts;

    @ElementCollection(targetClass = RoleType.class, fetch = FetchType.EAGER)
    private List<RoleType> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;


    @Override
    public String getUsername() {
        return email;
    }


    public enum RoleType implements GrantedAuthority {
        ROLE_ADMIN,
        ROLE_USER,
        ROLE_ANONYMOUS;

        @Override
        public String getAuthority() {
            return name();
        }
    }
}

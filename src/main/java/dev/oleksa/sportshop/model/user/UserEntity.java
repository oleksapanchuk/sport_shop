package dev.oleksa.sportshop.model.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.oleksa.sportshop.model.user.address.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;
import static javax.persistence.GenerationType.AUTO;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_DEFAULT)
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "First name cannot be empty")
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @NotNull(message = "Last name cannot be empty")
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @NotNull(message = "Email cannot be empty")
    @Email(message = "Invalid email. Please enter a valid email address")
    @Column(name = "email", unique = true, nullable = false, length = 300)
    private String email;

    @NotNull(message = "Password cannot be empty")
    @Column(name = "password", nullable = false, length = 1000)
    private String password;

    @NotNull
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @NotNull
    @OneToOne
    @JoinColumn(name = "gender_id", nullable = false)
    private Gender gender;

    @NotNull
    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @Column(name = "image_url")
    private String imageUrl;

    // Has the user confirmed their email?
    @Column(name = "is_confirmed", nullable = false)
    private Boolean isConfirmed = false;

    // Is the user blocked?
    @Column(name = "is_blocked", nullable = false)
    private Boolean isBlocked = false;

    // Is the user subscribed to the email newsletter?
    @Column(name = "is_subscribed", nullable = false)
    private Boolean isSubscribed = false;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "user_has_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Collection<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private Collection<Address> addresses = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

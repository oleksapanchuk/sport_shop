package dev.oleksa.sportshop.model.user;

import dev.oleksa.sportshop.model.user.address.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    @NotNull
    private Role roleId;
    @NotNull
    private String phone;
    @NotNull
    private Date dateOfBirth;
    private String imageUrl;
    @Column(columnDefinition = "boolean default false")
    private Boolean isConfirmed = false;
    @ManyToMany
    @JoinTable(
            name = "user_address",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Set<Address> addresses = new HashSet<>();
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}

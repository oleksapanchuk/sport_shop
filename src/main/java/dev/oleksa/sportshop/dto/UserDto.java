package dev.oleksa.sportshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Long genderId;
    private Date dateOfBirth;
    private String imageUrl;
    private Boolean isConfirmed;
    private Boolean isBlocked;
    private Boolean isSubscribed;
}

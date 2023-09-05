package dev.oleksa.sportshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Date dateOfBirth;

}

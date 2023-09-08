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
public class UserDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String roleName;
    private String phone;
    private Date dateOfBirth;

}

package dev.oleksa.sportshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDto {
    private Long id;
    private String unitNumber;
    private String streetNumber;
    private String address;
    private String city;
    private Long regionId;
    private String postalCode;
}

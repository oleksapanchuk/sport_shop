package dev.oleksa.sportshop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressRequest {
    private Long userId;
    private String unitNumber;
    private String streetNumber;
    private String addressLine;
    private String city;
    private Long regionId;
    private String postalCode;
    private Boolean isDefault;
}

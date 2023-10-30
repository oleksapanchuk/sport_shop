package dev.oleksa.sportshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDto {
    private Long userId;
    private String accountNumber;
    private String expiryDate;
    private String provider;
    private Boolean isDefault;
}

package dev.oleksa.sportshop.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDto {
    private Long paymentId;
    private Long userId;
    private String accountNumber;
    private String expiryDate;
    private String provider;
    private Boolean isDefault;
}

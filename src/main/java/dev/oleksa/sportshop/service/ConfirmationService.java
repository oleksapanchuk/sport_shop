package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.model.dto.ConfirmationDto;

public interface ConfirmationService {
    ConfirmationDto createConfirmation(ConfirmationDto confirmationDto);

    ConfirmationDto readConfirmation(Long confirmationId);

    ConfirmationDto updateConfirmation(ConfirmationDto confirmationDto);

    Boolean deleteConfirmation(Long confirmationId);
}

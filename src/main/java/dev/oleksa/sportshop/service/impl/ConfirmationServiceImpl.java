package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.mapper.ConfirmationMapper;
import dev.oleksa.sportshop.model.dto.ConfirmationDto;
import dev.oleksa.sportshop.repository.ConfirmationRepository;
import dev.oleksa.sportshop.service.ConfirmationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ConfirmationServiceImpl implements ConfirmationService {

    private final ConfirmationRepository confirmationRepository;
    private final ConfirmationMapper confirmationMapper;

    @Override
    public ConfirmationDto createConfirmation(ConfirmationDto confirmationDto) {
        var confirmation = confirmationMapper.toEntity(confirmationDto);
        confirmation.setCreatedDate(LocalDateTime.now());

        confirmation = confirmationRepository.save(confirmation);

        return confirmationMapper.toDto(confirmation);
    }

    @Override
    public ConfirmationDto readConfirmation(Long confirmationId) {
        return confirmationMapper.toDto(
                confirmationRepository.findById(confirmationId)
                        .orElseThrow()
        );
    }

    @Override
    public ConfirmationDto updateConfirmation(ConfirmationDto confirmationDto) {
        var confirmation = confirmationMapper.toEntity(confirmationDto);

        confirmation = confirmationRepository.save(confirmation);

        return confirmationMapper.toDto(confirmation);
    }

    @Override
    public Boolean deleteConfirmation(Long confirmationId) {
        try {
            confirmationRepository.deleteById(confirmationId);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}

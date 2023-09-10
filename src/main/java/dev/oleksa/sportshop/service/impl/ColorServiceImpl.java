package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.mapper.ColorMapper;
import dev.oleksa.sportshop.model.dto.ColorDto;
import dev.oleksa.sportshop.repository.ColorRepository;
import dev.oleksa.sportshop.service.ColorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;
    private final ColorMapper colorMapper;


    @Override
    public ColorDto createColor(ColorDto colorDto) {
        var color = colorMapper.toEntity(colorDto);

        color = colorRepository.save(color);

        return colorMapper.toDto(color);
    }

    @Override
    public ColorDto readColor(Long colorId) {
        return colorMapper.toDto(
                colorRepository.findById(colorId)
                        .orElseThrow()
        );
    }

    @Override
    public ColorDto updateColor(ColorDto colorDto) {
        var color = colorMapper.toEntity(colorDto);

        color = colorRepository.save(color);

        return colorMapper.toDto(color);
    }

    @Override
    public Boolean deleteColor(Long colorId) {
        try {
            colorRepository.deleteById(colorId);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}

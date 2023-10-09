package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.dto.ColorDto;

public interface ColorService {
    ColorDto createColor(ColorDto colorDto);

    ColorDto readColor(Long colorId);

    ColorDto updateColor(ColorDto colorDto);

    Boolean deleteColor(Long colorId);
}

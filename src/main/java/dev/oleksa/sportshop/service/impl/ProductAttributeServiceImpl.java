package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.dto.ColorDto;
import dev.oleksa.sportshop.dto.SizeDto;
import dev.oleksa.sportshop.mapper.ColorMapper;
import dev.oleksa.sportshop.mapper.SizeMapper;
import dev.oleksa.sportshop.model.product.ProductColor;
import dev.oleksa.sportshop.model.product.ProductSize;
import dev.oleksa.sportshop.model.product.ProductSizeType;
import dev.oleksa.sportshop.repository.ColorRepository;
import dev.oleksa.sportshop.repository.SizeRepository;
import dev.oleksa.sportshop.repository.SizeTypeRepository;
import dev.oleksa.sportshop.service.ProductAttributeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductAttributeServiceImpl implements ProductAttributeService {

    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final SizeTypeRepository sizeTypeRepository;

    private final SizeMapper sizeMapper;
    private final ColorMapper colorMapper;

    @Override
    public SizeDto createSize(SizeDto sizeDto) {
        var size = sizeMapper.toEntity(sizeDto);

        size = sizeRepository.save(size);

        return sizeMapper.toDto(size);
    }

    @Override
    public SizeDto readSize(Long sizeId) {
        return sizeMapper.toDto(
                sizeRepository.findById(sizeId)
                        .orElseThrow()
        );
    }

    @Override
    public SizeDto updateSize(SizeDto sizeDto) {
        var size = sizeMapper.toEntity(sizeDto);

        size = sizeRepository.save(size);

        return sizeMapper.toDto(size);
    }

    @Override
    public Boolean deleteSize(Long sizeId) {
        try {
            sizeRepository.deleteById(sizeId);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

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

    @Override
    public List<ProductColor> getProductColors() {
        return colorRepository.findAll();
    }

    @Override
    public ProductColor getProductColor(Long colorId) {
        return colorRepository.findById(colorId)
                .orElseThrow();
    }

    @Override
    public List<ProductSizeType> getProductSizeTypes() {
        return sizeTypeRepository.findAll();
    }

    @Override
    public ProductSizeType getProductSizeType(Long sizeTypeId) {
        return sizeTypeRepository.findById(sizeTypeId)
                .orElseThrow();
    }

    @Override
    public List<ProductSize> getAllProductSizes() {
        return sizeRepository.findAll();
    }

    @Override
    public List<ProductSize> getProductSizesByTypeId(Long sizeTypeId) {
        return sizeRepository.findByProductSizeTypeId(sizeTypeId);
    }

    @Override
    public ProductSize getProductSize(Long sizeId) {
        return sizeRepository.findById(sizeId)
                .orElseThrow();
    }
}

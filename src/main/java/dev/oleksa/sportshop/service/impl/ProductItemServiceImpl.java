package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.mapper.ProductItemMapper;
import dev.oleksa.sportshop.dto.ProductItemDto;
import dev.oleksa.sportshop.repository.ProductItemRepository;
import dev.oleksa.sportshop.service.ProductItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductItemServiceImpl implements ProductItemService {

    private final ProductItemRepository productItemRepository;
    private final ProductItemMapper productItemMapper;

    @Override
    public ProductItemDto createProductItem(ProductItemDto productItemDto) {
        var productItem = productItemMapper.toEntity(productItemDto);

        productItem = productItemRepository.save(productItem);

        return productItemMapper.toDto(productItem);
    }

    @Override
    public ProductItemDto readProductItem(Long productItemId) {
        return productItemMapper.toDto(
                productItemRepository.findById(productItemId)
                        .orElseThrow()
        );
    }

    @Override
    public ProductItemDto updateProductItem(ProductItemDto productItemDto) {
        var productItem = productItemMapper.toEntity(productItemDto);

        productItem = productItemRepository.save(productItem);

        return productItemMapper.toDto(productItem);
    }

    @Override
    public Boolean deleteProductItem(Long productItemId) {
        try {
            productItemRepository.deleteById(productItemId);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}

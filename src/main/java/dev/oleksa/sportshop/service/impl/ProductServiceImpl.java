package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.mapper.ProductMapper;
import dev.oleksa.sportshop.dto.ProductDto;
import dev.oleksa.sportshop.repository.ProductRepository;
import dev.oleksa.sportshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @Override
    public ProductDto createProduct(ProductDto productDto) {
        var product = productMapper.toEntity(productDto);

        product = productRepository.save(product);

        return productMapper.toDto(product);
    }

    @Override
    public ProductDto readProduct(Long productId) {
        return productMapper.toDto(
                productRepository.findById(productId)
                        .orElseThrow()
        );
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        var product = productMapper.toEntity(productDto);

        product = productRepository.save(product);

        return productMapper.toDto(product);
    }

    @Override
    public Boolean deleteProduct(Long productId) {
        try {
            productRepository.deleteById(productId);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}

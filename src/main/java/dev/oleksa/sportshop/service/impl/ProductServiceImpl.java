package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.mapper.ProductMapper;
import dev.oleksa.sportshop.dto.ProductDto;
import dev.oleksa.sportshop.model.product.Product;
import dev.oleksa.sportshop.model.product.ProductColor;
import dev.oleksa.sportshop.repository.ColorRepository;
import dev.oleksa.sportshop.repository.ProductRepository;
import dev.oleksa.sportshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Page<Product> getProducts(int page, int size, String sortByField, List<String> filterFields) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return productRepository.findAll(pageRequest);
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

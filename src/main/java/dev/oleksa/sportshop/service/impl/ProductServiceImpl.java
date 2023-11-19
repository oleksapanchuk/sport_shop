package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.dto.ProductDto;
import dev.oleksa.sportshop.dto.response.ProductResponse;
import dev.oleksa.sportshop.mapper.ProductMapper;
import dev.oleksa.sportshop.model.product.Product;
import dev.oleksa.sportshop.model.product.ProductHasSize;
import dev.oleksa.sportshop.model.product.ProductItem;
import dev.oleksa.sportshop.repository.ProductItemRepository;
import dev.oleksa.sportshop.repository.ProductRepository;
import dev.oleksa.sportshop.repository.ProductSizesRepository;
import dev.oleksa.sportshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductItemRepository productItemRepository;
    private final ProductSizesRepository productSizesRepository;
    private final ProductMapper productMapper;

    @Override
    public Page<Product> getProducts(int page, int size, String sortByField, List<String> filterFields) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return productRepository.findAll(pageRequest);
    }

    @Override
    public ProductResponse getProductDetailsById(Long productId, String locale) {

        Product product = productRepository.findById(productId)
                .orElseThrow();

        System.out.println(product);

        List<ProductItem> productItems = productItemRepository.findAllByProductId(productId);

        List<Long> colorIdList = new ArrayList<>();

        for (ProductItem pr : productItems) {
            colorIdList.add(pr.getColor().getId());
        }

        ProductItem productItem = productItems.get(0);

        List<ProductHasSize> productSizes = productSizesRepository.findAllByProductItemId(productItem.getId());

        List<Long> availableSizes = new ArrayList<>();

        for (ProductHasSize phs : productSizes) {
            availableSizes.add(phs.getId().getProductSize().getId());
        }

        return ProductResponse.builder()
                .productId(productId)
                .categoryName(
                        locale.equals("eng") ?
                                product.getCategory().getNameEng() :
                                product.getCategory().getNameUa()
                )
                .brandName(product.getBrand().getName())
                .productName(
                        locale.equals("eng") ?
                                product.getNameEng() :
                                product.getNameUa()
                )
                .description(
                        locale.equals("eng") ?
                                product.getDescriptionEng() :
                                product.getDescriptionUa()
                )
                .colorId(productItem.getColor().getId())
                .availableSizeIds(availableSizes)
                .availableColorIds(colorIdList)
                .imageUrl(productItem.getImageUrl())
                .price(product.getPrice())
                .likesNumber(product.getLikesNumber())
                .rating(product.getRating())
                .discounts(product.getDiscounts().stream().toList())
                .build();
    }

    @Override
    public ProductDto readProduct(Long productId) {
        return productRepository.findProductById_Named(productId);
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

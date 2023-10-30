package dev.oleksa.sportshop.service;

import dev.oleksa.sportshop.dto.ColorDto;
import dev.oleksa.sportshop.dto.SizeDto;
import dev.oleksa.sportshop.model.product.ProductColor;
import dev.oleksa.sportshop.model.product.ProductSize;
import dev.oleksa.sportshop.model.product.ProductSizeType;

import java.util.List;

public interface ProductAttributeService {
    SizeDto createSize(SizeDto sizeDto);

    SizeDto readSize(Long sizeId);

    SizeDto updateSize(SizeDto sizeDto);

    Boolean deleteSize(Long sizeId);
    ColorDto createColor(ColorDto colorDto);

    ColorDto readColor(Long colorId);

    ColorDto updateColor(ColorDto colorDto);

    Boolean deleteColor(Long colorId);




    List<ProductColor> getProductColors();
    ProductColor getProductColor(Long colorId);
    List<ProductSizeType> getProductSizeTypes();
    ProductSizeType getProductSizeType(Long sizeTypeId);
    List<ProductSize> getAllProductSizes();
    List<ProductSize> getProductSizesByTypeId(Long sizeTypeId);
    ProductSize getProductSize(Long sizeId);
}

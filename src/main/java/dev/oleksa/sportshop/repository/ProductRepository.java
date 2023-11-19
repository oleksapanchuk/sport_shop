package dev.oleksa.sportshop.repository;

import dev.oleksa.sportshop.dto.ProductDto;
import dev.oleksa.sportshop.model.product.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    @Query(nativeQuery = true)
    ProductDto findProductById_Named(Long id);

}

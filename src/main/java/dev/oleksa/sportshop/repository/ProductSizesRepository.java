package dev.oleksa.sportshop.repository;

import dev.oleksa.sportshop.model.product.ProductHasSize;
import dev.oleksa.sportshop.model.product.ProductSizeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductSizesRepository extends JpaRepository<ProductHasSize, ProductSizeKey> {


    @Modifying
    @Transactional
    @Query("SELECT phs FROM ProductHasSize phs WHERE phs.id.productItem.id =  :id")
    List<ProductHasSize> findAllByProductItemId(@Param("id") Long id);

}

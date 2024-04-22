package com.astart.app.persistence.repository.products;

import com.astart.app.persistence.entity.products.ProductsSuppliesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ProductsSuppliesRepository extends ListCrudRepository<ProductsSuppliesEntity, Integer> {


    @Query(value = "SELECT * FROM products_supplies WHERE product_id=?1", nativeQuery = true)
    List<ProductsSuppliesEntity> getSuppliesByProductId(Integer product_id);


}

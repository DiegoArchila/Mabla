package com.astart.app.persistence.repository.products;

import com.astart.app.persistence.entity.products.ProductsSuppliesEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductsSuppliesRepository extends ListCrudRepository<ProductsSuppliesEntity, Integer> {

}

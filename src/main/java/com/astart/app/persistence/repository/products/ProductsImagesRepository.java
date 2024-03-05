package com.astart.app.persistence.repository.products;

import com.astart.app.persistence.entity.products.ProductsImagesEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductsImagesRepository extends ListCrudRepository<ProductsImagesEntity, Integer> {
}

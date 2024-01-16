package com.astart.app.persistence.repository.products;

import com.astart.app.persistence.entity.products.ProductsGroupsEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductsGroupsRepository extends ListCrudRepository<ProductsGroupsEntity, Integer> {
}

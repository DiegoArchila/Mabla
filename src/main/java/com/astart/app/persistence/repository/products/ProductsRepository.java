package com.astart.app.persistence.repository.products;

import com.astart.app.persistence.entity.products.ProductsEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;


public interface ProductsRepository extends ListCrudRepository<ProductsEntity, Integer> {

    List<ProductsEntity> findAllByActiveTrueOrderByNameAsc();
}

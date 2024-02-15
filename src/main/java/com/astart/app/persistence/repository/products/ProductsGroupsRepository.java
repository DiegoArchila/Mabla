package com.astart.app.persistence.repository.products;

import com.astart.app.persistence.entity.products.ProductsGroupsEntity;
import com.astart.app.persistence.entity.products.UnitMeasureEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductsGroupsRepository extends ListCrudRepository<ProductsGroupsEntity, Integer> {

    @Modifying
    @Override
    @Query(value = "UPDATE products_groups SET deleted_at=NOW() WHERE id=?1", nativeQuery = true)
    void deleteById(Integer id);

    @Query(
            value = "SELECT * FROM products_groups WHERE CONCAT(name, ' ', description) LIKE %?1%",
            nativeQuery = true
    )
    Optional<List<ProductsGroupsEntity>> search(String q);

}

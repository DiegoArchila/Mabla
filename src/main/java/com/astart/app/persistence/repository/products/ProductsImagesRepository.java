package com.astart.app.persistence.repository.products;

import com.astart.app.persistence.entity.products.ProductsImagesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductsImagesRepository extends ListCrudRepository<ProductsImagesEntity, Integer> {

    @Query(
            value = "SELECT * FROM products_images WHERE CONCAT(name, ' ', description) ILIKE %?1%",
            nativeQuery = true
    )
    Optional<List<ProductsImagesEntity>> search(String q);

    @Override
    @Query(value = "DELETE FROM products_images WHERE id=?1", nativeQuery = true)
    void deleteById(Integer integer);

    @Query(value = "SELECT * FROM products_images WHERE product_id=?1", nativeQuery = true)
    Optional<List<ProductsImagesEntity>> getImagesByProduct(Integer id);
}

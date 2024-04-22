package com.astart.app.persistence.repository.products;

import com.astart.app.persistence.entity.products.SuppliesEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface SuppliesRepository extends ListCrudRepository<SuppliesEntity, Integer> {

    @Modifying
    @Override
    @Query(value = "UPDATE supplies SET deleted_at=NOW() WHERE id=?1", nativeQuery = true)
    void deleteById(Integer id);

    @Query(
            value = "SELECT * FROM supplies WHERE deleted_at IS NULL AND CONCAT(name, ' ', description) ILIKE %?1%",
            nativeQuery = true
    )
    Optional<List<SuppliesEntity>> search(String q);

    @Query(value = "SELECT * FROM supplies WHERE deleted_at IS NULL", nativeQuery = true)
    List<SuppliesEntity> findAllNotDeleted();
}

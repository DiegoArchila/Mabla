package com.astart.app.persistence.repository.products;

import com.astart.app.persistence.entity.products.UnitMeasureEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UnitMeasureRepository extends ListCrudRepository<UnitMeasureEntity, Integer> {

    @Modifying
    @Override
    @Query(value = "UPDATE unit_measure SET deleted_at=NOW() WHERE id=?1", nativeQuery = true)
    void deleteById(Integer id);

    @Query(
            value = "SELECT * FROM unit_measure WHERE CONCAT(name, ' ', symbol) ILIKE %?1%",
            nativeQuery = true
    )
    Optional<List<UnitMeasureEntity>> search(String q);

}
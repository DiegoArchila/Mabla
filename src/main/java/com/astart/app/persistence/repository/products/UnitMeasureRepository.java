package com.astart.app.persistence.repository.products;

import com.astart.app.persistence.entity.products.UnitMeasureEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface UnitMeasureRepository extends ListCrudRepository<UnitMeasureEntity, Integer> {
}

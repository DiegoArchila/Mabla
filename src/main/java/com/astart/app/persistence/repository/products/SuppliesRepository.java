package com.astart.app.persistence.repository.products;

import com.astart.app.persistence.entity.products.SuppliesEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface SuppliesRepository extends ListCrudRepository<SuppliesEntity, Integer> {
}

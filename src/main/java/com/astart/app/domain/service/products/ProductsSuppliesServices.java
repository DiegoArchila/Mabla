package com.astart.app.domain.service.products;

import com.astart.app.persistence.entity.products.ProductsSuppliesEntity;
import com.astart.app.persistence.repository.products.ProductsSuppliesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductsSuppliesServices {

    private final ProductsSuppliesRepository productsSuppliesRepository;

    @Autowired
    public ProductsSuppliesServices(ProductsSuppliesRepository productsSuppliesRepository) {
        this.productsSuppliesRepository = productsSuppliesRepository;
    }

    public Boolean save(ProductsSuppliesEntity productsSupplies){

        ProductsSuppliesEntity temp= this.productsSuppliesRepository.save(productsSupplies);
        return this.productsSuppliesRepository.existsById(temp.getId());

    }

    public List<ProductsSuppliesEntity> getSuppliesByProduct(Integer product_id){
        return this.productsSuppliesRepository.getSuppliesByProductId(product_id);
    }

}

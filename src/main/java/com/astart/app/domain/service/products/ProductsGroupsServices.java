package com.astart.app.domain.service.products;

import com.astart.app.persistence.entity.products.ProductsGroupsEntity;
import com.astart.app.persistence.repository.products.ProductsGroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsGroupsServices {

    private final ProductsGroupsRepository productsGroupsRepository;

    @Autowired
    public ProductsGroupsServices(ProductsGroupsRepository productsGroupsRepository){
        this.productsGroupsRepository=productsGroupsRepository;
    }

    /**
     * Find or get all list of elements from entities Products Groups
     * @return List of all Products Groups
     */
    public List<ProductsGroupsEntity> getAll(){
        return this.productsGroupsRepository.findAll();
    }
}

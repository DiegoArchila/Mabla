package com.astart.app.domain.service.products;

import com.astart.app.persistence.entity.products.ProductsGroupsEntity;
import com.astart.app.persistence.entity.products.ProductsImagesEntity;
import com.astart.app.persistence.repository.products.ProductsImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class ProductsImagesServices {


    private final ProductsImagesRepository  productsImagesRepository;

    @Autowired
    public ProductsImagesServices(ProductsImagesRepository productsImagesRepository) {
        this.productsImagesRepository = productsImagesRepository;
    }

    public Boolean Save(ProductsImagesEntity productsImagesEntity, MultipartFile imagen) {

        return false;
    }
}

package com.astart.app.domain.service.products;

import com.astart.app.paths.PathsProject;
import com.astart.app.persistence.entity.products.ProductsImagesEntity;
import com.astart.app.persistence.repository.products.ProductsImagesRepository;
import com.astart.app.utils.ImagesOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Service
@Transactional
public class ProductsImagesServices {


    private final ProductsImagesRepository  productsImagesRepository;

    @Autowired
    public ProductsImagesServices(ProductsImagesRepository productsImagesRepository) {
        this.productsImagesRepository = productsImagesRepository;
    }

    public Boolean Save(ProductsImagesEntity productsImagesEntity, MultipartFile image) {

        productsImagesEntity.setPath(
                String.valueOf(ImagesOptimizer.getOptimized(
                        image, Path.of(PathsProject.IMAGES_PATH_PRODUCTS.toAbsolutePath().toString())
                ))
        );

        if (productsImagesEntity.getPath() != "" || productsImagesEntity.getPath()==null){
            ProductsImagesEntity temp = this.productsImagesRepository.save(productsImagesEntity);
            return this.productsImagesRepository.existsById(temp.getId());
        }

        return false;
    }
}

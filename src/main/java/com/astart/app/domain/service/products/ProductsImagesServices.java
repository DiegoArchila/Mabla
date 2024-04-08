package com.astart.app.domain.service.products;

import com.astart.app.paths.PathsProject;
import com.astart.app.persistence.entity.products.ProductsImagesEntity;
import com.astart.app.persistence.repository.products.ProductsImagesRepository;
import com.astart.app.utils.ImagesOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductsImagesServices {


    private final ProductsImagesRepository  productsImagesRepository;

    @Autowired
    public ProductsImagesServices(ProductsImagesRepository productsImagesRepository) {
        this.productsImagesRepository = productsImagesRepository;
    }

    public Boolean Save(ProductsImagesEntity productsImagesEntity, MultipartFile image) throws RuntimeException {

        try {

            productsImagesEntity.setPath(
                    String.valueOf(ImagesOptimizer.getOptimized(
                            image, Path.of(PathsProject.IMAGES_PATH_PRODUCTS.toAbsolutePath().toString())
                    ))

            );

            System.out.println("Image created, path:"+ productsImagesEntity.getPath());

            if (productsImagesEntity.getPath() != "" || productsImagesEntity.getPath()!=null){
                ProductsImagesEntity temp = this.productsImagesRepository.save(productsImagesEntity);
                return this.productsImagesRepository.existsById(temp.getId());
            }

        } catch (Exception e){
            File file = new File(productsImagesEntity.getPath());

            if (file.exists()) {
                file.delete();
                System.out.println("image deleted");
            }

            file=null;

            throw new RuntimeException("Error on create the image: "+ e.getMessage());

        }

        return false;
    }

    public Optional<ProductsImagesEntity> getById(Integer id){
        return this.productsImagesRepository.findById(id);
    }

    public Boolean update(ProductsImagesEntity productImage){
        Optional<ProductsImagesEntity> old = this.productsImagesRepository.findById(productImage.getId());
        return old.equals(this.productsImagesRepository.save(productImage));
    }

    public Boolean delete(Integer id){
        if (this.productsImagesRepository.existsById(id)) {
            this.productsImagesRepository.deleteById(id);
            if (this.productsImagesRepository.existsById(id)){
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Optional<List<ProductsImagesEntity>> getImagesByProducts(Integer productId){
        Optional<List<ProductsImagesEntity>> result = this.productsImagesRepository.getImagesByProduct(productId);

        return result;
    }

}

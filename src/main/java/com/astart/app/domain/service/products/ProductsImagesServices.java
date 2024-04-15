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
    private File file;

    @Autowired
    public ProductsImagesServices(ProductsImagesRepository productsImagesRepository) {
        this.productsImagesRepository = productsImagesRepository;
    }

    public Boolean Save(Integer product_id, MultipartFile[] images) {

        ImagesOptimizer imagesOptimizer= new ImagesOptimizer();
        ProductsImagesEntity[] dataImages = new ProductsImagesEntity[images.length];

        for (int i=0;i<images.length;i++) {

            dataImages[i] = new ProductsImagesEntity();

            try {

                dataImages[i].setProduct_id(product_id);
                dataImages[i].setPath(
                        String.valueOf(imagesOptimizer.getOptimized(
                                images[i], Path.of(PathsProject.IMAGES_PATH_PRODUCTS.toAbsolutePath().toString())
                        ))
                );

                System.out.println("Image created, path:"+ dataImages[i].getPath());

                this.productsImagesRepository.save(dataImages[i]);

                dataImages[i]=null;

            } catch (RuntimeException e) {
                this.deleteImage(dataImages[i].getPath());
                throw new RuntimeException("Error on create the image: " + e.getMessage());
            }
        }

        return true;
    }

    /**
     * Search an image and return the object with the data from image
     * @param id
     * @return
     */
    public Optional<ProductsImagesEntity> getById(Integer id){
        return this.productsImagesRepository.findById(id);
    }

    /**
     * Update the image
     * @param productImage
     * @return
     */
    public Boolean update(ProductsImagesEntity productImage){
        Optional<ProductsImagesEntity> old = this.productsImagesRepository.findById(productImage.getId());
        return old.equals(this.productsImagesRepository.save(productImage));
    }

    /**
     * Delete the Image
     * @param id
     * @return
     */
    public Boolean delete(Integer id){
        if (this.productsImagesRepository.existsById(id)) {
            // Get the image
            Optional<ProductsImagesEntity> res = this.getById(id);
            this.productsImagesRepository.deleteById(id);
            if ( !this.productsImagesRepository.existsById(id) ) {
                this.deleteImage(res.get().getPath());
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Optional<List<ProductsImagesEntity>> getImagesByProducts(Integer productId){
        Optional<List<ProductsImagesEntity>> result = this.productsImagesRepository.getImagesByProduct(productId);

        return result;
    }

    /**
     * Delete the image from the disk
     * @param path
     * @return true if the deleted is successfully
     */
    private boolean deleteImage(String path){
        try{
            this.file = new File(path);

            if (this.file.exists()) {
                this.file.delete();
                return true;
            } else {
                return false;
            }

        } catch(RuntimeException e){
            throw new RuntimeException("Error on delete the image: "+ e.getMessage());
        } finally {
            this.file=null;
        }

    }

}

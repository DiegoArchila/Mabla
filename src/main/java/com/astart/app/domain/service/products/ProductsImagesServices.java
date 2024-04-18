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
     * Delete the Image
     * @param id
     * @return
     */
    public Boolean delete(Integer[] list){

//        Integer[] listIds =new Integer[list.length];

        ProductsImagesEntity[] res= new ProductsImagesEntity[list.length];

        for (int i=0; i< list.length; i++){
            if (this.productsImagesRepository.existsById(list[i])) {

                // Get the image
                res[i] = new ProductsImagesEntity();
                res[i] = this.productsImagesRepository.getById(list[i]).get();

                System.out.println("[PREPARED-TO-DELETE]: "+ res[i].getId());
                System.out.println("List Position, ID:"+list[i]);

                this.productsImagesRepository.deleteById(res[i].getId());
                if ( !this.productsImagesRepository.existsById(list[i]) ) {
                    this.deleteImage(res[i].getPath());
                }
            }
        }

        return true;

    }


    public Optional<List<ProductsImagesEntity>> getImagesByProduct(Integer productId){
        Optional<List<ProductsImagesEntity>> result = this.productsImagesRepository.getImagesByProduct(productId);
        System.out.println("[DEBUG] Resultado Query: "+ result.get());

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

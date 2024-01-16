package com.astart.app.domain.service.products;

import com.astart.app.persistence.entity.products.ProductsEntity;
import com.astart.app.persistence.repository.products.ProductsRepository;
import com.astart.app.domain.dto.products.ProductsResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsServices {

    private final ProductsRepository productsRepository;

    @Autowired
    public ProductsServices(ProductsRepository productsRepository) {
        this.productsRepository=productsRepository;
    }

    /**
     * Find or get all list of elements from entities Products
     * @return List of all Products
     */
    public List<ProductsEntity> getAll(){

        ProductsResponses productsResponses = new ProductsResponses();


        return this.productsRepository.findAllByActiveTrueOrderByNameAsc();

    }

    /**
     * Find Product by Id
     * @param id Id from product
     * @return the product found or else null if isn't found.
     */
    public ProductsResponses getId(Integer id){

        ProductsEntity tempProduct = productsRepository.findById(id).orElse(null);

        ProductsResponses product =new ProductsResponses();

        product.setId(tempProduct.getId());
        product.setName(tempProduct.getName());
        product.setDescription(tempProduct.getDescription());
        product.setSku(tempProduct.getSku());
        product.setGroup(tempProduct.getGroup().getName());
        product.setUnitMeasure(tempProduct.getUnitMeasure().getSymbol());
        product.setNotes(tempProduct.getNotes());

        return product;
    }

    /**
     * Save a Product
     *
     * @param product Product to save
     * @return Product saved
     */
    public Optional<ProductsEntity> save(ProductsEntity product){
        productsRepository.save(product);
        return productsRepository.findById(product.getId());
    }

    /**
     * Edit a product
     * @param product the product to edit
     * @return the product edited

    public ProductsEntity edit(ProductsEntity product) {
        return ProductsEntity;
    }*/

    /**
     * Delete a product by Id
     * @param Id Id from product to delete
     * @return return True if the product is deleted or False else it
     */
    public boolean deletedById(Integer id){
        if(existsById(id)){
            productsRepository.deleteById(id);
            if (existsById(id)){
                return false;
            }else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Validate if exists a product by Id
     * @param id Id from product
     * @return True if product exists or else False if not exits it
     */
    private boolean existsById(Integer id) {
        Optional<ProductsEntity> temp = productsRepository.findById(id);
        if(!temp.isEmpty()) {
            if (temp.get().getActive() && temp.get().getDeleted_at()==null) {
                return true;
            } else {
                return false;
            }
        }else {
            return false;
        }
    }
}

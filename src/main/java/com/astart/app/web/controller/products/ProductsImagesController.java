package com.astart.app.web.controller.products;

import com.astart.app.domain.service.products.ProductsImagesServices;
import com.astart.app.persistence.entity.products.ProductsImagesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/products/images")
public class ProductsImagesController {

    private final ProductsImagesServices productsImagesServices;

    @Autowired
    public ProductsImagesController(ProductsImagesServices productsImagesServices) {
        this.productsImagesServices = productsImagesServices;
    }

    @PostMapping("/save")
    public ResponseEntity save(
            @RequestParam String name,
            @RequestParam Integer product_id,
            @RequestParam MultipartFile image
    ) throws Exception {

        ProductsImagesEntity productsImages = new ProductsImagesEntity();
        productsImages.setName(name);
        productsImages.setProduct_id(product_id);

        if (this.productsImagesServices.Save(productsImages, image)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductsImagesEntity> getById(@PathVariable Integer id){
        Optional<ProductsImagesEntity> result = this.productsImagesServices.getById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/edit")
    public ResponseEntity update(@RequestBody ProductsImagesEntity productsImages){
        if (this.productsImagesServices.update(productsImages)) {
            return ResponseEntity.status(HttpStatus.OK).body("edited successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Not updated it");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(Integer id){
        if (this.productsImagesServices.delete(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                    .body("Not deleted, register not found or error found while try deleted it");
        }
    }

    @PostMapping("")
    public ResponseEntity getImagesByProduct(Integer productId){
        Optional<List<ProductsImagesEntity>> result = this.productsImagesServices.getImagesByProducts(productId);
        if (result.isPresent()){
            ResponseEntity.ok(result.get());
        } else {
            ResponseEntity.notFound();
        }
    }


}

package com.astart.app.web.controller.products;

import com.astart.app.domain.service.products.ProductsImagesServices;
import com.astart.app.persistence.entity.products.ProductsImagesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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




}

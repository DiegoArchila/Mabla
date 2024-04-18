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
@RequestMapping("/products")
public class ProductsImagesController {

    private final ProductsImagesServices productsImagesServices;

    @Autowired
    public ProductsImagesController(ProductsImagesServices productsImagesServices) {
        this.productsImagesServices = productsImagesServices;
    }

    @PostMapping("/{id}/images/save")
    public ResponseEntity save(
            @PathVariable(name = "id") Integer id,
            @RequestParam MultipartFile[] images
    ) throws Exception {

        if (this.productsImagesServices.Save((Integer) id, images)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}/images")
    public ResponseEntity<List<ProductsImagesEntity>> getByProduct(@PathVariable Integer id){
        Optional<List<ProductsImagesEntity>> result = this.productsImagesServices.getImagesByProduct((Integer) id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}/images/delete")
    public ResponseEntity delete(@RequestBody Integer[] id){
        if (this.productsImagesServices.delete(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                    .body("Not deleted, register not found or error found while try deleted it");
        }
    }
    
}

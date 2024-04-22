package com.astart.app.web.controller.products;

import com.astart.app.domain.service.products.ProductsSuppliesServices;
import com.astart.app.persistence.entity.products.ProductsSuppliesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsSuppliesController {

    private final ProductsSuppliesServices productsSuppliesServices;

    @Autowired
    public ProductsSuppliesController(ProductsSuppliesServices productsSuppliesServices) {
        this.productsSuppliesServices = productsSuppliesServices;
    }

    @PostMapping("/supplies/save")
    public ResponseEntity save(@RequestBody ProductsSuppliesEntity productsSupplies){
        if (this.productsSuppliesServices.save(productsSupplies)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
    }

    @GetMapping("/{id}/supplies")
    public ResponseEntity getSuppliesByProductId(@PathVariable("id") Integer product_id) {
        return ResponseEntity.ok(this.productsSuppliesServices.getSuppliesByProduct(product_id));
    }

    @DeleteMapping("/supplies/delete")
    public ResponseEntity delete(@RequestBody Integer productSupply_id){
        if(this.productsSuppliesServices.delete(productSupply_id)){
            return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                    .body("Not deleted, register not found or error found while try deleted it");
        }
    }

    @PostMapping("/supplies/update")
    public ResponseEntity update (@RequestBody ProductsSuppliesEntity productsSupplies){
        this.productsSuppliesServices.update(productsSupplies);
        return ResponseEntity.ok().build();
    }
}
package com.astart.app.web.controller.products;

import com.astart.app.persistence.entity.products.ProductsEntity;
import com.astart.app.domain.service.products.ProductsServices;
import com.astart.app.domain.dto.products.ProductsResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductsServices productsServices;

    @Autowired
    public ProductsController(ProductsServices productsServices){
      this.productsServices=productsServices;
    }

    @GetMapping("")
    public ResponseEntity<List<ProductsEntity>> getAll() {
      return ResponseEntity.ok(productsServices.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductsResponses> getById(@PathVariable Integer id) {
      ProductsResponses product=this.productsServices.getId(id);

      return ResponseEntity.ok(product);
    }

    @PostMapping("/save")
    public ResponseEntity<ProductsEntity> save(@RequestBody ProductsEntity product){
      return  ResponseEntity.ok(this.productsServices.save(product).orElse(null));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) {
      if (this.productsServices.deletedById(id)) {
        return ResponseEntity.accepted().build();
      } else {
        return new ResponseEntity<String>("the product was not deleted", HttpStatus.NOT_MODIFIED);
      }

    }

    @PostMapping("/update")
    public ResponseEntity<Optional<ProductsEntity>> edit(@RequestBody ProductsEntity product) {
      return ResponseEntity.ok(productsServices.save(product));
    }


}

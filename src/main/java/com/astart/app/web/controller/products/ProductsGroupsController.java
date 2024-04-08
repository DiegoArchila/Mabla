package com.astart.app.web.controller.products;

import com.astart.app.persistence.entity.products.ProductsGroupsEntity;
import com.astart.app.domain.service.products.ProductsGroupsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products/groups")
public class ProductsGroupsController {

  private final ProductsGroupsServices productsGroupsServices;

  @Autowired
  public ProductsGroupsController(ProductsGroupsServices productsGroupsServices){
    this.productsGroupsServices=productsGroupsServices;
  }

  @GetMapping("")
  public ResponseEntity<List<ProductsGroupsEntity>> getAll(){
    return ResponseEntity.ok(this.productsGroupsServices.getAll());
  }

  @PostMapping("/save")
  public ResponseEntity save(@RequestBody ProductsGroupsEntity productsGroupsEntity){
    if ((this.productsGroupsServices.Save(productsGroupsEntity))) {
      return ResponseEntity.status(HttpStatus.CREATED).build();
    } else {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductsGroupsEntity> getById(@PathVariable Integer id){
    Optional<ProductsGroupsEntity> result = this.productsGroupsServices.getById(id);
    if (result.isPresent()){
      return ResponseEntity.ok(result.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @PutMapping("/edit")
  public ResponseEntity update(@RequestBody ProductsGroupsEntity productsGroups){
    if (this.productsGroupsServices.update(productsGroups)) {
      return ResponseEntity.status(HttpStatus.OK).body("edited successfully");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Not updated it");
    }
  }


  @DeleteMapping("/delete")
  public ResponseEntity delete(@RequestBody Integer id){
    if (this.productsGroupsServices.delete((Integer) id)){
      return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
              .body("Not deleted, register not found or error found while try deleted it");
    }
  }

  @GetMapping("/search")
  public ResponseEntity<List<ProductsGroupsEntity>> search(@RequestParam String q){
    Optional<List<ProductsGroupsEntity>> result= productsGroupsServices.search(q);
    if (result.isPresent()){
      return ResponseEntity.ok(result.get());
    } else {
      return (ResponseEntity<List<ProductsGroupsEntity>>) ResponseEntity.status(HttpStatus.NOT_FOUND);
    }
  }

}

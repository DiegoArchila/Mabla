package com.astart.app.web.controller.products;

import com.astart.app.persistence.entity.products.ProductsGroupsEntity;
import com.astart.app.domain.service.products.ProductsGroupsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products/groups/")
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


}

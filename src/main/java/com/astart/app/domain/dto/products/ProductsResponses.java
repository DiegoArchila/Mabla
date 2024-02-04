package com.astart.app.domain.dto.products;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductsResponses {

  private Integer id;
  private String name;
  private String description;
  private String sku;
  private String[] groups;
  private String unitMeasure;
  private String notes;


  public ProductsResponses(){
  }

}

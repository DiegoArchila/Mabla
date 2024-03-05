package com.astart.app.domain.dto.products;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductsResponses implements Serializable {

  private Integer id;
  private String name;
  private String description;
  private String sku;
  private String group;
  private String unitMeasure;
  private String notes;


  public ProductsResponses(){
  }

}

package com.astart.app.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(
        name = "supplies",
        schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class ProductsSupplies {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(
          name = "product_id",
          nullable = false
  )
  @ManyToOne
  private Products product;

  @Column(
          name = "supply_id",
          nullable = false
  )
  @ManyToOne
  private Supplies supply;

  @Column(
          name = "um_id",
          nullable = false
  )
  @ManyToOne
  private UnitMeasure unit_measure;

  @Column(
          name = "amount",
          nullable = false
  )
  private Float amount;

  @Column(
          name = "created_at",
          columnDefinition = "DEFAULT current_timestamp",
          nullable = false
  )
  private Date created_at;

  @Column(
          name = "updated_at",
          nullable = true
  )
  private Date updated_at;

  @Column(
          name = "deleted_at",
          nullable = true
  )
  private Date deleted_at;

  /**
   * METHODS: Don't using methods, using Lombok
   */


}

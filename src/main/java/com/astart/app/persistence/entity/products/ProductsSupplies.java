package com.astart.app.persistence.entity.products;

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
  private Integer product_id;

  @Column(
          name = "supply_id",
          nullable = false
  )
  private Integer supply_id;

  @Column(
          name = "um_id",
          nullable = false
  )
  private Integer um_id;

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
   * Relations
   */

  @ManyToOne
  @JoinColumn(name = "product_id",
          referencedColumnName = "id",
          insertable = false,
          updatable = false
  )
  private Products product;


  @ManyToOne
  @JoinColumn(name = "supply_id",
          referencedColumnName = "id",
          insertable = false,
          updatable = false
  )
  private Supplies supply;

  @ManyToOne
  @JoinColumn(name = "um_id",
          referencedColumnName = "id",
          insertable = false,
          updatable = false
  )
  private UnitMeasure unitMeasure;

  /**
   * METHODS: Don't using methods, using Lombok
   */


}

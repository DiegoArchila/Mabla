package com.astart.app.persistence.entity.products;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
//@SQLDelete(sql = "UPDATE products_supplies SET deleted_at=NOW(), updated_at=NOW() WHERE id=?")
@Table(
        name = "products_supplies",
        schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class ProductsSuppliesEntity {

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
          nullable = false,
          updatable = false
  )
  @CreationTimestamp()
  private Date created_at;

  @Column(
          name = "updated_at",
          nullable = true
  )
  @UpdateTimestamp()
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
  private ProductsEntity product;


  @ManyToOne
  @JoinColumn(name = "supply_id",
          referencedColumnName = "id",
          insertable = false,
          updatable = false
  )
  private SuppliesEntity supply;

  @ManyToOne
  @JoinColumn(name = "um_id",
          referencedColumnName = "id",
          insertable = false,
          updatable = false
  )
  private UnitMeasureEntity unitMeasure;

  /**
   * METHODS: Don't using methods, using Lombok
   */


}

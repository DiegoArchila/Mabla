package com.astart.app.persistence.entity.products;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(
        name = "ProductsImages",
        schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class ProductsImages {

  /**
   * FIELDS
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(
          name = "name",
          length = 256,
          unique = true,
          nullable = false
  )
  private String name;

  @Column(
          name = "path",
          length = 256,
          nullable = false
  )
  private String path;

  @Column(
          name = "product_id",
          nullable = false
  )
  private Integer product_id;

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

  /**
   * METHODS: Don't using methods, using Lombok
   */
}

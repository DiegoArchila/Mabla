package com.astart.app.persistence.entity.products;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(
        name = "products_images",
        schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class ProductsImagesEntity implements Serializable {

  /**
   * FIELDS
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(
          name="is_main",
          nullable = true
  )
  private Boolean main;

  @Column(
          name = "path",
          length = 256,
          nullable = false
  )
  private String path;

  @Column(
          nullable = false
  )
  private Integer product_id;

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

  /**
   * Relations
   */
  @ManyToOne
  @JoinColumn(
          name = "product_id",
          referencedColumnName = "id",
          insertable = false,
          updatable = false
  )
  private ProductsEntity product;

  /**
   * METHODS: Don't using methods, using Lombok
   */
}

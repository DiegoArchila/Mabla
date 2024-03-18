package com.astart.app.persistence.entity.products;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(
        name = "products_groups",
        schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class ProductsGroupsEntity {

  /**
   * FIELDS
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(
          name = "name",
          length = 256,
          unique = true,
          nullable = false
  )
  private String name;

  @Column(
          name = "description",
          length = 256,
          nullable = false
  )
  private String description;

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


  /**
   * METHODS: Don't using methods, using Lombok
   */

}

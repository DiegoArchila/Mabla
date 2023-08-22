package com.astart.app.persistence.entity.products;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(
        name = "products_groups",
        schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class ProductsGroups {

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


  // @OneToMany(mappedBy = "group")
  // private List<Products> products;


  /**
   * METHODS: Don't using methods, using Lombok
   */

}
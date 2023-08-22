package com.astart.app.persistence.entity.products;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(
        name = "products",
        schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class Products {

  /**
   * FIELDS
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(
          name = "name",
          length = 128,
          nullable = false
  )
  private String name;

  @Column(
          name = "description",
          length = 256,
          nullable = true
  )
  private String description;

  @Column(
          name = "sku",
          length = 256,
          nullable = false
  )
  private String sku;

  @Column(
          name = "um_id",
          nullable = false
  )
  private Integer um_id;

  @Column(
          name = "group_id",
          nullable = false
  )
  private Integer group_id;

  @Column(
          name = "active",
          columnDefinition = "DEFAULT TRUE",
          nullable = false
  )
  private Boolean active;

  @Column(
          name = "notes",
          nullable = true
  )
  private String notes;

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
  @JoinColumn(name = "um_id",
          referencedColumnName = "id",
          insertable = false,
          updatable = false
  )
  private UnitMeasure unitMeasure;

  @ManyToOne
  @JoinColumn(name = "group_id",
          referencedColumnName = "id",
          insertable = false,
          updatable = false
  )
  private ProductsGroups group;

  @OneToMany(mappedBy = "product")
  private List<ProductsImages> images;

  /**
   * METHODS: Don't using methods, using Lombok
   */

}

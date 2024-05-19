package com.astart.app.persistence.entity.products;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(
        name = "products",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "description"})},
        schema = "public"
)
@Getter
@Setter
@NoArgsConstructor
public class ProductsEntity implements Serializable {

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
          nullable = true
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
          nullable = false
  )
  private Boolean active=true;

  @Column(
          name = "notes",
          nullable = true
  )
  private String notes;

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
  @JoinColumn(name = "um_id",
          referencedColumnName = "id",
          insertable = false,
          updatable = false
  )
  private UnitMeasureEntity unitMeasure;

  @ManyToOne
  @JoinColumn(name = "group_id",
          referencedColumnName = "id",
          insertable = false,
          updatable = false
  )
  private ProductsGroupsEntity group;

  /**
   * METHODS: Don't using methods, using Lombok
   */

}

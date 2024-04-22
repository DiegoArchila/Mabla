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
@Table(
        name = "supplies",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name","description","um_id"})},
        schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class SuppliesEntity {

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
          nullable = true
  )
  private String description;

  @Column(
          name = "um_id",
          nullable = false
  )
  private Integer um_id;

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
  @UpdateTimestamp
  private Date updated_at;

  @Column(
          name = "deleted_at",
          nullable = true
  )
  private Date deleted_at;

  /**
   * METHODS: Don't using methods, using Lombok
   */

  /**
   * RELATIONS:
   */

    @ManyToOne
    @JoinColumn(
            name = "um_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    private UnitMeasureEntity unit_measure;
}

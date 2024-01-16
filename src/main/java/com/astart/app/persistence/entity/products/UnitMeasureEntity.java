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
@SQLDelete(sql = "UPDATE unit_measure SET deleted_at=NOW(), updated_at=NOW() WHERE id=?")
@Table(
        name = "unit_measure",
        schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class UnitMeasureEntity {

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
          nullable = false,
          unique = true
  )
  private String name;

  @Column(
          name = "description",
          nullable = true
  )
  private String description;

  @Column(
          name = "symbol",
          length = 10,
          nullable = false,
          unique = true
  )
  private String symbol;

  @Column(
          name = "created_at",
          columnDefinition = "DEFAULT current_timestamp",
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
   * METHODS: Don't using methods, using Lombok
   */
}

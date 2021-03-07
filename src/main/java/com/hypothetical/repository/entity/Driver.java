package com.hypothetical.repository.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
@Entity
@Table(name = "driver")
public class Driver {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;

  @Column(name = "driver_id", nullable = false)
  private String driverId;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "date_of_birth", nullable = false)
  private LocalDate dateOfBirth;

  @Column(name = "create_date_time", nullable = false)
  private LocalDateTime creationDateTime;

  @Column(name = "update_date_time")
  private LocalDateTime updateDateTime;
}
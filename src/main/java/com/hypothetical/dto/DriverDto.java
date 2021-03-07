package com.hypothetical.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DriverDto {

  private String driverId;
  private String firstName;
  private String lastName;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate dateOfBirth;
  private LocalDateTime creationDateTime;
}

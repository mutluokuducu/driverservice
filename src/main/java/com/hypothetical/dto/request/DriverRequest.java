package com.hypothetical.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverRequest {

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  @NotBlank
  private String dateOfBirth;

}
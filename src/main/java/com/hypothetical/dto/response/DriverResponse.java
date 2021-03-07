package com.hypothetical.dto.response;

import com.hypothetical.dto.DriverDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DriverResponse {

  private List<DriverDto> driverList;

}
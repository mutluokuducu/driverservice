package com.hypothetical.utill;

import static com.hypothetical.util.DateUtils.getDate;

import com.hypothetical.dto.DriverDto;
import com.hypothetical.dto.request.DriverRequest;
import com.hypothetical.dto.response.DriverResponse;
import com.hypothetical.repository.entity.Driver;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ObjectFactory {

  public static final String FIRST_NAME_1 = "Jon";
  public static final String LAST_NAME_1 = "Dan";
  public static final String FIRST_NAME_2 = "Alex";
  public static final String LAST_NAME_2 = "Nixon";

  public static final String DATE_OF_BIRTH_1 = "1990-01-12";
  public static final String DATE_OF_BIRTH_2 = "1970-03-09";
  public static final String DRIVER_ID_1 = "XXXXXX";
  public static final String DRIVER_ID_2 = "YYYYYYY";
  public static final String BEFORE_DATE = "2021-03-07";

  public static DriverRequest buildDriverRequest() {
    return DriverRequest.builder()
        .dateOfBirth(DATE_OF_BIRTH_1)
        .firstName(FIRST_NAME_1)
        .lastName(LAST_NAME_1)
        .build();
  }

  public static DriverDto buildDriverDto() {
    return DriverDto.builder()
        .dateOfBirth(getDate(DATE_OF_BIRTH_1))
        .firstName(FIRST_NAME_1)
        .lastName(LAST_NAME_1)
        .driverId(DRIVER_ID_1)
        .build();
  }

  public static DriverResponse buildDriverResponse() {
    return DriverResponse.builder()
        .driverList(List.copyOf(buildDriverDtoList()))
        .build();
  }

  public static List<DriverDto> buildDriverDtoList() {
    List<DriverDto> driverDtos = new ArrayList<>();

    driverDtos.add(
        DriverDto.builder()
            .dateOfBirth(getDate(DATE_OF_BIRTH_1))
            .firstName(FIRST_NAME_1)
            .lastName(LAST_NAME_1)
            .driverId(DRIVER_ID_1)
            .creationDateTime(LocalDateTime.now())
            .build());

    driverDtos.add(
        DriverDto.builder()
            .dateOfBirth(getDate(DATE_OF_BIRTH_2))
            .firstName(FIRST_NAME_2)
            .lastName(LAST_NAME_2)
            .driverId(DRIVER_ID_2)
            .creationDateTime(LocalDateTime.now())
            .build());

    return driverDtos;
  }

  public static Driver buildDriver() {
    return Driver.builder()
        .dateOfBirth(getDate(DATE_OF_BIRTH_1))
        .firstName(FIRST_NAME_1)
        .lastName(LAST_NAME_1)
        .driverId(DRIVER_ID_1)
        .creationDateTime(LocalDateTime.now())
        .build();
  }
}

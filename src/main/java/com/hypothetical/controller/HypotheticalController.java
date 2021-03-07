package com.hypothetical.controller;

import static com.hypothetical.constant.HypotheticalConstants.DRIVER_CREATE;
import static com.hypothetical.constant.HypotheticalConstants.DRIVER_LIST;
import static com.hypothetical.constant.HypotheticalConstants.DRIVER_LIST_BY_DATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.hypothetical.dto.request.DriverRequest;
import com.hypothetical.dto.response.DriverResponse;
import com.hypothetical.services.HypotheticalService;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HypotheticalController {

  @Autowired
  public HypotheticalService hypotheticalService;

  @ApiOperation(
      value = "Add driver",
      nickname = "Add driver",
      response = ResponseEntity.class)
  @PostMapping(
      value = DRIVER_CREATE,
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public HttpEntity<?> addDriver(
      @RequestBody @Valid DriverRequest driverRequest) {
    hypotheticalService.addDriver(driverRequest);

    return ResponseEntity.ok().build();
  }

  @ApiOperation(
      value = "List drivers ",
      nickname = "List drivers ",
      response = ResponseEntity.class)
  @GetMapping(
      value = DRIVER_LIST)
  public ResponseEntity<DriverResponse> getDrivers() {

    DriverResponse driverResponse = hypotheticalService.getDrivers();

    return ResponseEntity.ok().body(driverResponse);
  }

  @ApiOperation(
      value = "List drivers before selected date ",
      nickname = "List drivers before selected date ",
      response = ResponseEntity.class)
  @GetMapping(
      value = DRIVER_LIST_BY_DATE)
  public ResponseEntity<DriverResponse> getDriversByDate(
      @NotBlank @RequestParam("date") String date) {

    DriverResponse driverResponse = hypotheticalService.getDriversByDate(date);

    return ResponseEntity.ok().body(driverResponse);
  }
}

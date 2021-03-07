package com.hypothetical.services;

import com.hypothetical.dto.request.DriverRequest;
import com.hypothetical.dto.response.DriverResponse;


public interface HypotheticalService {

  void addDriver(DriverRequest driverRequest);

  DriverResponse getDrivers();

  DriverResponse getDriversByDate(String date);
}

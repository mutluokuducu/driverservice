package com.hypothetical.services;

import static com.hypothetical.util.DateUtils.getDate;

import com.hypothetical.dto.DriverDto;
import com.hypothetical.dto.request.DriverRequest;
import com.hypothetical.dto.response.DriverResponse;
import com.hypothetical.mapper.HypotheticalMapper;
import com.hypothetical.repository.HypotheticalRepository;
import com.hypothetical.repository.entity.Driver;
import com.hypothetical.util.DriverIDGenerator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HypotheticalServiceImpl implements HypotheticalService {

  @Autowired
  private HypotheticalRepository hypotheticalRepository;

  @Autowired
  private DriverIDGenerator driverIDGenerator;

  @Autowired
  private HypotheticalMapper hypotheticalMapper;

  @Override
  public void addDriver(DriverRequest driverRequest) {
    log.info("Driver inserted");
    LocalDateTime localDateTime = LocalDateTime.now();
    Driver driver = Driver.builder()
        .driverId(driverIDGenerator.generateUniqueUuid())
        .firstName(driverRequest.getFirstName())
        .lastName(driverRequest.getLastName())
        .dateOfBirth(getDate(driverRequest.getDateOfBirth()))
        .creationDateTime(localDateTime)
        .updateDateTime(localDateTime)
        .build();
    hypotheticalRepository.save(driver);

  }

  @Override
  public DriverResponse getDrivers() {
    log.info("Get all driver  list");
    List<Driver> responseList = hypotheticalRepository.findAll();

    List<DriverDto> driverDtos = new ArrayList<>();
    responseList.forEach(list -> driverDtos.add(hypotheticalMapper.toDriverResponse(list)));

    return DriverResponse.builder()
        .driverList(driverDtos)
        .build();
  }

  @Override
  public DriverResponse getDriversByDate(String date) {
    LocalDate localDate = getDate(date);

    log.info("Get all driver  list before {}", date);
    List<Driver> responseList = hypotheticalRepository.findAll();

    responseList.stream()
        .filter(x -> x.getCreationDateTime().toLocalDate().isBefore(localDate))
        .collect(Collectors.toList());

    List<DriverDto> driverDtos = new ArrayList<>();
    responseList.forEach(list -> driverDtos.add(hypotheticalMapper.toDriverResponse(list)));

    return DriverResponse.builder()
        .driverList(driverDtos)
        .build();
  }
}

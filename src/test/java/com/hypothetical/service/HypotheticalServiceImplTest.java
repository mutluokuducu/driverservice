package com.hypothetical.service;

import static com.hypothetical.exeption.ErrorType.DATE_FORMAT_WRONG;
import static com.hypothetical.utill.ObjectFactory.BEFORE_DATE;
import static com.hypothetical.utill.ObjectFactory.DATE_OF_BIRTH_1;
import static com.hypothetical.utill.ObjectFactory.DRIVER_ID_1;
import static com.hypothetical.utill.ObjectFactory.FIRST_NAME_1;
import static com.hypothetical.utill.ObjectFactory.LAST_NAME_1;
import static com.hypothetical.utill.ObjectFactory.buildDriver;
import static com.hypothetical.utill.ObjectFactory.buildDriverDto;
import static com.hypothetical.utill.ObjectFactory.buildDriverRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hypothetical.dto.DriverDto;
import com.hypothetical.dto.request.DriverRequest;
import com.hypothetical.dto.response.DriverResponse;
import com.hypothetical.exeption.HypotheticalException;
import com.hypothetical.mapper.HypotheticalMapper;
import com.hypothetical.repository.HypotheticalRepository;
import com.hypothetical.repository.entity.Driver;
import com.hypothetical.services.HypotheticalServiceImpl;
import com.hypothetical.util.DriverIDGenerator;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HypotheticalServiceImplTest {

  @Mock
  private HypotheticalRepository hypotheticalRepository;

  @Mock
  private DriverIDGenerator driverIDGenerator;

  @Mock
  private HypotheticalMapper hypotheticalMapper;

  @InjectMocks
  private HypotheticalServiceImpl hypotheticalService;

  @Test
  void shouldCreateDriver() {
    DriverRequest driverRequest = buildDriverRequest();

    when(driverIDGenerator.generateUniqueUuid()).thenReturn(DRIVER_ID_1);

    assertThatCode(
        () ->
            hypotheticalService.addDriver(driverRequest))
        .doesNotThrowAnyException();

    verify(hypotheticalRepository).save(argThat((Driver driver) -> {
      assertThat(driver).isNotNull();
      assertThat(driver.getFirstName()).isEqualTo(FIRST_NAME_1);
      assertThat(driver.getLastName()).isEqualTo(LAST_NAME_1);
      assertThat(driver.getDriverId()).isEqualTo(DRIVER_ID_1);
      assertThat(driver.getDateOfBirth()).isEqualTo(DATE_OF_BIRTH_1);
      return true;
    }));
  }

  @Test
  void processInsertDriver_ShouldThrowException_WhenDateOfBirthInvalidFormat() {
    DriverRequest driverRequest = buildDriverRequest();
    driverRequest.setDateOfBirth("12/02/2001");

    HypotheticalException exceptionThrown =
        assertThrows(
            HypotheticalException.class,
            () ->
                hypotheticalService.addDriver(driverRequest));

    assertThat(exceptionThrown.getErrorType()).isEqualTo(DATE_FORMAT_WRONG);
  }


  @Test
  void processDriver_ShouldGetDriverList() {
    Driver driver = buildDriver();
    DriverDto driverDto = buildDriverDto();

    when(hypotheticalMapper.toDriverResponse(driver)).thenReturn(driverDto);

    when(hypotheticalRepository.findAll())
        .thenReturn(Lists.newArrayList(driver));

    DriverResponse driverResponse = hypotheticalService.getDrivers();

    assertThat(driverResponse).isNotNull();
    assertThat(driverResponse.getDriverList().get(0).getFirstName())
        .isEqualTo(FIRST_NAME_1);
    assertThat(driverResponse.getDriverList().get(0).getLastName())
        .isEqualTo(LAST_NAME_1);
    assertThat(driverResponse.getDriverList().get(0).getDriverId())
        .isEqualTo(DRIVER_ID_1);
    assertThat(driverResponse.getDriverList().get(0).getDateOfBirth())
        .isEqualTo(DATE_OF_BIRTH_1);

  }

  @Test
  void processDriver_ShouldGetDriverList_WhenBeforeSelectedDate() {
    Driver driver = buildDriver();
    DriverDto driverDto = buildDriverDto();

    when(hypotheticalMapper.toDriverResponse(driver)).thenReturn(driverDto);

    when(hypotheticalRepository.findAll())
        .thenReturn(Lists.newArrayList(driver));

    DriverResponse driverResponse = hypotheticalService.getDriversByDate(BEFORE_DATE);

    assertThat(driverResponse).isNotNull();
    assertThat(driverResponse.getDriverList().get(0).getFirstName())
        .isEqualTo(FIRST_NAME_1);
    assertThat(driverResponse.getDriverList().get(0).getLastName())
        .isEqualTo(LAST_NAME_1);
    assertThat(driverResponse.getDriverList().get(0).getDriverId())
        .isEqualTo(DRIVER_ID_1);
    assertThat(driverResponse.getDriverList().get(0).getDateOfBirth())
        .isEqualTo(DATE_OF_BIRTH_1);

  }
}
package com.hypothetical.mapper;

import static com.hypothetical.utill.ObjectFactory.DATE_OF_BIRTH_1;
import static com.hypothetical.utill.ObjectFactory.FIRST_NAME_1;
import static com.hypothetical.utill.ObjectFactory.LAST_NAME_1;
import static com.hypothetical.utill.ObjectFactory.buildDriver;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.hypothetical.dto.DriverDto;
import com.hypothetical.repository.entity.Driver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HypotheticalMapperTest {

  HypotheticalMapperImpl hypotheticalMapper = new HypotheticalMapperImpl();

  @Test
  public void toSubscriptionAccount() {
    Driver driver = buildDriver();
    DriverDto driverDto = hypotheticalMapper.toDriverResponse(driver);

    assertThat(driverDto.getFirstName()).isEqualTo(FIRST_NAME_1);
    assertThat(driverDto.getLastName()).isEqualTo(LAST_NAME_1);
    assertThat(driverDto.getDateOfBirth()).isEqualTo(DATE_OF_BIRTH_1);
  }
}

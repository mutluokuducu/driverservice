package com.hypothetical.controller;

import static com.hypothetical.constant.HypotheticalConstants.DRIVER_CREATE;
import static com.hypothetical.constant.HypotheticalConstants.DRIVER_LIST;
import static com.hypothetical.constant.HypotheticalConstants.DRIVER_LIST_BY_DATE;
import static com.hypothetical.util.JsonUtils.objectToJson;
import static com.hypothetical.utill.ObjectFactory.BEFORE_DATE;
import static com.hypothetical.utill.ObjectFactory.DATE_OF_BIRTH_1;
import static com.hypothetical.utill.ObjectFactory.DATE_OF_BIRTH_2;
import static com.hypothetical.utill.ObjectFactory.DRIVER_ID_1;
import static com.hypothetical.utill.ObjectFactory.DRIVER_ID_2;
import static com.hypothetical.utill.ObjectFactory.FIRST_NAME_1;
import static com.hypothetical.utill.ObjectFactory.FIRST_NAME_2;
import static com.hypothetical.utill.ObjectFactory.LAST_NAME_1;
import static com.hypothetical.utill.ObjectFactory.LAST_NAME_2;
import static com.hypothetical.utill.ObjectFactory.buildDriverRequest;
import static com.hypothetical.utill.ObjectFactory.buildDriverResponse;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hypothetical.dto.request.DriverRequest;
import com.hypothetical.exeption.GlobalExceptionHandler;
import com.hypothetical.services.HypotheticalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class HypotheticalControllerTest {

  private MockMvc mockMvc;

  @Mock
  private HypotheticalService hypotheticalService;

  @InjectMocks
  private HypotheticalController hypotheticalController;

  @BeforeEach
  public void init() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(hypotheticalController)
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
  }

  @Test
  void createDriver_shouldReturnIsOK() throws Exception {
    DriverRequest driverRequest = buildDriverRequest();
    this.mockMvc
        .perform(
            post(DRIVER_CREATE)
                .content(objectToJson(driverRequest))
                .contentType(APPLICATION_JSON_VALUE))
        .andExpect(status().isOk());
  }

  @Test
  void createDriver_shouldReturnInternalServerError_WhenDriverRequestIsNull() throws Exception {
    this.mockMvc
        .perform(
            post(DRIVER_CREATE)
                .content(objectToJson(null))
                .contentType(APPLICATION_JSON_VALUE))
        .andExpect(status().isInternalServerError());
  }

  @Test
  void shouldGetDriverList() throws Exception {
    when(hypotheticalService
        .getDrivers())
        .thenReturn(buildDriverResponse());

    this.mockMvc
        .perform(
            get(DRIVER_LIST)
                .contentType(APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.driverList").isNotEmpty())
        .andExpect(jsonPath("$.driverList[0].firstName").value(FIRST_NAME_1))
        .andExpect(jsonPath("$.driverList[0].lastName").value(LAST_NAME_1))
        .andExpect(jsonPath("$.driverList[0].driverId").value(DRIVER_ID_1))
        .andExpect(jsonPath("$.driverList[0].dateOfBirth").value(DATE_OF_BIRTH_1))

        .andExpect(jsonPath("$.driverList").isNotEmpty())
        .andExpect(jsonPath("$.driverList[1].firstName").value(FIRST_NAME_2))
        .andExpect(jsonPath("$.driverList[1].lastName").value(LAST_NAME_2))
        .andExpect(jsonPath("$.driverList[1].driverId").value(DRIVER_ID_2))
        .andExpect(jsonPath("$.driverList[1].dateOfBirth").value(DATE_OF_BIRTH_2));
  }

  @Test
  void shouldGetDriverList_WhenGivenDateBefore() throws Exception {
    when(hypotheticalService
        .getDriversByDate(BEFORE_DATE))
        .thenReturn(buildDriverResponse());

    this.mockMvc
        .perform(
            get(DRIVER_LIST_BY_DATE)
                .param("date", BEFORE_DATE)
                .contentType(APPLICATION_JSON_VALUE))

        .andExpect(status().isOk())
        .andExpect(jsonPath("$.driverList").isNotEmpty())
        .andExpect(jsonPath("$.driverList[0].firstName").value(FIRST_NAME_1))
        .andExpect(jsonPath("$.driverList[0].lastName").value(LAST_NAME_1))
        .andExpect(jsonPath("$.driverList[0].driverId").value(DRIVER_ID_1))
        .andExpect(jsonPath("$.driverList[0].dateOfBirth").value(DATE_OF_BIRTH_1))

        .andExpect(jsonPath("$.driverList").isNotEmpty())
        .andExpect(jsonPath("$.driverList[1].firstName").value(FIRST_NAME_2))
        .andExpect(jsonPath("$.driverList[1].lastName").value(LAST_NAME_2))
        .andExpect(jsonPath("$.driverList[1].driverId").value(DRIVER_ID_2))
        .andExpect(jsonPath("$.driverList[1].dateOfBirth").value(DATE_OF_BIRTH_2));
  }
}
package com.hypothetical;

import static com.hypothetical.util.DateUtils.getDate;

import com.hypothetical.repository.HypotheticalRepository;
import com.hypothetical.repository.entity.Driver;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public CommandLineRunner commandlineRunner(HypotheticalRepository hypotheticalRepository)
      throws Exception {

    return args -> {
      hypotheticalRepository.deleteAllInBatch();
      List<Driver> drivers = new ArrayList<>();
      drivers.add(
          createCreditCard("Jon", "Dan", "1990-01-12", "XXXXXX"));
      drivers.add(
          createCreditCard("Alex", "Nixon", "1970-03-09", "YYYYYYY"));

      hypotheticalRepository.saveAll(drivers);
    };
  }

  private Driver createCreditCard(String firstName, String lastName, String dateOfBirth,
      String driverId) {
    LocalDateTime localDateTime = LocalDateTime.now();
    return Driver.builder()
        .driverId(driverId)
        .firstName(firstName)
        .lastName(lastName)
        .dateOfBirth(getDate(dateOfBirth))
        .creationDateTime(localDateTime)
        .updateDateTime(localDateTime)
        .build();
  }

}

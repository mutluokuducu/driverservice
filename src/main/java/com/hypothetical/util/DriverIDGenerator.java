package com.hypothetical.util;

import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class DriverIDGenerator {

  public static UUID generateUuid() {
    return UUID.randomUUID();
  }

  public String generateUniqueUuid() {
    return generateUuid().toString();
  }
}

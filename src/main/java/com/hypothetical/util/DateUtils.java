package com.hypothetical.util;

import static com.hypothetical.exeption.ErrorType.DATE_FORMAT_WRONG;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.time.format.ResolverStyle.STRICT;
import static java.util.Optional.ofNullable;

import com.hypothetical.exeption.HypotheticalException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtils {

  public static final String DATE_FORMAT_UUUU_MM_DD = "uuuu-MM-dd";
  public static final DateTimeFormatter DATE_FORMAT_YYYY_MM_DD_FORMATTER =
      ofPattern(DATE_FORMAT_UUUU_MM_DD).withResolverStyle(STRICT);
  private static final String ISO8601_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
  private static final DateTimeFormatter ISO8601_DATETIME_FORMATTER = ofPattern(
      ISO8601_DATETIME_FORMAT);

  public static LocalDateTime getLocalDateTime(String dateTime) {
    return ofNullable(dateTime)
        .map(
            d -> {
              try {
                return stringToLocalDateTime(d);
              } catch (DateTimeParseException ex) {
                log.error("Payment date format is wrong. ", ex);
                throw new HypotheticalException(DATE_FORMAT_WRONG);
              }
            })
        .orElse(LocalDateTime.now());
  }


  public static LocalDate getDate(String date) {
    return ofNullable(date)
        .map(
            d -> {
              try {
                return stringToLocalDate(d);
              } catch (DateTimeParseException ex) {
                log.error("Payment date format is wrong. ", ex);
                throw new HypotheticalException(DATE_FORMAT_WRONG);
              }
            })
        .orElse(LocalDate.now());
  }

  public static LocalDate stringToLocalDate(final String value) {
    return LocalDate.parse(value, DATE_FORMAT_YYYY_MM_DD_FORMATTER);
  }

  public static LocalDateTime stringToLocalDateTime(final String value) {
    return LocalDateTime.parse(value, ISO8601_DATETIME_FORMATTER);
  }
}

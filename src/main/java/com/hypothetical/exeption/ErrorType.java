package com.hypothetical.exeption;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
public enum ErrorType {
  INTERNAL_ERROR(100, "An internal server error occurred", INTERNAL_SERVER_ERROR),
  DATE_FORMAT_WRONG(101,
      "Date  is not in expected format, yyyy-MM-dd or invalid", BAD_REQUEST),
  INVALID_PARAMETER_ERROR(102, "Invalid field(s) or blank field(s) ", UNPROCESSABLE_ENTITY);

  private int id;
  private String description;
  private HttpStatus httpStatus;

  ErrorType(int id, String description, HttpStatus httpStatus) {

    this.id = id;
    this.description = description;
    this.httpStatus = httpStatus;
  }
}

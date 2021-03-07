package com.hypothetical.exeption;

import lombok.Getter;

@Getter
public class HypotheticalException extends RuntimeException {

  private final ErrorType errorType;

  public HypotheticalException(ErrorType errorType) {
    this.errorType = errorType;
  }
}

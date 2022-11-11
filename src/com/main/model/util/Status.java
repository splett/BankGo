package com.main.model.util;

public enum Status {
  NOT_FOUND(404),
  CREATED(201);

  private Integer statusCode;

  Status(Integer statusCode) {
    this.statusCode = statusCode;
  }

  public Integer getStatusCode() {
    return statusCode;
  }
}

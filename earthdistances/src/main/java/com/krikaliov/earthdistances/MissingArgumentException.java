package com.krikaliov.earthdistances;

public class MissingArgumentException extends RuntimeException {
  private final String argName;

  public MissingArgumentException(String argName) {
    super("Missing argument for this command : " + argName);

    this.argName = argName;
  }

  public String getArgName() {
    return this.argName;
  }
}

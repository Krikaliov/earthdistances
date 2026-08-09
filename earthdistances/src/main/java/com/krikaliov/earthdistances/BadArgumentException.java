package com.krikaliov.earthdistances;

public class BadArgumentException extends RuntimeException {
  private final String reason;
  private final String argName;
  private final String argValue;

  public BadArgumentException(String argName, String argValue, String reason) {
    super("Bad argument given for " + argName + ": '" + argValue + "' unrecognized! Reason: " + reason + ".");

    this.reason = reason;
    this.argName = argName;
    this.argValue = argValue;
  }

  public String getReason() {
    return this.reason;
  }

  public String getArgName() {
    return this.argName;
  }

  public String getArgValue() {
    return this.argValue;
  }
}

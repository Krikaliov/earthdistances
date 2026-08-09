package com.krikaliov.earthdistances;

public abstract class ArgumentOptional<T> {
  private final String argName;

  protected T value;
  protected T defaultValue;

  public ArgumentOptional(String argName) {
    this.argName = argName;
  }

  public String getArgName() {
    return this.argName;
  }

  public T getValue() {
    if (this.value == null) {
      return this.defaultValue;
    } else {
      return this.value;
    }
  }

  public boolean matches(String buf) {
    return buf.startsWith("--" + this.argName);
  }
}

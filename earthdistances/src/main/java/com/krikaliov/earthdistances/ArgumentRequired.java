package com.krikaliov.earthdistances;

public abstract class ArgumentRequired<T> {
  private final String argName;

  protected T value;

  public ArgumentRequired(String argName) {
    this.argName = argName;
  }

  public String getArgName() {
    return this.argName;
  }

  public T getValue() throws MissingArgumentException {
    if (this.value == null) {
      throw new MissingArgumentException(this.argName);
    } else {
      return this.value;
    }
  }
}

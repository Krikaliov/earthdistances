package com.krikaliov.earthdistances;

public interface CommandFunction {
  @SuppressWarnings("rawtypes")
  void exec(Argument[] args);
}

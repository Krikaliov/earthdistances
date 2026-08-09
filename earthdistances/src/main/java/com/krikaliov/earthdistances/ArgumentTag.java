package com.krikaliov.earthdistances;

public class ArgumentTag extends ArgumentOptional<Boolean> implements Argument<Boolean> {
  public ArgumentTag(String argName) {
    super(argName);
    super.defaultValue = false;
  }

  @Override
  public void parse(String buf) {
    this.value = buf.equals("--" + super.getArgName());
  }
}

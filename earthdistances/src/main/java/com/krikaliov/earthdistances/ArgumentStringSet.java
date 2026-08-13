package com.krikaliov.earthdistances;

public class ArgumentStringSet extends ArgumentRequired<String> implements Argument<String> {
  private final String[] values;

  public ArgumentStringSet(String argName, String[] values) {
    super(argName);
    this.values = values;
  }

  @Override
  public void parse(String buf) throws BadArgumentException {
    for (String val : this.values) {
      if (val.equals(buf)) {
        this.value = buf;
        return;
      }
    }
    throw new BadArgumentException(super.getArgName(), buf, "not an option for this argument");
  }
}

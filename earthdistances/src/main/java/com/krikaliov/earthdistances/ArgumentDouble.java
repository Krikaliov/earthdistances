package com.krikaliov.earthdistances;

public class ArgumentDouble extends ArgumentRequired<Double> implements Argument<Double> {

  public ArgumentDouble(String argName) {
    super(argName);
  }

  @Override
  public void parse(String buf) throws BadArgumentException {
    try {
      super.value = Double.valueOf(buf);
    } catch (NumberFormatException e) {
      throw new BadArgumentException(this.getArgName(), buf, "not a floating number");
    } catch (Exception e) {
      throw new BadArgumentException(this.getArgName(), buf, e.getMessage());
    }
  }
}

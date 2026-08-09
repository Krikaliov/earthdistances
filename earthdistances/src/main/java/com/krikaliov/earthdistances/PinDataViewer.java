package com.krikaliov.earthdistances;

public class PinDataViewer {
  public final static String[] colors = {"green", "red", "yellow", "blue", "pink"};

  public PinDataViewer() {}

  private String displayPin(PinData pin) {
    if (pin == null) {
      return "()";
    } else {
      final String thetaStr = Double.toString(pin.getTheta());
      final String phiStr = Double.toString(pin.getPhi());
      return "(" + thetaStr + " " + phiStr + ")";
    }
  }

  public String displayUpperPin() {
    return this.displayPin(PinDataManager.getInstance().upper());
  }

  public String displayLowerPin() {
    return this.displayPin(PinDataManager.getInstance().lower());
  }

  @Override
  public String toString() {
    final double distance = PinDataManager.getInstance().distance();
    return "[" + this.displayUpperPin() + " , " + this.displayLowerPin() + "]"
      + ((distance > 0) ? "<" + Double.toString(distance) + " km>" : "<>");
  }
}

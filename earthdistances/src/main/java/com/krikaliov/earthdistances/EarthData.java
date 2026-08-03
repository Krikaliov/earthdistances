package com.krikaliov.earthdistances;

public class EarthData {

  private final double calculatedDistance;
  private final int unitDistance;

  public EarthData() {
    this.calculatedDistance = 0.0;
    this.unitDistance = 0;
  }

  @Override
  public String toString() {
    String unitStr;
    switch (this.unitDistance) {
      case 0:
      default:
        unitStr = " km";
    }
    return String.valueOf(this.calculatedDistance) + unitStr;
  }

}

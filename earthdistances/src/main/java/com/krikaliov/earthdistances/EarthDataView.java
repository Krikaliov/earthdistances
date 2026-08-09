package com.krikaliov.earthdistances;

public class EarthDataView {
  private final EarthData dataRef;

  public EarthDataView(EarthData dataRef) {
    this.dataRef = dataRef;
  }

  @Override
  public String toString() {
    String radiusInMeterStr = Double.toString(EarthData.RADIUS);
    String radiusInMilesStr = Double.toString(this.dataRef.radiusInMiles());

    return "Earth's radius : " + radiusInMeterStr + " km (" + radiusInMilesStr + " miles)";
  }
}

package com.krikaliov.earthdistances;

public class EarthData {

  public static final double RADIUS = 6371.0;
  public static final double TOKM = 1.609344;

  public EarthData() {}

  public double radiusInMiles() {
    return RADIUS / TOKM;
  }
}

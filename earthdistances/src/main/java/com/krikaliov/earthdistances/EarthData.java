package com.krikaliov.earthdistances;

public class EarthData {

  public static final double RADIUS = 6371;

  public EarthData() {}

  public double radiusInMiles() {
    return RADIUS / 1.6087;
  }
}

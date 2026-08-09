package com.krikaliov.earthdistances;

public class PinData {
  public final static double TORADIAN = Math.PI / 180.0;

  private final double theta; // in degrees [-180:180]
  private final double phi; // in degrees [-90:90]
  private final String color; // among colors from 'PinDataViewer.colors'

  // 3D Coordinates of the pin calculated from above data
  // where the origin is the Earth center point and the unit
  // distance is the meter.
  private final double x;
  private final double y;
  private final double z;

  public PinData(double theta, double phi, String color) {
    this.theta = theta;
    this.phi = phi;
    this.color = color;

    this.x = EarthData.RADIUS * Math.cos(TORADIAN * this.theta) * Math.cos(TORADIAN * this.phi);
    this.y = EarthData.RADIUS * Math.sin(TORADIAN * this.theta) * Math.cos(TORADIAN * this.phi);
    this.z = EarthData.RADIUS * Math.sin(TORADIAN * this.phi);
  }

  public PinData(PinData src) {
    this.theta = src.theta;
    this.phi = src.phi;
    this.color = src.color;
    
    this.x = src.x;
    this.y = src.y;
    this.z = src.z;
  }

  public double getTheta() {
    return this.theta;
  }

  public double getPhi() {
    return this.phi;
  }

  public double[] getCartesian() {
    double[] set = new double[3];
    set[0] = this.x;
    set[1] = this.y;
    set[2] = this.z;
    return set;
  }

  public double distance(PinData other) {
    // 1. Calculate [straight line distance]² between both pins
    double straightLineSquared = 
      Math.pow(this.x - other.x, 2) +
      Math.pow(this.y - other.y, 2) +
      Math.pow(this.z - other.z, 2);

    // 2. Use Al-Kashi formula to find big circle angle portion
    // s² = 2r² - 2r²cos(a) = (1 - cos(a))2r²
    // <=> a = arccos(1-(s²/2r²))
    double earthRadiusDoubledSquared = 2.0 * Math.pow(EarthData.RADIUS, 2);
    double angle = Math.acos(1.0 - (straightLineSquared / earthRadiusDoubledSquared));

    // Use arc formula to find distance between both pins
    return angle * EarthData.RADIUS;
  }
}

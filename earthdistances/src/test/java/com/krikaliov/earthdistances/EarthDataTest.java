package com.krikaliov.earthdistances;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class EarthDataTest {
  @Test
  public void testMilesConversion() {
    final EarthData dataTest = new EarthData();
    final double radiusInMiles = dataTest.radiusInMiles();

    assertEquals(3958.756, radiusInMiles, 0.002);
  }
}

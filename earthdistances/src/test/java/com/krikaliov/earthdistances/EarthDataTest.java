package com.krikaliov.earthdistances;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class EarthDataTest {
  @Test
  public void testToString() {
    final EarthData dataTest = new EarthData();
    final String dataTestStr = dataTest.toString();

    assertEquals(dataTestStr, "0.0 km");
  }
}

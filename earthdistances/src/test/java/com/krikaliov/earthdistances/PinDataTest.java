package com.krikaliov.earthdistances;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class PinDataTest {
  @Test
  public void testDistance() {
    PinData pin1 = new PinData(50.582, 3.094, "green");
    PinData pin2 = new PinData(49.012, 2.524, "green");
    double distance = pin1.distance(pin2);

    assertTrue(180000.0 < distance && distance < 190000.0);

    PinData nul1 = new PinData(-180.0, 0.0, "pink");
    PinData nul2 = new PinData(180.0, 0.0, "yellow");
    distance = nul1.distance(nul2);

    assertEquals(0.0, distance, 0.1);
  }

  @Test
  public void testGetters() {
    PinData pin = new PinData(27, 3, "green");

    assertEquals(27, pin.getTheta(), 0.001);
    assertEquals(3, pin.getPhi(), 0.001);
  }
}

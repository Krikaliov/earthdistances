package com.krikaliov.earthdistances;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class EarthDataViewTest {
  @Test
  public void testToString() {
    final EarthDataView dataViewTest = new EarthDataView(new EarthData());
    final String dataViewTestStr = dataViewTest.toString();

    assertTrue(
      dataViewTestStr.contains("---------------") &&
      dataViewTestStr.contains("earth distances") &&
      dataViewTestStr.contains("---------------") &&
      dataViewTestStr.contains("0.0 km") &&
      dataViewTestStr.contains("Thank you") &&
      dataViewTestStr.contains("krikaliov")
    );
  }
}

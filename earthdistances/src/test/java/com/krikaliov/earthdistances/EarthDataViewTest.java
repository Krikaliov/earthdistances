package com.krikaliov.earthdistances;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class EarthDataViewTest {
  @Test
  public void testToString() {
    final EarthDataView dataViewTest = new EarthDataView(new EarthData());
    final String dataViewTestStr = dataViewTest.toString();

    assertTrue(
      dataViewTestStr.contains("Earth's radius") &&
      dataViewTestStr.contains("6371.008 km") &&
      dataViewTestStr.contains("3960.345 miles")
    );
  }
}

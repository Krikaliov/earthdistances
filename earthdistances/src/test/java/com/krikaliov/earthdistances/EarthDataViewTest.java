package com.krikaliov.earthdistances;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class EarthDataViewTest {
  @Test
  public void testToString() {
    final EarthDataView dataViewTest = new EarthDataView(new EarthData());
    final String dataViewTestStr = dataViewTest.toString();
    assertEquals("Earth's radius : 6371.0 km (3958.7558657440545 miles)", dataViewTestStr);
  }
}

package com.krikaliov.earthdistances;

public class EarthDataView {
  private final EarthData dataRef;

  public EarthDataView(EarthData dataRef) {
    this.dataRef = dataRef;
  }

  @Override
  public String toString() {
    String text = """
        ----------------------------
        --| earth distances v0.1 |--
        ----------------------------

        This version only support the 14 billions years BC Earth when it was a single point.
        So there are all the available distances:
        """ + this.dataRef + """

        
        Today Earth should be added in a newer version :D
        Thank you for using my software! © krikaliov
        """;
    return text;
  }
}

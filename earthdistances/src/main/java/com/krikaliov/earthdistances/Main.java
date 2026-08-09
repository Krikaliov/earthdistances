package com.krikaliov.earthdistances;

import java.io.IOException;

public class Main {
  public static final String VERSION = "v0.2";
  public static final String APPNAME = "earth-distances";

  public static void main(String[] args) throws IOException {
    App app = new App(480, 854);
    app.loop();
  }
}

package com.krikaliov.earthdistances;

import java.io.IOException;

public class App {
  private boolean alive;

  private final int width;
  private final int height;

  private final EarthDataView earthDataView;
  private final PinDataViewer pinDataViewer;

  private final String label;

  private final Controller mainController;

  public App(int width, int height) {
    this.alive = true;

    this.width = width;
    this.height = height;

    this.earthDataView = new EarthDataView(new EarthData());
    this.pinDataViewer = new PinDataViewer();

    String msg = Main.APPNAME + " " + Main.VERSION + " " + Integer.toString(this.width) + "x" + Integer.toString(this.height);
    int n = msg.length();
    this.label = "-".repeat(n+8) + "\n--| " + msg + " |--\n" + "-".repeat(n+8);

    this.mainController = new Controller(this);
  }

  public void loop() throws IOException {
    System.out.println(this.label);
    System.out.println();

    System.out.println(this.earthDataView);
    System.out.println();

    while (this.alive) {
      System.out.println(this.pinDataViewer);

      this.mainController.waitForCmdLine();
    }
  }

  @SuppressWarnings("rawtypes")
  public final CommandFunction quitFn = (Argument[] _) -> {
    System.out.println("Thank you for using my software! © krikaliov");
    this.alive = false;
  };
}

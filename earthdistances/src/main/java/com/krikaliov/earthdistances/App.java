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

    final String msg = Main.APPNAME + " " + Main.VERSION;
    String unions = "";
    for (int i = 0 ; i < msg.length() + 8 ; i++) unions = unions.concat("-");
    this.label = unions + "\n--| " + msg + " |--\n" + unions;

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

  public int[] getScreenSize() {
    final int[] size = new int[2];
    size[0] = this.width;
    size[1] = this.height;
    return size;
  }

  public boolean isAlive() {
    return this.alive;
  }

  public PinDataViewer pinViewer() {
    return this.pinDataViewer;
  }

  @SuppressWarnings("rawtypes")
  public final CommandFunction quitCmdFn = (Argument[] x) -> {
    System.out.println("Thank you for using my software! © krikaliov");
    this.alive = false;
  };
}

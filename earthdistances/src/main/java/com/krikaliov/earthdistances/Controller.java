package com.krikaliov.earthdistances;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
  private final BufferedReader reader;
  private final CommandManager cmdManager;

  public Controller(App app) {
    this.reader = new BufferedReader(new InputStreamReader(System.in));
    this.cmdManager = new CommandManager(app, System.out);
  }

  public void waitForCmdLine() throws IOException {
    System.out.print("> ");
    final String raw = this.reader.readLine().trim();
    this.cmdManager.scan(raw);
  }
}

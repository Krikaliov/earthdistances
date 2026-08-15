package com.krikaliov.earthdistances;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Logger extends Thread {
  private static class Binder implements Runnable {
    private CommandManager cmdManager = null;
    private BufferedReader reader = null;

    private InputStream input = null;
    private PrintStream output = null;

    public boolean alive = false;

    private final PinDataViewer pinViewer = new PinDataViewer();

    public Binder() {}

    public synchronized void resetOutput(final PrintStream output, final CommandFunction quitAppFn) {
      this.output = output;
      if (this.output == null) {
        this.cmdManager = null;
      } else {
        this.cmdManager = new CommandManager(output, quitAppFn);
      }
    }

    public synchronized void resetInput(final InputStream input) {
      this.input = input;
      if (this.input == null) {
        this.reader = null;
      } else {
        this.reader = new BufferedReader(new InputStreamReader(input));
      }
    }

    public synchronized final PrintStream output() { return this.output; }

    @Override
    public void run() {
      try {
        while (this.alive) {
          if (this.reader != null) {
            this.output.println(this.pinViewer);
            final String raw = this.reader.readLine().trim();
            if (raw != null && raw.length() > 0 && this.cmdManager != null) {
              this.cmdManager.scan(raw);
            }
          }
          sleep(100);
        }
      } catch (InterruptedException | IOException e) {
        this.alive = false;
      }
    }
  }
  private static Binder binder = new Binder();

  private static Logger inst = null;
  private Logger() { super(binder); }
  public final static synchronized Logger getInstance() {
    if (inst == null) { inst = new Logger(); }
    return inst;
  }

  public synchronized void set(final CommandFunction quitAppFn, final InputStream input, final PrintStream output) {
    binder.resetOutput(output, quitAppFn);
    binder.resetInput(input);
  }

  public synchronized final boolean isRunning() {
    return binder.alive;
  }

  public synchronized void openConsole() {
    if (!this.isRunning()) {
      binder.alive = true;
      super.start();
    }
  }

  public synchronized void closeConsole() {
    binder.resetInput(null);
    binder.resetOutput(null, null);
    binder.alive = false;

    super.interrupt();
  }

  public synchronized void log(String msg) {
    if (binder.output() != null) binder.output().println(msg);
  }
}

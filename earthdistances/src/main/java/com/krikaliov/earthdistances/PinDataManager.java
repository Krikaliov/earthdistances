package com.krikaliov.earthdistances;

public class PinDataManager {
  // Singleton that handles with 0-2 pins in LIFO style
  private final PinData[] pins;
  private String lastError;

  private static PinDataManager inst = null;
  private PinDataManager() {
    this.pins = new PinData[2];
    this.lastError = null;
  }

  public static synchronized PinDataManager getInstance() {
    if (inst == null) {
      inst = new PinDataManager(); 
    }
    return inst;
  }

  public void clear() {
    this.pins[0] = null;
    this.pins[1] = null;
  }

  public PinData upper() {
    return (this.pins[0] == null) ? null : new PinData(this.pins[0]);
  }

  public PinData lower() {
    return (this.pins[1] == null) ? null : new PinData(this.pins[1]);
  }

  public double distance() {
    final PinData upper = this.upper();
    final PinData lower = this.lower();
    if (upper == null || lower == null) {
      return -1;
    } else {
      return upper.distance(lower);
    }
  }

  public int amount() {
    return ((this.pins[0] == null) ? 0 : 1) + ((this.pins[1] == null) ? 0 : 1);
  }

  public void push(PinData pin) {
    final PinData tmp = (this.pins[0] == null) ? null : new PinData(this.pins[0]);
    this.pins[0] = (pin == null) ? null : new PinData(pin);
    this.pins[1] = tmp;
  }

  public void setError(String msg) {
    this.lastError = msg;
  }

  public String getError() {
    try {
      return this.lastError;
    } finally {
      this.lastError = null;
    }
  }
}

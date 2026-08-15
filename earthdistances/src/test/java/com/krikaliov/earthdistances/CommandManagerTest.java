package com.krikaliov.earthdistances;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class CommandManagerTest {
  private final ByteArrayOutputStream output = new ByteArrayOutputStream();
  private final String utf8 = StandardCharsets.UTF_8.name();
  private final String ln = System.lineSeparator();
  
  private boolean quit = false;
  @SuppressWarnings("rawtypes")
  protected final CommandFunction mockAppQuitFn = (Argument[] x) -> { this.quit = true; };

  private final PrintStream stream;
  private final CommandManager cmdManager;

  private final PinDataManager pins;
  private final PinDataViewer pinViewer;

  public CommandManagerTest() throws FileNotFoundException, UnsupportedEncodingException {
    this.stream = new PrintStream(this.output, true, this.utf8);

    this.cmdManager = new CommandManager(this.stream, this.mockAppQuitFn);

    this.pins = PinDataManager.getInstance();
    this.pinViewer = new PinDataViewer();
  }

  @Test
  public void testQuitCmd() {
    this.cmdManager.scan("quit");
    assertTrue(quit);
  }

  @Test
  public void testWrongCmd() throws UnsupportedEncodingException {
    this.cmdManager.scan("boil eggs");
    final String msg = this.output.toString(this.utf8);
    assertTrue(msg.endsWith(
      "boil is not recognized as a command!" + ln
    ));
  }

  @Test
  public void testCmdListCmd() throws UnsupportedEncodingException {
    this.cmdManager.scan("?");
    final String msg = this.output.toString(this.utf8);
    assertTrue(msg.endsWith(
      "List of available commands:" + ln +
      " - help" + ln +
      " - pin" + ln +
      " - clear" + ln +
      " - quit" + ln +
      " - ?" + ln +
      "Type 'help <cmd>' to get info details about any command." + ln
    ));
  }

  @Test
  public void testHelpDotCmd() throws UnsupportedEncodingException {
    this.cmdManager.scan("help .");
    final String msg = this.output.toString(this.utf8);
    assertTrue(msg.endsWith(
      "List of available commands:" + ln +
      " - help" + ln +
      " - pin" + ln +
      " - clear" + ln +
      " - quit" + ln +
      " - ?" + ln +
      "Type 'help <cmd>' to get info details about any command." + ln
    ));
  }

  @Test
  public void testHelpCmdListCmd() throws UnsupportedEncodingException {
    this.cmdManager.scan("help ?");
    final String msg = this.output.toString(this.utf8);
    assertTrue(msg.endsWith("Usage: ?" + ln));
  }

  @Test
  public void testHelpHelpCmd() throws UnsupportedEncodingException {
    this.cmdManager.scan("help help");
    final String msg = this.output.toString(this.utf8);
    assertTrue(msg.endsWith("Usage: help <cmd>" + ln));
  }

  @Test
  public void testHelpPinCmd() throws UnsupportedEncodingException {
    this.cmdManager.scan("help pin");
    final String msg = this.output.toString(this.utf8);
    assertTrue(msg.endsWith("Usage: pin [--lower] <theta> <phi> <color>" + ln));
  }

  @Test
  public void testHelpQuitCmd() throws UnsupportedEncodingException {
    this.cmdManager.scan("help quit");
    final String msg = this.output.toString(this.utf8);
    assertTrue(msg.endsWith("Usage: quit" + ln));
  }

  @Test
  public void testHelpClearCmd() throws UnsupportedEncodingException {
    this.cmdManager.scan("help clear");
    final String msg = this.output.toString(this.utf8);
    assertTrue(msg.endsWith("Usage: clear" + ln));
  }

  @Test
  public void testHelpBadArgCmd() throws UnsupportedEncodingException {
    this.cmdManager.scan("help boil");
    final String msg = this.output.toString(this.utf8);
    assertTrue(msg.endsWith(
      "Bad argument given for cmd: 'boil' unrecognized! Reason: not an option for this argument." + ln
    ));
  }

  @Test
  public void testHelpMisArgCmd() throws UnsupportedEncodingException {
    this.cmdManager.scan("help");
    final String msg = this.output.toString(this.utf8);
    assertTrue(msg.endsWith(
      "Missing argument for this command : cmd" + ln
    ));
  }

  @Test
  public void testPinCmd() {
    // Pushing 1 pin
    this.cmdManager.scan("pin 50 3 green");
    assertTrue(this.pins.amount() >= 1);
    assertNotNull(this.pins.upper());

    final PinData pin1 = this.pins.upper();
    assertEquals(50.0, pin1.getTheta(), 0.001);
    assertEquals(3.0, pin1.getPhi(), 0.001);
    assertEquals("green", pin1.getColor());

    assertTrue(this.pinViewer.toString().startsWith("[(50.0 3.0) , ("));

    // Pushing 2 pins with lower option
    this.cmdManager.scan("pin --lower 44 2 yellow");
    assertEquals(2, this.pins.amount());
    assertNotNull(this.pins.upper());
    assertNotNull(this.pins.lower());

    final PinData pin2 = this.pins.lower();
    assertEquals(44.0, pin2.getTheta(), 0.001);
    assertEquals(2.0, pin2.getPhi(), 0.001);
    assertEquals("yellow", pin2.getColor());

    assertEquals(this.pinViewer.toString(),
      "[(50.0 3.0) , (44.0 2.0)]<" + Double.toString(this.pins.distance()) + " km>"
    );

    assertTrue(pin1.equals(this.pins.upper()));
  }

  @Test
  public void testClearCmd() {
    this.cmdManager.scan("clear");

    assertEquals(0, this.pins.amount());
    assertNull(this.pins.lower());
    assertNull(this.pins.upper());

    assertEquals("[() , ()]<>", this.pinViewer.toString());
  }
}

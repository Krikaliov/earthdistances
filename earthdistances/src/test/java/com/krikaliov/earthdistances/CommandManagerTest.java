package com.krikaliov.earthdistances;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class CommandManagerTest {
  private final ByteArrayOutputStream output = new ByteArrayOutputStream();
  private final String utf8 = StandardCharsets.UTF_8.name();
  private final String ln = System.lineSeparator();
  
  private final App app;
  private final PrintStream stream;
  private final CommandManager cmdManager;

  public CommandManagerTest() throws FileNotFoundException, UnsupportedEncodingException {
    this.stream = new PrintStream(this.output, true, this.utf8);
    this.app = new App(854, 480);
    this.cmdManager = new CommandManager(this.app, this.stream);
  }

  @Test
  public void testQuitCmd() {
    this.cmdManager.scan("quit");
    assertFalse(this.app.isAlive());
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
      " - quit" + ln +
      " - ?" + ln +
      "Type 'help <cmd>' to get info details about any command." + ln
    ));
  }

  @Test
  public void testHelpCmdListCmd() throws UnsupportedEncodingException {
    this.cmdManager.scan("help ?");
    final String msg = this.output.toString(this.utf8);
    assertTrue(msg.endsWith(
      "Usage: ?" + ln
    ));
  }

  @Test
  public void testHelpHelpCmd() throws UnsupportedEncodingException {
    this.cmdManager.scan("help help");
    final String msg = this.output.toString(this.utf8);
    assertTrue(msg.endsWith(
      "Usage: help <cmd>" + ln
    ));
  }

  @Test
  public void testHelpPinCmd() throws UnsupportedEncodingException {
    this.cmdManager.scan("help pin");
    final String msg = this.output.toString(this.utf8);
    assertTrue(msg.endsWith(
      "Usage: pin <theta> <phi> <color>" + ln
    ));
  }

  @Test
  public void testHelpQuitCmd() throws UnsupportedEncodingException {
    this.cmdManager.scan("help quit");
    final String msg = this.output.toString(this.utf8);
    assertTrue(msg.endsWith(
      "Usage: quit" + ln
    ));
  }

  @Test
  public void testHelpBadArgCmd() throws UnsupportedEncodingException {
    this.cmdManager.scan("help clear");
    final String msg = this.output.toString(this.utf8);
    assertTrue(msg.endsWith(
      "Bad argument given for cmd: 'clear' unrecognized! Reason: not an option for this argument." + ln
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
    final PinDataManager pins = PinDataManager.getInstance();
    final PinDataViewer pinViewer = this.app.pinViewer();

    // Pushing 1 pin
    this.cmdManager.scan("pin 50 3 green");
    assertTrue(pins.amount() >= 1);
    assertNotNull(pins.upper());

    final PinData pin1 = pins.upper();
    assertEquals(50.0, pin1.getTheta(), 0.001);
    assertEquals(3.0, pin1.getPhi(), 0.001);
    assertEquals("green", pin1.getColor());

    assertTrue(pinViewer.toString().startsWith(
      "[(50.0 3.0) , ("
    ));

    // Pushing 2 pins
    this.cmdManager.scan("pin 44 2 yellow");
    assertEquals(2, pins.amount());
    assertNotNull(pins.upper());
    assertNotNull(pins.lower());

    final PinData pin2 = pins.upper();
    assertEquals(44.0, pin2.getTheta(), 0.001);
    assertEquals(2.0, pin2.getPhi(), 0.001);
    assertEquals("yellow", pin2.getColor());

    assertEquals(pinViewer.toString(),
      "[(44.0 2.0) , (50.0 3.0)]<" + Double.toString(pins.distance()) + " km>"
    );

    assertTrue(pin1.equals(pins.lower()));
  }
}

package com.krikaliov.earthdistances;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ArgumentTest {
  @SuppressWarnings("rawtypes")
  @Test
  public void ArgumentDouble() {
    final Argument argDouble = new ArgumentDouble("argDouble");
    final BadArgumentException bae = assertThrows(
      BadArgumentException.class,
      () -> argDouble.parse("zero")
    );

    assertEquals("argDouble", bae.getArgName());
    assertEquals("zero", bae.getArgValue());
    assertEquals("not a floating number", bae.getReason());
    
    final MissingArgumentException mae = assertThrows(
      MissingArgumentException.class,
      () -> argDouble.getValue()
    );

    assertEquals("argDouble", mae.getArgName());
    assertEquals("Missing argument for this command : argDouble", mae.getMessage());

    argDouble.parse("3.1415");
    assertEquals(3.1415, (Double) argDouble.getValue(), 0.0001);
  }

  @SuppressWarnings("rawtypes")
  @Test
  public void ArgumentStringSet() {
    final String[] args = {"one", "two", "three"};
    final Argument argStringSet = new ArgumentStringSet("argStringSet", args);
    final BadArgumentException e = assertThrows(
      BadArgumentException.class,
      () -> argStringSet.parse("four")
    );

    assertEquals("argStringSet", e.getArgName());
    assertEquals("four", e.getArgValue());
    assertEquals("not an option for this argument", e.getReason());
    
    final MissingArgumentException mae = assertThrows(
      MissingArgumentException.class,
      () -> argStringSet.getValue()
    );

    assertEquals("argStringSet", mae.getArgName());
    assertEquals("Missing argument for this command : argStringSet", mae.getMessage());

    argStringSet.parse("one");
    assertEquals("one", (String) argStringSet.getValue());
  }

  @SuppressWarnings("rawtypes")
  @Test
  public void ArgumentTag() {
    final Argument argTag = new ArgumentTag("test");

    argTag.parse("test");
    assertFalse((Boolean) argTag.getValue());
  
    argTag.parse("--test");
    assertTrue((Boolean) argTag.getValue());

    argTag.parse("--no-ff");
    assertFalse((Boolean) argTag.getValue());
  }
}

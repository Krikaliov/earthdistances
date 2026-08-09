package com.krikaliov.earthdistances;

public interface Argument<T> {
  /**
   * Parse the given input part and compute the associated value.
   * @return the associated value of this argument.
   */
  public void parse(String buf) throws BadArgumentException;

  /**
   * @return Name of this argument.
   */
  public String getArgName();

  /**
   * Called by the trigger command function when the argument
   * has already been parsed juste before. Return default value
   * when the argument is optional. Throws MissingArgumentException
   * otherwise.
   * @return the associated value of this argument.
   */
  public T getValue();
}

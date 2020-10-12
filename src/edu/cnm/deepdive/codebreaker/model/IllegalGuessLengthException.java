package edu.cnm.deepdive.codebreaker.model;

/**
 *
 */
public class IllegalGuessLengthException extends IllegalArgumentException{

  /**
   *
   */
  public IllegalGuessLengthException() {
  }

  /**
   *
   * @param message
   */
  public IllegalGuessLengthException(String message) {
    super(message);
  }

  /**
   *
   * @param message
   * @param cause
   */
  public IllegalGuessLengthException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   *
   * @param cause
   */
  public IllegalGuessLengthException(Throwable cause) {
    super(cause);
  }
}

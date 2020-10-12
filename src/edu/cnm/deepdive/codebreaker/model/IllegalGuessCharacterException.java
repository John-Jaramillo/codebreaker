package edu.cnm.deepdive.codebreaker.model;

/**
 *
 */
public class IllegalGuessCharacterException extends IllegalArgumentException {

  /**
   *
   */
  public IllegalGuessCharacterException() {
  }

  /**
   *
   * @param message
   */
  public IllegalGuessCharacterException(String message) {
    super(message);
  }

  /**
   *
   * @param message
   * @param cause
   */
  public IllegalGuessCharacterException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   *
   * @param cause
   */
  public IllegalGuessCharacterException(Throwable cause) {
    super(cause);
  }
}

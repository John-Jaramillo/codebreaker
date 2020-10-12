package edu.cnm.deepdive.codebreaker.model;

import edu.cnm.deepdive.codebreaker.model.Code.Guess;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Initiates a game to guess a randomly generated secret code.
 */
public class Game {

  private static final String GOOD_CHARACTER_PATTERN_FORMAT = "[%s]";
  private static final String ILLEGAL_LENGTH_MESSAGE =
      "Invalid guess length: code length is %d; guess length is %d.";
  private static final String ILLEGAL_CHARACTER_MESSAGE =
      "Guess includes invalid characters: pool is \"%s\"; guess included=\"%s\".";

  private final Code code;
  private final List<Guess> guesses;
  private final String pool;
  private final int length;
  private final String goodCharacterPattern;

  /**
   * Generates a secret code.
   *
   * @param pool Characters allowed for secret code.
   * @param length Number of characters in the secret code.
   * @param rng Source of randomness.
   */
  public Game(String pool, int length, Random rng) {
    code = new Code(pool, length, rng);
    guesses = new LinkedList<>();
    this.pool = pool;
    this.length = length;
    goodCharacterPattern = String.format(GOOD_CHARACTER_PATTERN_FORMAT, pool);
  }

  /**
   *
   * @return
   */
  public Code getCode() {
    return code;
  }

  /**
   * Returns list of guesses.
   */
  public List<Guess> getGuesses() {
    return Collections.unmodifiableList(guesses);
  }

  /**
   * Returns pool of characters allowed.
   */
  public String getPool() {
    return pool;
  }

  /**
   * Returns number of characters in secret code.
   */
  public int getLength() {
    return length;
  }

  /**
   * Returns number of times user has guessed secret code.
   */
  public int getGuessCount() {
    return guesses.size();
  }

  /**
   * Catches exceptions for correct length of guess and use of allowed characters.
   *
   * @param text The text the user inputs for their guess.
   * @return
   * @throws IllegalGuessLengthException
   * @throws IllegalGuessCharacterException
   */
  public Guess guess(String text)
      throws IllegalGuessLengthException, IllegalGuessCharacterException{
    if (text.length() != length) {
      throw new IllegalGuessLengthException(
          String.format(ILLEGAL_LENGTH_MESSAGE, length, text.length()));
    }
    String badCharacters = text.replaceAll(goodCharacterPattern, "");
    if (!badCharacters.isEmpty()) {
      throw new IllegalGuessCharacterException(
          String.format(ILLEGAL_CHARACTER_MESSAGE, pool, badCharacters));
    }
    Guess guess = code.new Guess(text);
    guesses.add(guess);
    return guess;
  }

  /**
   * Restarts current game with the same secret code.
   */
  public void restart() {
    guesses.clear();
  }
}
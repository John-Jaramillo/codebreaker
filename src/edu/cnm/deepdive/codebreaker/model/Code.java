package edu.cnm.deepdive.codebreaker.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Encapsulates a randomly generated secret code. This class also includes the inner {@code Guess}
 * class.
 */
public class Code {

  private final char[] secret;

  /**
   * Initializes this instance by generating a ramdom {@link String} of length {@code length} with
   * characters taken from {@code pool}. A source of randomness must be provided in {@code rng}.
   *
   * @param pool Characters allowed in secret code that represent a color.
   * @param length Number of characters in the secret code,
   * @param rng Random number generator.
   */
  public Code(String pool, int length, Random rng) {
    secret = new char[length];
    for (int i = 0; i < secret.length; i++) {
      secret[i] = pool.charAt(rng.nextInt(pool.length()));
    }
  }

  @Override
  public String toString() {
    return new String(secret);
  }

  /**
   * A single attempt to guess a {@link Code}.
   */
  public class Guess {

    private static final String STRING_FORMAT = "{text: \"%s\", correct: %d, close: %d}";

    private final String text;
    private final int correct;
    private final int close;

    /**
     * Initializes this instance by computing the number of characters in the {@code text} that
     * are in the same position in the {@link Code}, as well as the number of additional matching
     * characters that are not in the same position.
     *
     * @param text Guess content.
     */
    public Guess(String text) {
      this.text = text;
      int correct = 0;
      int close = 0;

      Map<Character, Set<Integer>> letterMap = getLetterMap(text);

      char[] work = Arrays.copyOf(secret, secret.length);

      for (int i = 0; i < work.length; i++) {
        char letter = work[i];
        Set<Integer> positions = letterMap.getOrDefault(letter, Collections.emptySet());
        if (positions.contains(i)) {
          correct++;
          positions.remove(i);
          work[i] = 0;
        }
      }

      for (char letter : work) {
        if (letter != 0) {
          Set<Integer> positions = letterMap.getOrDefault(letter, Collections.emptySet());
          if (positions.size() > 0) {
            close++;
            Iterator<Integer> iter = positions.iterator();
            iter.next();
            iter.remove();
          }
        }
      }

      this.correct = correct;
      this.close = close;
    }

    private Map<Character, Set<Integer>> getLetterMap(String text) {
      Map<Character, Set<Integer>> letterMap = new HashMap<>();
      char[] letters = text.toCharArray();
      for (int i = 0; i < letters.length; i++) {
        char letter = letters[i];
        Set<Integer> positions = letterMap.getOrDefault(letter, new HashSet<>());
        positions.add(i);
        letterMap.putIfAbsent(letter, positions);
      }
      return letterMap;
    }

    @Override
    public String toString() {
      return String.format(STRING_FORMAT, text, correct, close);
    }

    /**
     * Returns the text of the guess.
     */
    public String getText() {
      return text;
    }

    /**
     * Returns the number of characters in the guess that are in the secret code and are in the
     * same position in both.
     */
    public int getCorrect() {
      return correct;
    }

    /**
     * Returns the number of characters in the guess that are in the code but which are not in the
     * same position in both. Any character already matched in the code by {@link #getCorrect()}
     * are not included in this result.
     */
    public int getClose() {
      return close;
    }
  }

}

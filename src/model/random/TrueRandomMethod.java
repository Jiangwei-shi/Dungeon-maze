package model.random;

import java.util.Random;


/**
 * this is the TrueRandomMethod. it used in the project2 code.
 */
public class TrueRandomMethod implements RandomMethod {
  private final int max;
  private final int min;

  /**
   * this the trueRandomMethod constructor.
   *
   * @param type true or false.
   * @param max  max value.
   * @param min  min value.
   */
  public TrueRandomMethod(String type, int max, int min) {
    this.max = max;
    this.min = min;
  }

  @Override
  public int getRandomNumber() {
    Random randomMethod = new Random();
    int result = randomMethod.nextInt(max) % (max - min + 1) + min;
    return result;
  }
}

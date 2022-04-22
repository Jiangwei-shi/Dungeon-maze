package model.random;

/**
 * Factory pattern.
 */
public class RandomFactory {
  /**
   * this is the getRandom function.
   *
   * @param randomType True or False.
   * @param max        this is the Max value.
   * @param min        this is the Min value.
   * @return the random number.
   * @throws IllegalArgumentException when the Max smaller than Min or the
   *                                  type is not true or false.
   */
  public static RandomMethod getRandom(String randomType, int max, int min)
      throws IllegalArgumentException {
    if (min > max) {
      throw (new IllegalArgumentException("the max value should be bigger than min value"));
    }
    if (randomType.equalsIgnoreCase("true")) {
      return new TrueRandomMethod("true", max, min);
    } else if (randomType.equalsIgnoreCase("false")) {
      return new FalseRandomMethod("false", max, min);
    } else {
      throw (new IllegalArgumentException("the type just have true or false"));
    }
  }
}

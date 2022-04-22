package model.random;

/**
 * this the FalseRandomMethod function. used in the test case.
 */
public class FalseRandomMethod implements RandomMethod {
  private final int min;

  /**
   * this function just return the Min value. In order to let us predict the value.
   *
   * @param type True or false.
   * @param max  the Max value, but we don't use it in this function.
   * @param min  the min value, we set this value as the return.
   */
  public FalseRandomMethod(String type, int max, int min) {
    this.min = min;
  }

  @Override
  public int getRandomNumber() {
    return min;
  }
}


import static org.junit.Assert.assertEquals;
import model.random.RandomFactory;
import model.random.RandomMethod;
import org.junit.Test;

/**
 * test the random function.
 */
public class RandomMethodTest {

  @Test
  public void getRandomNumber() {
    RandomMethod randomMethod = RandomFactory.getRandom("false", 5, 0);
    assertEquals(0, randomMethod.getRandomNumber());
  }
}

import static org.junit.Assert.assertEquals;
import model.dungeon.Dungeon;
import model.dungeon.Pair;
import model.dungeon.Player;
import model.dungeon.PlayerImpl;
import model.kruskalalgorithm.Direction;
import org.junit.Before;
import org.junit.Test;

/**
 * this is the player test class.
 */
public class PlayerTest {

  private Player player;
  private Dungeon testDungeon;

  @Before
  public void setUp() throws Exception {
    player = new PlayerImpl();
    Pair<Integer, Integer> pair = new Pair<>(0, 1);
    player.setCurrentLocation(pair);
  }

  @Test
  public void getCurrentLocation() {
    Pair<Integer, Integer> pair = new Pair<>(0, 1);
    assertEquals(pair, player.getCurrentLocation());
  }

  @Test
  public void getTreasureList() {
    String expect = "[Diamond, Rubies]";
    assertEquals(expect, player.getTreasureList().toString());
  }

  @Test
  public void getPossibleMove() {

    player = testDungeon.getPlayer();
    String expected = "[SOUTH, EAST, WEST]";
    assertEquals(expected,testDungeon.getPossibleMoves().toString());
  }

  @Test
  public void testPickUpTreasure() {
    String expect = "0=1";
    player = testDungeon.getPlayer();
    assertEquals(expect,player.getCurrentLocation().toString());
    testDungeon.makeMove(Direction.EAST);
    String expectTreasure = "Diamond";
    assertEquals(expectTreasure,player.getTreasureList().get(0));
  }

}

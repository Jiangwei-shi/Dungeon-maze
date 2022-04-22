
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.dungeon.AbstractDungeon;
import model.dungeon.Dungeon;
import model.dungeon.NonWrapDungeon;
import model.dungeon.Pair;
import model.dungeon.Player;
import model.dungeon.WrapDungeon;
import model.kruskalalgorithm.CaveType;
import model.kruskalalgorithm.Direction;
import org.junit.Before;
import org.junit.Test;

/**
 * this is dungeon test class.
 */
public class DungeonTest {

  private Dungeon wrapDungeon;
  private Dungeon nonWrapDungeon;
  private Dungeon testDungeon;

  @Before
  public void setUp() throws Exception {
    wrapDungeon = new WrapDungeon(5, 5, 3, 40, 5);
    nonWrapDungeon = new NonWrapDungeon(5, 5, 0, 40, 5);
    testDungeon = new AbstractDungeon();
  }

  @Test
  public void getNumberOfRows() {
    assertEquals(5, nonWrapDungeon.getNumberOfRows());
    assertEquals(5, wrapDungeon.getNumberOfRows());
  }

  @Test
  public void testDistanceOfStartAndEnd() {
    Pair<Integer, Integer> pair1 = nonWrapDungeon.getStartingPoint();
    Pair<Integer, Integer> pair2 = nonWrapDungeon.getEndPoint();
    int distance =
        Math.abs(pair1.fst - pair2.fst) + Math.abs(pair1.snd - pair2.snd);
    assertTrue(distance >= 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidRows() {
    wrapDungeon = new WrapDungeon(-5, 5, 3, 40, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidNonWrapRows() {
    wrapDungeon = new NonWrapDungeon(-5, 5, 3, 40, 5);
  }

  @Test
  public void getNumberOfColumns() {
    assertEquals(5, nonWrapDungeon.getNumberOfColumns());
    assertEquals(5, wrapDungeon.getNumberOfColumns());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidColumns() {
    wrapDungeon = new WrapDungeon(5, -5, 3, 40, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidNonWrapColumns() {
    wrapDungeon = new NonWrapDungeon(5, -5, 3, 40, 5);
  }

  @Test
  public void isWrapped() {
    assertFalse(nonWrapDungeon.getIsWrapped());
    assertTrue(wrapDungeon.getIsWrapped());
  }

  @Test
  public void getInterconnectivity() {
    assertEquals(0, nonWrapDungeon.getInterconnectivity());
    assertEquals(3, wrapDungeon.getInterconnectivity());
  }

  @Test
  public void getPossibleMoves() {
    StringBuilder stringBuilder = new StringBuilder();
    for (Direction direction : testDungeon.getPossibleMoves()) {
      stringBuilder.append(direction.toString() + " ");
    }
    assertEquals("SOUTH EAST WEST ", stringBuilder.toString());
  }

  @Test
  public void getCaveTypeList() {
    nonWrapDungeon = new NonWrapDungeon(5, 5, 0, 0, 5);
    String expected = "{0=0=Tunnels, 0=1=Caves, 0=2=Caves, 0=3=Caves, 0=4=Tunnels, 1=0=Tunnels, "
        + "1=1=Tunnels, 1=2=Tunnels, 1=3=Tunnels, 1=4=Tunnels, 2=0=Tunnels, 2=1=Tunnels, "
        + "2=2=Tunnels, 2=3=Tunnels, 2=4=Tunnels, 3=0=Tunnels, 3=1=Tunnels, 3=2=Tunnels, "
        + "3=3=Tunnels, 3=4=Tunnels, 4=0=Caves, 4=1=Caves, 4=2=Caves, 4=3=Caves, 4=4=Caves}";
    assertEquals(expected, nonWrapDungeon.getCaveTypeList().toString());
  }

  @Test
  public void listOfNodeRelation() {
    nonWrapDungeon = new NonWrapDungeon(5, 5, 0, 0, 5);
    String expected = "{0=0=[1=0, 0=1], 0=1=[0=0, 1=1, 0=2], 0=2=[0=1, 1=2, 0=3], "
        + "0=3=[0=2, 1=3, 0=4], 0=4=[0=3, 1=4], 1=0=[0=0, 2=0], 1=1=[0=1, 2=1], 1=2=[0=2, 2=2], "
        + "1=3=[0=3, 2=3], 1=4=[0=4, 2=4], 2=0=[1=0, 3=0], 2=1=[1=1, 3=1], 2=2=[1=2, 3=2], "
        + "2=3=[1=3, 3=3], 2=4=[1=4, 3=4], 3=0=[2=0, 4=0], 3=1=[2=1, 4=1], 3=2=[2=2, 4=2],"
        + " 3=3=[2=3, 4=3], 3=4=[2=4, 4=4], 4=0=[3=0], 4=1=[3=1], 4=2=[3=2], 4=3=[3=3], 4=4=[3=4]}";
    assertEquals(expected, nonWrapDungeon.listOfNodeRelation().toString());
  }

  @Test
  public void treasurePercentage() {
    int numberOfTreasureCave = 0;
    nonWrapDungeon = new NonWrapDungeon(5, 5, 0, 100, 5);
    for (CaveType caveType : nonWrapDungeon.getCaveTypeList().values()) {
      if (!caveType.equals(CaveType.Tunnels)) {
        numberOfTreasureCave++;
      }
    }
    assertEquals(8, numberOfTreasureCave);
  }

  @Test
  public void getStartingPoint() {
    String expected = "0=2";
    assertEquals(expected, testDungeon.getStartingPoint().toString());
  }

  @Test
  public void PlayerStartingPoint() {
    Player player = nonWrapDungeon.getPlayer();
    assertEquals(player.getCurrentLocation(), nonWrapDungeon.getStartingPoint());
  }

  @Test
  public void getEndPoint() {
    String expected = "4=4";
    assertEquals(expected, testDungeon.getEndPoint().toString());
  }

  @Test
  public void getDirection() {
    Pair<Integer, Integer> point1 = new Pair<>(0, 0);
    Pair<Integer, Integer> point2 = new Pair<>(1, 0);
    Direction direction = nonWrapDungeon.getDirection(point1, point2);
    assertEquals(Direction.SOUTH, direction);
  }

  @Test
  public void validShoot() {
    assertEquals(0, testDungeon.shoot("0", "SOUTH"));
  }

  @Test
  public void pickUpArrow() {
    testDungeon.pickUp("Arrow");
    assertEquals("[Arrow, Arrow, Arrow]"
        , testDungeon.getPlayer().getTreasureList().toString());
  }

  @Test
  public void Move() {
    testDungeon.makeMove(Direction.SOUTH);
    assertEquals("1=2"
        , testDungeon.getPlayer().getCurrentLocation().toString());
  }
}

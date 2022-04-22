package model.dungeon;

//import com.sun.tools.javac.util.Pair;

import model.kruskalalgorithm.BasicMaze;
import model.kruskalalgorithm.CaveType;
import model.kruskalalgorithm.Direction;
import model.kruskalalgorithm.ModifiedKruskal;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


/**
 * this is the AbstractDungeon. in the wrap dungeon or non warp dungeon class.
 * they will pass the parameter and the abstract class will build a maze.
 * Implements maze interface.
 */
public class AbstractDungeon implements Dungeon {
  protected final int numberOfRows;
  protected final int numberOfColumns;
  protected final boolean isWrapped;
  protected final int interconnectivity;
  protected final double treasurePercentage;
  protected final BasicMaze BasicMaze;
  protected final int numberOfOtyugh;
  protected Pair<Integer, Integer> startingPoint;
  protected Pair<Integer, Integer> endPoint;
  protected final Player player;
  protected final Map<Pair<Integer, Integer>, ArrayList<String>> itemsInCave;

  /**
   * Constructor for AbstractDungeon.
   *
   * @param numberOfRows       number of rows in the maze.
   * @param numberOfColumns    number of columns in the maze.
   * @param interconnectivity  number of remaining walls in the room maze - specified by user.
   * @param isWrapped          tells if room is wrapping room maze or not.
   * @param treasurePercentage the percentage of treasure in the cave.
   * @throws IllegalArgumentException if any arguments are invalid.
   */
  public AbstractDungeon(int numberOfRows, int numberOfColumns, boolean isWrapped,
                         int interconnectivity, double treasurePercentage, int numberOfOtyugh)
      throws IllegalArgumentException {
    if (numberOfRows < 5 || numberOfColumns < 5) {
      throw new IllegalArgumentException("the number of rows should be bigger than 5"
          + "the number of columns should be bigger than 5");
    }
    if (interconnectivity < 0) {
      throw new IllegalArgumentException("the interconnectivity should bigger than 0");
    }
    if (treasurePercentage < 0 || treasurePercentage > 100) {
      throw new IllegalArgumentException("the treasure percentage ranges are 0 to 100");
    }
    this.numberOfRows = numberOfRows;
    this.numberOfColumns = numberOfColumns;
    this.isWrapped = isWrapped;
    this.numberOfOtyugh = numberOfOtyugh;
    if ((isWrapped && interconnectivity > maxNumberOfInterconnectivityForWrapDungeon())
        || (!isWrapped && interconnectivity > maxNumberOfInterconnectivityForNonWrapDungeon())) {
      throw new IllegalArgumentException(" Number of Interconnectivity not possible.");
    }
    this.interconnectivity = interconnectivity;
    this.treasurePercentage = treasurePercentage;
    this.player = new PlayerImpl();
    this.BasicMaze = new BasicMaze(numberOfRows, numberOfColumns, isWrapped);
    createMaze(treasurePercentage, numberOfOtyugh);
    this.startingPoint = BasicMaze.getStartingPoint();
    this.endPoint = BasicMaze.getEndPoint();
    this.itemsInCave = BasicMaze.getItemsInCave();
  }

  /**
   * just for test.
   *
   * @throws IllegalArgumentException if any arguments are invalid.
   */
  public AbstractDungeon() throws IllegalArgumentException {
    this.numberOfRows = 5;
    this.numberOfColumns = 5;
    this.isWrapped = false;
    this.interconnectivity = 0;
    this.treasurePercentage = 100;
    this.numberOfOtyugh = 7;
    this.player = new PlayerImpl();
    this.BasicMaze = new BasicMaze(5, 5, false);
    ModifiedKruskal modifiedKruskal = new ModifiedKruskal();
    modifiedKruskal.createMazeWithKruskal(BasicMaze, interconnectivity);
    BasicMaze.fakeSetStartAndEndPoint();
    BasicMaze.fakeAssignTreasure(treasurePercentage);
    BasicMaze.assignArrow(treasurePercentage);
    BasicMaze.assignOtyughs(numberOfOtyugh);
    player.setCurrentLocation(BasicMaze.getStartingPoint());
    this.startingPoint = BasicMaze.getStartingPoint();
    this.endPoint = BasicMaze.getEndPoint();
    this.itemsInCave = BasicMaze.getItemsInCave();
  }

  /**
   * just for test.
   *
   * @throws IllegalArgumentException if any arguments are invalid.
   */
  public AbstractDungeon(String string) throws IllegalArgumentException {
    this.numberOfRows = 5;
    this.numberOfColumns = 5;
    this.isWrapped = false;
    this.interconnectivity = 0;
    this.treasurePercentage = 0;
    this.numberOfOtyugh = 0;
    this.player = new PlayerImpl();
    this.BasicMaze = new BasicMaze(5, 5, false);
    ModifiedKruskal modifiedKruskal = new ModifiedKruskal();
    modifiedKruskal.createMazeWithKruskal(BasicMaze, interconnectivity);
    BasicMaze.fakeSetStartAndEndPoint();
    BasicMaze.fakeAssignTreasure(treasurePercentage);
    BasicMaze.assignArrow(treasurePercentage);
    BasicMaze.assignOtyughs(numberOfOtyugh);
    player.setCurrentLocation(BasicMaze.getStartingPoint());
    this.startingPoint = BasicMaze.getStartingPoint();
    this.endPoint = BasicMaze.getEndPoint();
    this.itemsInCave = BasicMaze.getItemsInCave();
  }

  private void createMaze(double treasurePercentage, int numberOfOtyugh) {
    ModifiedKruskal modifiedKruskal = new ModifiedKruskal();
    modifiedKruskal.createMazeWithKruskal(BasicMaze, interconnectivity);
    BasicMaze.setStartAndEndPoint();
    BasicMaze.assignTreasure(treasurePercentage);
    BasicMaze.assignArrow(treasurePercentage);
    BasicMaze.assignOtyughs(numberOfOtyugh);
    player.setCurrentLocation(BasicMaze.getStartingPoint());
  }

  private int maxNumberOfInterconnectivityForNonWrapDungeon() {
    int totalEdges = 2 * numberOfRows * numberOfColumns - numberOfRows
        - numberOfColumns;
    return totalEdges - (numberOfRows * numberOfColumns) + 1;
  }

  private int maxNumberOfInterconnectivityForWrapDungeon() {
    int totalEdges = 2 * numberOfRows * numberOfColumns;
    return totalEdges - (numberOfRows * numberOfColumns) + 1;
  }

  @Override
  public int getNumberOfRows() {
    return numberOfRows;
  }

  @Override
  public int getNumberOfColumns() {
    return numberOfColumns;
  }

  @Override
  public boolean getIsWrapped() {
    return isWrapped;
  }

  @Override
  public int getNumberOfOtyugh() {
    return numberOfOtyugh;
  }

  @Override
  public double getTreasurePercentage() {
    return treasurePercentage;
  }

  @Override
  public int getInterconnectivity() {
    return interconnectivity;
  }

  @Override
  public BasicMaze getDungeon() {
    return BasicMaze;
  }

  @Override
  public void pickUp(String string) {
    if (!itemsInCave.get(player.getCurrentLocation()).contains(string)) {
      throw new NoSuchElementException("there are no such items in the cave");
    }
    ArrayList<String> newItemList = itemsInCave.get(player.getCurrentLocation());
    newItemList.remove(string);
    player.pickUp(string);
    itemsInCave.replace(player.getCurrentLocation(), newItemList);
  }

  @Override
  public int detectOtyugh() {
    Pair<Integer, Integer> original = player.getCurrentLocation();
    int numberOfOtyugh = 0;
    for (Direction direction : BasicMaze.getPossibleMoves(player.getCurrentLocation())) {
      makeMove(direction);
      if (getItemsInCave().get(player.getCurrentLocation()).contains("Otyugh")
          || getItemsInCave().get(player.getCurrentLocation()).contains("halfLifeOtyugh")) {
        player.setCurrentLocation(original);
        return 2;
      }
      for (Direction direction2 : BasicMaze.getPossibleMoves(player.getCurrentLocation())) {
        makeMove(direction2);
        if (getItemsInCave().get(player.getCurrentLocation()).contains("Otyugh")
            || getItemsInCave().get(player.getCurrentLocation()).contains("halfLifeOtyugh")) {
          numberOfOtyugh++;
        }
        makeMove(oppositeDirection(direction2));
      }
      makeMove(oppositeDirection(direction));
    }
    player.setCurrentLocation(original);
    if (numberOfOtyugh == 0) {
      return 0;
    } else if (numberOfOtyugh == 1) {
      return 1;
    } else {
      return 2;
    }
  }

  /**
   * did shoot return 0.
   * shoot one time return 1.
   * kill return 2.
   * doesn't have arrow return.
   *
   * @param caveNumber the number of through caves.
   * @param direction  the direction.
   * @return int.
   */
  @Override
  public int shoot(String caveNumber, String direction) {
    Pair<Integer, Integer> original = player.getCurrentLocation();
    if (!player.getTreasureList().contains("Arrow")) {
      return 3;
    }
    Direction dir;
    switch (direction) {
      case "NORTH":
        dir = Direction.NORTH;
        break;
      case "EAST":
        dir = Direction.EAST;
        break;
      case "SOUTH":
        dir = Direction.SOUTH;
        break;
      default:
        dir = Direction.WEST;
        break;
    }

    int distance = Integer.parseInt(caveNumber);
    if (distance > 2) {
      throw new IllegalArgumentException("the number just can be 1 or 2");
    }
    while (distance != 0) {

      if (!getPossibleMoves().contains(dir)) {
        player.loose();
        return 0;
      }
      makeMove(dir);
      if (getCaveTypeList().get(player.getCurrentLocation()) == CaveType.Caves) {
        distance--;
        //dir = oppositeDirection(dir);
      } else {
        for (Direction direction1 : getPossibleMoves()) {

          if (direction1 != oppositeDirection(dir)) {
            dir = direction1;
          }
        }
      }
    }
    if (getItemsInCave().get(player.getCurrentLocation()).contains("Otyugh")) {
      ArrayList<String> newItems = itemsInCave.get(player.getCurrentLocation());
      newItems.remove("Otyugh");
      newItems.add("halfLifeOtyugh");
      itemsInCave.replace(player.getCurrentLocation(), newItems);
      player.setCurrentLocation(original);
      player.loose();
      return 1;
    } else if (getItemsInCave().get(player.getCurrentLocation()).contains("halfLifeOtyugh")) {
      ArrayList<String> newItems = itemsInCave.get(player.getCurrentLocation());
      newItems.remove("halfLifeOtyugh");
      itemsInCave.replace(player.getCurrentLocation(), newItems);
      player.setCurrentLocation(original);
      player.loose();
      return 2;
    } else {
      player.setCurrentLocation(original);
      player.loose();
      return 0;
    }
  }

  @Override
  public boolean isGameOver() {
    return player.getCurrentLocation().equals(endPoint);
  }

  private Direction oppositeDirection(Direction direction) {
    if (direction == Direction.EAST) {
      return Direction.WEST;
    } else if (direction == Direction.SOUTH) {
      return Direction.NORTH;
    } else if (direction == Direction.WEST) {
      return Direction.EAST;
    } else {
      return Direction.SOUTH;
    }
  }

  @Override
  public Direction getDirection(Pair<Integer, Integer> point1, Pair<Integer, Integer> point2) {


    if (point2.fst - point1.fst == 1
        || point2.fst - point1.fst == -numberOfRows + 1) {
      return Direction.SOUTH;
    }
    if (point2.fst - point1.fst == -1
        || point2.fst - point1.fst == numberOfRows - 1) {
      return Direction.NORTH;
    }
    if (point2.snd - point1.snd == 1
        || point2.snd - point1.snd == -numberOfRows + 1) {
      return Direction.EAST;
    }
    if (point2.snd - point1.snd == -1
        || point2.snd - point1.snd == numberOfRows - 1) {
      return Direction.WEST;
    }
    return null;
  }

  @Override
  public List<String> getTreasureList() {
    return player.getTreasureList();
  }

  @Override
  public Pair<Integer, Integer> getStartingPoint() {
    return startingPoint;
  }

  @Override
  public Pair<Integer, Integer> getEndPoint() {
    return endPoint;
  }


  @Override
  public EnumSet<Direction> getPossibleMoves() {
    return BasicMaze.getPossibleMoves(player.getCurrentLocation());
  }

  /**
   * Get the current moves of the player.
   *
   * @param row this is the row of userCurrent location.
   * @param column this is the column of userCurrent location.
   * @return the possible moves of the player from current location
   */
  @Override
  public EnumSet<Direction> getPossibleMoves(int row, int column) {
    Pair<Integer, Integer> pair = new Pair<>(row, column);
    return BasicMaze.getPossibleMoves(pair);
  }

  @Override
  public Map<Pair<Integer, Integer>, ArrayList<String>> getItemsInCave() {
    return itemsInCave;
  }

  @Override
  public Player getPlayer() {
    return player;
  }

  @Override
  public Map<Pair<Integer, Integer>, CaveType> getCaveTypeList() {
    return BasicMaze.getCaveTypeList();
  }

  @Override
  public Map<Pair<Integer, Integer>, ArrayList<Pair<Integer, Integer>>> listOfNodeRelation() {
    return BasicMaze.getBasicMazeAdjacencyList();
  }

  @Override
  public void makeMove(Direction direction) throws IllegalArgumentException {

    if (!getPossibleMoves().contains(direction)) {
      throw new IllegalArgumentException("Invalid move.");
    }
    Pair<Integer, Integer> currentLocation = player.getCurrentLocation();
    Pair<Integer, Integer> newLocation = null;
    switch (direction) {
      case EAST:
        newLocation = new Pair<>(currentLocation.fst,
            (currentLocation.snd + 1 + numberOfColumns) % numberOfColumns);
        break;
      case WEST:
        newLocation = new Pair<>(currentLocation.fst,
            (currentLocation.snd - 1 + numberOfColumns) % numberOfColumns);
        break;
      case NORTH:
        newLocation = new Pair<>((currentLocation.fst - 1 + numberOfRows)
            % numberOfRows, currentLocation.snd);
        break;
      case SOUTH:
        newLocation = new Pair<>((currentLocation.fst + 1 + numberOfRows)
            % numberOfRows, currentLocation.snd);
        break;
      default:
        throw new IllegalArgumentException("Invalid action.");
    }
    player.setCurrentLocation(newLocation);
  }

}



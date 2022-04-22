package model.kruskalalgorithm;


import model.dungeon.Pair;
import model.random.RandomFactory;
import model.random.RandomMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


/**
 * this is a Basic Maze, you can regard it as a maze which interconnection is max value,
 * every two adjacent points are connected to each other.
 */
public class BasicMaze {

  private final int rows;
  private final int columns;
  private final boolean isWrapped;
  private Pair<Integer, Integer> startingPoint;
  private Pair<Integer, Integer> endPoint;
  private final Map<Pair<Integer, Integer>, ArrayList<Pair<Integer, Integer>>> listOfNodeRelation;
  private final Map<Pair<Integer, Integer>, CaveType> caveTypeList;
  private final Map<Pair<Integer, Integer>, ArrayList<String>> itemsInCave;

  /**
   * this is the BasicMaze class constructor.
   *
   * @param numberOfRows    number of rows.
   * @param numberOfColumns number of columns.
   * @param isWrapped       is wrap or not.
   */
  public BasicMaze(int numberOfRows, int numberOfColumns, boolean isWrapped) {
    this.rows = numberOfRows;
    this.columns = numberOfColumns;
    this.isWrapped = isWrapped;
    listOfNodeRelation = new HashMap<Pair<Integer, Integer>, ArrayList<Pair<Integer, Integer>>>();
    caveTypeList = new HashMap<>();
    itemsInCave = new HashMap<>();
    createMaze();
  }

  private void createMaze() {
    Pair<Integer, Integer> temp;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        ArrayList<Pair<Integer, Integer>> nodeList = new ArrayList<>();
        if (isWrapped || i + 1 < rows) {
          temp = new Pair<>((i + 1 + rows) % rows, j);
          nodeList.add(temp);
        }

        if (isWrapped || j + 1 < columns) {
          temp = new Pair<>(i, (j + 1 + columns) % columns);
          nodeList.add(temp);
        }

        if (isWrapped || i - 1 >= 0) {
          temp = new Pair<>((i - 1 + rows) % rows, j);
          nodeList.add(temp);
        }

        if (isWrapped || j - 1 >= 0) {
          temp = new Pair<>(i, (j - 1 + columns) % columns);
          nodeList.add(temp);
        }
        Pair<Integer, Integer> node = new Pair<>(i, j);
        listOfNodeRelation.put(node, nodeList);
        if (listOfNodeRelation.get(node).size() == 2) {
          caveTypeList.put(node, CaveType.Tunnels);
        } else {
          caveTypeList.put(node, CaveType.Caves);
        }

      }
    }
    for (Pair key : listOfNodeRelation.keySet()) {
      itemsInCave.put(key, new ArrayList(Collections.singletonList("empty")));
    }
  }

  /**
   * get the row of the maze.
   *
   * @return number of rows.
   */
  public int getRows() {
    return rows;
  }

  /**
   * get the columns of the maze.
   *
   * @return number of columns.
   */
  public int getColumns() {
    return columns;
  }

  /**
   * get isWrap or not.
   *
   * @return wrap or not.
   */
  public boolean isWrapped() {
    return isWrapped;
  }

  /**
   * get the start point location.
   *
   * @return the position of the start point.
   */
  public Pair<Integer, Integer> getStartingPoint() {
    return startingPoint;
  }

  /**
   * get the end point location.
   *
   * @return the position of the end point.
   */
  public Pair<Integer, Integer> getEndPoint() {
    return endPoint;
  }

  /**
   * get the relationship of the maze.
   *
   * @return the relationship in each node.
   */
  public Map<Pair<Integer, Integer>, ArrayList<Pair<Integer,
      Integer>>> getBasicMazeAdjacencyList() {
    return listOfNodeRelation;
  }

  /**
   * Gets the caves type for each cave of the BasicMaze.
   */
  public Map<Pair<Integer, Integer>, CaveType> getCaveTypeList() {
    return caveTypeList;
  }

  /**
   * get the items in the caves.
   *
   * @return the items in each cave.
   */
  public Map<Pair<Integer, Integer>, ArrayList<String>> getItemsInCave() {
    return itemsInCave;
  }

  /**
   * get possible moves.
   *
   * @param location the location.
   * @return the possible of move set.
   */
  public EnumSet<Direction> getPossibleMoves(Pair<Integer, Integer> location) {

    if (location.fst >= rows || location.fst < 0 || location.snd >= columns
        || location.snd < 0) {
      throw new IllegalArgumentException("Invalid location.");
    }
    ArrayList<Pair<Integer, Integer>> list = listOfNodeRelation.get(location);
    EnumSet<Direction> directions = EnumSet.noneOf(Direction.class);
    Pair<Integer, Integer> node;

    //North
    if (isWrapped) {
      node = new Pair<>((location.fst - 1 + rows) % rows, location.snd);
    } else {
      node = new Pair<>((location.fst - 1), location.snd);
    }
    if (list.contains(node)) {
      directions.add(Direction.NORTH);
    }

    //South
    if (isWrapped) {
      node = new Pair<>((location.fst + 1 + rows) % rows, location.snd);
    } else {
      node = new Pair<>((location.fst + 1), location.snd);
    }
    if (list.contains(node)) {
      directions.add(Direction.SOUTH);
    }

    //East
    if (isWrapped) {
      node = new Pair<>(location.fst, (location.snd + 1 + columns) % columns);
    } else {
      node = new Pair<>(location.fst, (location.snd + 1));
    }
    if (list.contains(node)) {
      directions.add(Direction.EAST);
    }

    //West
    if (isWrapped) {
      node = new Pair<>(location.fst, (location.snd - 1 + columns) % columns);
    } else {
      node = new Pair<>(location.fst, (location.snd - 1));
    }
    if (list.contains(node)) {
      directions.add(Direction.WEST);
    }

    return directions;
  }

  /**
   * After use modified kruskal function. it will create a new maze.
   *
   * @param edges the ArrayList of interconnect edges.
   */
  public void createBasicMazeFromEdges(ArrayList<Pair<Pair<Integer, Integer>,
      Pair<Integer, Integer>>> edges) {
    listOfNodeRelation.replaceAll((p, v) -> new ArrayList<>());
    for (Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> pair : edges) {
      ArrayList<Pair<Integer, Integer>> temp = listOfNodeRelation.get(pair.fst);
      temp.add(pair.snd);
      listOfNodeRelation.put(pair.fst, temp);
      ArrayList<Pair<Integer, Integer>> temp2 = listOfNodeRelation.get(pair.snd);
      temp2.add(pair.fst);
      listOfNodeRelation.put(pair.snd, temp2);
    }
    for (Pair key : listOfNodeRelation.keySet()) {
      if (listOfNodeRelation.get(key).size() == 2) {
        caveTypeList.put(key, CaveType.Tunnels);
      } else {
        caveTypeList.put(key, CaveType.Caves);
      }
    }
  }

  /**
   * get the Edges of the maze.
   *
   * @return all the edges.
   */
  public ArrayList<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> getEdges() {
    ArrayList<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> edges = new ArrayList<>();
    HashSet<Pair<Integer, Integer>> visited = new HashSet<>();
    for (Pair<Integer, Integer> pair : listOfNodeRelation.keySet()) {
      visited.add(pair);
      for (Pair<Integer, Integer> pair1 : listOfNodeRelation.get(pair)) {
        if (!visited.contains(pair1)) {
          edges.add(new Pair<>(pair, pair1));
        }
      }
    }
    return edges;
  }

  /**
   * get number of all the node in maze.
   *
   * @return number of node in maze.
   */
  public int getNumberOfNodes() {
    return listOfNodeRelation.size();
  }

  /**
   * set the start and end point.
   */
  public void setStartAndEndPoint() {
    ArrayList<Pair<Integer, Integer>> listOfCave = new ArrayList<>();
    for (Pair key : getCaveTypeList().keySet()) {
      if (getCaveTypeList().get(key) == CaveType.Caves) {
        listOfCave.add(key);
      }
    }
    RandomFactory randomMethod = new RandomFactory();
    int distance = 0;
    while (distance < 5) {
      RandomMethod randomMethod1 = randomMethod.getRandom("true", listOfCave.size(), 0);
      int randomNumber1 = randomMethod1.getRandomNumber();
      Pair<Integer, Integer> start = listOfCave.get(randomNumber1);
      int xForStart = start.fst;
      int yForStart = start.snd;
      Pair<Integer, Integer> removeNumber = listOfCave.remove(randomNumber1);
      RandomMethod randomMethod2 = randomMethod.getRandom("true", listOfCave.size(), 0);
      int randomNumber2 = randomMethod2.getRandomNumber();
      Pair<Integer, Integer> end = listOfCave.get(randomNumber2);
      int xForEnd = end.fst;
      int yForEnd = end.snd;
      distance = Math.abs(xForStart - xForEnd) + Math.abs(yForStart - yForEnd);
      if (distance < 5) {
        listOfCave.add(removeNumber);
      }
      startingPoint = start;
      endPoint = end;
    }

  }

  /**
   * just for test.
   */
  public void fakeSetStartAndEndPoint() {
    Pair<Integer, Integer> start = new Pair<>(0, 2);
    Pair<Integer, Integer> end = new Pair<>(4, 4);
    startingPoint = start;
    endPoint = end;
  }


  /**
   * this is just for test.
   *
   * @param treasurePercent the treasurePercent.
   */
  public void fakeAssignTreasure(double treasurePercent) {
    if (treasurePercent < 0.0 || treasurePercent > 100.0) {
      throw new IllegalArgumentException("Treasure percent invalid.");
    }
    ArrayList<Pair<Integer, Integer>> listOfCave = new ArrayList<>();
    for (Pair key : caveTypeList.keySet()) {
      if (caveTypeList.get(key) == CaveType.Caves) {
        listOfCave.add(key);
      }
    }
    int treasureCave;
    if (treasurePercent == 0) {
      treasureCave = 0;
    } else if ((int) (listOfCave.size() * treasurePercent) / 100 == 0) {
      treasureCave = 1;
    } else {
      treasureCave = (int) (listOfCave.size() * treasurePercent) / 100;
    }
    for (int i = 0; i < treasureCave; i++) {
      RandomMethod randomTrue = RandomFactory.getRandom("true", listOfCave.size(), 0);
      int random = randomTrue.getRandomNumber();
      ArrayList<String> treasure = itemsInCave.get(listOfCave.get(random));
      treasure.add("Diamond");
      itemsInCave.put(listOfCave.get(random), treasure);
      listOfCave.remove(random);
    }
  }

  /**
   * assign treasure in the cave.
   *
   * @param treasurePercent the user's input of the treasure percent.
   */
  public void assignTreasure(double treasurePercent) {
    if (treasurePercent < 0.0 || treasurePercent > 100.0) {
      throw new IllegalArgumentException("Treasure percent invalid.");
    }
    ArrayList<Pair<Integer, Integer>> listOfCave = new ArrayList<>();
    for (Pair key : caveTypeList.keySet()) {
      if (caveTypeList.get(key) == CaveType.Caves) {
        listOfCave.add(key);
      }
    }
    int treasureCave;
    if (treasurePercent == 0) {
      treasureCave = 0;
    } else if ((int) (listOfCave.size() * treasurePercent) / 100 == 0) {
      treasureCave = 1;
    } else {
      treasureCave = (int) (listOfCave.size() * treasurePercent) / 100;
    }
    for (int i = 0; i < treasureCave; i++) {
      RandomMethod randomTrue = RandomFactory.getRandom("true", listOfCave.size(), 0);
      int random = randomTrue.getRandomNumber();
      ArrayList<String> treasure = itemsInCave.get(listOfCave.get(random));
      int caveType = (random + 3) % 3;
      switch (caveType) {
        case 0:
          treasure.add("Diamond");
          break;
        case 1:
          treasure.add("Diamond");
          treasure.add("Rubies");
          break;
        case 2:
          treasure.add("Diamond");
          treasure.add("Sapphires");
          break;
        default:
          break;
      }
      itemsInCave.put(listOfCave.get(random), treasure);
      listOfCave.remove(random);
    }
  }

  /**
   * assign Arrow in the maze.
   *
   * @param treasurePercent the percent of the treasure in the caves.
   */
  public void assignArrow(double treasurePercent) {
    if (treasurePercent < 0.0 || treasurePercent > 100.0) {
      throw new IllegalArgumentException("Arrow percent invalid.");
    }
    ArrayList<Pair<Integer, Integer>> listOfAllCave = new ArrayList<>(caveTypeList.keySet());
    int arrowCave;
    if (treasurePercent == 0) {
      arrowCave = 0;
    } else if ((int) (listOfAllCave.size() * treasurePercent) / 100 == 0) {
      arrowCave = 1;
    } else {
      arrowCave = (int) (listOfAllCave.size() * treasurePercent) / 100;
    }
    for (int i = 0; i < arrowCave; i++) {
      RandomMethod randomTrue = RandomFactory.getRandom("true", listOfAllCave.size(), 0);
      int random = randomTrue.getRandomNumber();
      ArrayList<String> items = itemsInCave.get(listOfAllCave.get(random));
      items.add("Arrow");
      itemsInCave.put(listOfAllCave.get(random), items);
      listOfAllCave.remove(random);
    }
  }


  private int getNumberOfCaves() {
    int result = 0;
    for (Pair key : caveTypeList.keySet()) {
      if (caveTypeList.get(key) != CaveType.Tunnels) {
        result++;
      }
    }
    return result;
  }

  private ArrayList<Pair<Integer, Integer>> getListOfCave() {
    ArrayList<Pair<Integer, Integer>> listOfCave = new ArrayList<>();
    for (Pair key : caveTypeList.keySet()) {
      if (caveTypeList.get(key) == CaveType.Caves) {
        listOfCave.add(key);
      }
    }
    return listOfCave;
  }

  /**
   * assign the Otyughs in the caves.
   *
   * @param numberOfOtyugh the number of the Otyugh in the maze.
   */
  public void assignOtyughs(int numberOfOtyugh) {
    int maxOtyughNumber = getNumberOfCaves() - 1;
    if (numberOfOtyugh > maxOtyughNumber) {
      throw new IllegalArgumentException("the number of Otyugh is Invalid,"
          + "It should be smaller than sum of caves - 1");
    }
    ArrayList<Pair<Integer, Integer>> listOfCave = getListOfCave();
    listOfCave.remove(startingPoint);
    for (int i = 0; i < numberOfOtyugh; i++) {
      RandomMethod randomTrue = RandomFactory.getRandom("true", listOfCave.size(), 0);
      int random = randomTrue.getRandomNumber();
      ArrayList<String> items = itemsInCave.get(listOfCave.get(random));
      items.add("Otyugh");
      itemsInCave.put(listOfCave.get(random), items);
      listOfCave.remove(random);
    }
  }

}

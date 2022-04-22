package model.dungeon;

import model.kruskalalgorithm.BasicMaze;
import model.kruskalalgorithm.CaveType;
import model.kruskalalgorithm.Direction;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

/**
 * Interface for Maze.
 * This interface represents a maze . It provides methods to access properties like
 * number of rows, number of columns, number of remaining walls, whether maze is wrapped or not.
 * It also provides methods to set  and get starting point, goal point in the maze,
 * get possible moves from current location and also make a specific move in a direction.
 */
public interface Dungeon {

  /**
   * Gets number of rows in the maze.
   * @return number of rows in the maze
   */
  int getNumberOfRows();

  /**
   * Gets number of columns in the maze.
   * @return number of columns in the maze
   */
  int getNumberOfColumns();

  /**
   * Tells if the maze is a wrapped maze or not.
   * @return if maze is wrapped or not
   */
  boolean getIsWrapped();

  /**
   * Gets number of remaining walls in the maze.
   * @return number of remaining walls in the maze.
   */
  int getInterconnectivity();

  /**
   * get the treasurePercent that the player input.
   * @return treasurePercent.
   */
  double getTreasurePercentage();

  /**
   * get the basic maze.
   * @return Basic Maze.
   */
  BasicMaze getDungeon();

  /**
   * Get the current moves of the player.
   * @return the possible moves of the player from current location
   */
  EnumSet<Direction> getPossibleMoves();

  /**
   * Get the current moves of the player.
   * @return the possible moves of the player from current location
   */
  EnumSet<Direction> getPossibleMoves(int row, int column);

  /**
   * Get the  player of the game.
   * @return the player object of the maze
   */
  Player getPlayer();

  /**
   * Get the  node type for each node in the maze.
   * @return a map of the node type for each node( gold/thief/blank)
   */
  Map<Pair<Integer, Integer>, CaveType> getCaveTypeList();

  /**
   * this will getItemInCave class.
   * @return the Map of connection between location and Items.
   */
  Map<Pair<Integer, Integer>, ArrayList<String>> getItemsInCave();

  /**
   * this will return the adjacency list of the dungeon.
   * @return adjacency list.
   */
  Map<Pair<Integer, Integer>, ArrayList<Pair<Integer, Integer>>> listOfNodeRelation();

  /**
   * Get the starting point of the maze.
   * @return starting point of the maze
   */
  Pair<Integer, Integer> getStartingPoint();

  /**
   * Get the end location of the maze.
   * @return end location of the maze
   */
  Pair<Integer, Integer> getEndPoint();

  /**
   * Make a particular specified move for the player.
   * @throws IllegalArgumentException if cannot make move in given direction
   */
  void makeMove(Direction direction) throws IllegalArgumentException;

  /**
   * according to the point1 and point 2. we can get the direction from point1 to point2.
   * @param point1 point1.
   * @param point2 point2.
   * @return the direction.
   */
  Direction getDirection(Pair<Integer, Integer> point1, Pair<Integer, Integer> point2);

  /**
   * get the treasure list of the player.
   * @return the list of treasures from player.
   */
  List<String> getTreasureList();

  /**
   * this is the pickup function. which will let user pick up things.
   * @param string the items thar user want to pick up.
   */
  void pickUp(String string);

  /**
   * this is detected Otyugh function.
   * @return if it has Otyugh nearby.
   */
  int detectOtyugh();

  /**
   * this is the shoot function.
   * @param caveNumber the caveNumber.
   * @param direction the direction.
   * @return the result of shoot.
   */
  int shoot(String caveNumber,String direction);

  /**
   * identify if it is game over.
   * @return the result that if it is game over.
   */
  boolean isGameOver();

  /**
   * get the Number of Otyugh.
   * @return the number of Otyugh.
   */
  int getNumberOfOtyugh();
}


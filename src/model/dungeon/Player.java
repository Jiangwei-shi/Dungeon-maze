package model.dungeon;

import java.util.List;

/**
 * this is the player interface. It can set current location and get location.
 * in the treasure list. It has the treasure list that the player collect.
 */
public interface Player {

  /**
   * Sets the current location of the player.
   */
  void setCurrentLocation(Pair<Integer, Integer> location);

  /**
   * Gets the current location of the player.
   *
   * @return the current location of the player in the maze.
   */
  Pair<Integer, Integer> getCurrentLocation();

  /**
   * Gets the current treasure list of the player.
   *
   * @return the current treasure list of the player.
   */
  List<String> getTreasureList();

  /**
   * this is the pickup function.
   * @param string items that want to pick up.
   */
  void pickUp(String string);

  /**
   * loose items.
   */
  void loose();
}

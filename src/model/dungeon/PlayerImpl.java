package model.dungeon;

import model.dungeon.Player;

import java.util.ArrayList;
import java.util.List;


/**
 * This is playerImpl class in the maze.
 */
public class PlayerImpl implements Player {

  private Pair<Integer, Integer> currentLocation;
  private final List<String> treasure;

  /**
   * this is the constructor.
   */
  public PlayerImpl() {
    treasure = new ArrayList<>();
    treasure.add("Arrow");
    treasure.add("Arrow");
    treasure.add("Arrow");
  }

  /**
   * Sets the current location of the player.
   */
  @Override
  public void setCurrentLocation(Pair<Integer, Integer> location) {
    currentLocation = location;
  }

  /**
   * Gets the current location of the player.
   *
   * @return the current location of the player in the maze.
   */
  @Override
  public Pair<Integer, Integer> getCurrentLocation() {
    return currentLocation;
  }

  /**
   * Gets the current treasure list of the player.
   *
   * @return the current treasure list of the player.
   */
  @Override
  public List<String> getTreasureList() {
    return treasure;
  }

  @Override
  public void pickUp(String string) {
    treasure.add(string);
  }

  @Override
  public void loose() {
    treasure.remove("Arrow");
  }

}


package model.dungeon;

import model.dungeon.AbstractDungeon;

/**
 * this is the wrapDungeon class. Just pass the parameter into AbstractDungeon.
 */
public class WrapDungeon extends AbstractDungeon {
  /**
   * Constructor for Wrapping Room Maze.
   *
   * @param numberOfRows       number of rows.
   * @param numberOfColumns    number of columns.
   * @param interconnectivity  number of walls that should be leave after use kruskal.
   * @param treasurePercentage percentage of treasure.
   * @throws IllegalArgumentException if any parameter is negative
   */
  public WrapDungeon(int numberOfRows, int numberOfColumns, int interconnectivity,
                     double treasurePercentage, int numberOfOtyugh) {
    super(numberOfRows, numberOfColumns, true, interconnectivity, treasurePercentage,
        numberOfOtyugh);
  }
}

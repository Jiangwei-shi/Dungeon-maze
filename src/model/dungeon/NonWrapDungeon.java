package model.dungeon;


/**
 * non wrap dungeon class.
 */
public class NonWrapDungeon extends AbstractDungeon {
  /**
   * Constructor for Room Maze.
   * @param numberOfRows      : number of rows in the maze
   * @param numberOfColumns   : number of columns in the maze
   * @param interconnectivity : number of walls that should be left after maze creation
   * @throws IllegalArgumentException if any parameter is negative
   */
  public NonWrapDungeon(int numberOfRows, int numberOfColumns, int interconnectivity,
                        double treasurePercentage,int numberOfOtyugh) {
    super(numberOfRows, numberOfColumns, false, interconnectivity,
        treasurePercentage,numberOfOtyugh);
  }
}

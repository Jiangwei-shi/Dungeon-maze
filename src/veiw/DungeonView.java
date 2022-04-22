package veiw;

import controller.DungeonController;
import model.kruskalalgorithm.Direction;

import java.io.IOException;

/**
 * this is the view part in the MVC design. Which accept the result from controller and update view.
 */
public interface DungeonView {

  /**
   * this adds a keyListener in the cave.
   *
   * @param controller model.
   */
  void addKeyListener(DungeonController controller);

  /**
   * this make the view visible.
   */
  void makeVisible();

  /**
   * this method will update the view after move.
   *
   * @param direction the direction that moved.
   */
  void refreshAfterMove(Direction direction);

  /**
   * this method will update the view after pick up.
   */
  void refreshAfterPickUp();

  /**
   * this method will update the view after soot.
   * @param result the shoot result. if 1 means shoot one time. if 2 means kill a monster. if 0
   *               means didn't shoot anything.
   */
  void refreshAfterShoot(int result);

  /**
   * this method will update the view after user press restart game.
   * @throws IOException when some file can't read. then will throw IOException.
   */
  void reStartGame() throws IOException;

  /**
   * after user set a new game. it will update as a new view. those parameters work for model.
   * @param numberOfRows the row of dungeon.
   * @param numberOfColumns the column of dungeon.
   * @param isWrapped is wrap or not.
   * @param interconnectivity the interconnectivity of the dungeon.
   * @param treasurePercentage the treasure percentage.
   * @param numberOfOtyugh the number of otyugh.
   * @throws IOException when some file can't read. then will throw IOException.
   */
  void startNewGame(int numberOfRows, int numberOfColumns, boolean isWrapped,
                    int interconnectivity, double treasurePercentage, int numberOfOtyugh)
      throws IOException;
}

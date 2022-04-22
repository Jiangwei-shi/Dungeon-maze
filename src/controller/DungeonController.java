package controller;

import model.dungeon.Dungeon;
import model.kruskalalgorithm.Direction;

import java.io.IOException;

/**
 * this is the Controller part in MVC. which get the user's input and pass it into model.
 * then pass the result back to view.
 */
public interface DungeonController {
  /**
   * this is the play game function. used to start game.
   * @param dungeon pass the model.
   * @throws IOException while is no Dungeon. report IOException.
   */
  void playGame(Dungeon dungeon) throws IOException;

  /**
   * this is the function which get the user' input.
   * @param direction get the direction when moved.
   */
  void handleKeyInput(Direction direction);

  /**
   * when user pick up treasure. pass this into model.
   * @param treasureName the name of treasure.
   */
  void handlePickUpTreasure(String treasureName);

  /**
   * when user shoot. pass this into model.
   * @param caveNumber the cave number.
   * @param direction the direction that user moved.
   */
  void handleShooting(String caveNumber, String direction);

  /**
   * when user press restart. call this function.
   * @throws IOException when getting the photo failed. report IOException.
   */
  void reStartGame() throws IOException;

  /**
   * when user press start new game. call this function.
   * @param model the model part.
   * @throws IOException when getting the photo part failed. report IOException.
   */
  void startNewGame(Dungeon model) throws IOException;
}

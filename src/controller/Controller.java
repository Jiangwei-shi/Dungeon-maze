package controller;

import model.dungeon.Dungeon;

import java.io.IOException;


/**
 * hold the input and output. then print it.
 */
public interface Controller {
  /**
   * play game.
   *
   * @param dungeon dungeon.
   * @throws IOException when the dundeon is not a dungeon.
   */
  void playGame(Dungeon dungeon) throws IOException;
}

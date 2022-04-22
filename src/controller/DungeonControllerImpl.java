package controller;

import model.dungeon.AbstractDungeon;
import model.dungeon.Dungeon;
import model.kruskalalgorithm.Direction;
import veiw.DungeonView;
import veiw.DungeonViewImpl;

import java.io.IOException;

/**
 * this is the DungeonController implement class.
 */
public class DungeonControllerImpl implements DungeonController {
  private DungeonView view;
  private Dungeon model;

  /**
   * this is the constructor of DungeonControllerImpl.
   * @param model this is the model.
   * @param view this is the view.
   */
  public DungeonControllerImpl(Dungeon model, DungeonView view) {
    this.view = view;
    this.model = model;
  }

  @Override
  public void playGame(Dungeon dungeon) throws IOException {
    this.view.makeVisible();
    this.view.addKeyListener(this);
  }

  @Override
  public void handleKeyInput(Direction direction) {
    if (model.getPossibleMoves().contains(direction)) {
      model.makeMove(direction);
      view.refreshAfterMove(direction);
    }
  }

  @Override
  public void handlePickUpTreasure(String treasureName) {
    model.pickUp(treasureName);
    view.refreshAfterPickUp();
  }

  @Override
  public void handleShooting(String caveNumber, String direction) {
    int result = model.shoot(caveNumber, direction);
    view.refreshAfterShoot(result);
  }

  @Override
  public void reStartGame() throws IOException {
    int numberOfRow = model.getNumberOfRows();
    int numberOfColumn = model.getNumberOfColumns();
    Boolean isWrapped = model.getIsWrapped();
    int numberOfOtyugh = model.getNumberOfOtyugh();
    Double treasurePercentage = model.getTreasurePercentage();
    int interconnectivity = model.getInterconnectivity();
    Dungeon reStartDungeon =
        new AbstractDungeon(numberOfRow, numberOfColumn, isWrapped, interconnectivity,
            treasurePercentage, numberOfOtyugh);
    DungeonView reStartDungeonView = new DungeonViewImpl(reStartDungeon);
    DungeonController restartController =
        new DungeonControllerImpl(reStartDungeon, reStartDungeonView);
    restartController.playGame(reStartDungeon);
  }

  @Override
  public void startNewGame(Dungeon model) throws IOException {
    DungeonView startNewDungeonView = new DungeonViewImpl(model);
    DungeonController restartController =
        new DungeonControllerImpl(model, startNewDungeonView);
    restartController.playGame(model);
  }


}

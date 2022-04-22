package veiw;

import model.dungeon.Dungeon;
import model.kruskalalgorithm.Direction;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * this is the mouseListener class in the view.
 */
public class MouseListener extends MouseAdapter {
  private final Dungeon dungeon;
  private final DungeonScrollPane dungeonScrollPane;

  /**
   * this is the constructor of MouseListener class.
   * @param dungeon the model.
   * @param dungeonScrollPane the dungeon scroll pane.
   */
  public MouseListener(Dungeon dungeon, DungeonScrollPane dungeonScrollPane) {
    this.dungeon = dungeon;
    this.dungeonScrollPane = dungeonScrollPane;
  }


  @Override
  public void mouseClicked(MouseEvent e) {
    super.mouseClicked(e);
    if (e.getPoint().x > 50 && e.getPoint().x < 100 && e.getPoint().y < 50) {
      if (dungeon.getPossibleMoves().contains(Direction.NORTH)) {
        dungeon.makeMove(Direction.NORTH);
        dungeonScrollPane.updateAfterMove(Direction.NORTH);
      }
    } else if (e.getPoint().x < 50 && e.getPoint().y > 50 && e.getPoint().y < 100) {
      if (dungeon.getPossibleMoves().contains(Direction.WEST)) {
        dungeon.makeMove(Direction.WEST);
        dungeonScrollPane.updateAfterMove(Direction.WEST);
      }
    } else if (e.getPoint().x > 100 && e.getPoint().y > 50 && e.getPoint().y < 100) {
      if (dungeon.getPossibleMoves().contains(Direction.EAST)) {
        dungeon.makeMove(Direction.EAST);
        dungeonScrollPane.updateAfterMove(Direction.EAST);
      }
    } else if (e.getPoint().x > 50 && e.getPoint().x < 100 && e.getPoint().y > 100) {
      if (dungeon.getPossibleMoves().contains(Direction.SOUTH)) {
        dungeon.makeMove(Direction.SOUTH);
        dungeonScrollPane.updateAfterMove(Direction.SOUTH);
      }
    }
  }
}

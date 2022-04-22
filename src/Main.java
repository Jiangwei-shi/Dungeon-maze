import controller.ControllerImpl;
import controller.DungeonController;
import controller.DungeonControllerImpl;
import model.dungeon.AbstractDungeon;
import model.dungeon.Dungeon;
import model.dungeon.NonWrapDungeon;
import model.dungeon.WrapDungeon;
import veiw.DungeonView;
import veiw.DungeonViewImpl;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * this is the main class.
 */
public class Main {
  /**
   * main class.
   *
   * @param args arguments.
   * @throws IOException while the file cannot read.
   */
  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
      Dungeon model = new AbstractDungeon(5, 5, false, 0, 50, 3);
      DungeonView view = new DungeonViewImpl(model);
      DungeonController controller = new DungeonControllerImpl(model, view);
      controller.playGame(model);
    } else {
      Readable input = new InputStreamReader(System.in);
      Appendable output = System.out;
      if (Boolean.parseBoolean(args[2])) {
        new ControllerImpl(input, output).playGame(
            new WrapDungeon(Integer.parseInt(args[0]), Integer.parseInt(args[1]),
                Integer.parseInt(args[3]), Double.parseDouble(args[4]), Integer.parseInt(args[5])));
      } else {
        new ControllerImpl(input, output).playGame(
            new NonWrapDungeon(Integer.parseInt(args[0]), Integer.parseInt(args[1]),
                Integer.parseInt(args[3]), Double.parseDouble(args[4]), Integer.parseInt(args[5])));
      }
    }

  }
}

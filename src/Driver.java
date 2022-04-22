import controller.ControllerImpl;
import model.dungeon.NonWrapDungeon;
import model.dungeon.WrapDungeon;

import java.io.IOException;
import java.io.InputStreamReader;


/**
 * this is the drive class.
 * enter rows of the dungeon, columns of the dungeon,interconnectivity number of the dungeon,
 * wrap or not,percentage of the caves that have treasure. And will create a dungeon. then the
 * player will find a way from start to end.
 */
public class Driver {

  /**
   * main class.
   *
   * @param args 0 present 5,5,3,false,100
   *             1 present 5,5,3,true,100
   */
  public static void main(String[] args) throws IllegalArgumentException, IOException {
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

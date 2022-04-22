package controller;

import model.dungeon.Dungeon;
import model.kruskalalgorithm.Direction;
import model.random.RandomMethod;
import model.random.TrueRandomMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * this is the controllerImpl class. will hold user input and model.
 * then run the playGame() function.
 */
public class ControllerImpl implements Controller {
  private final Appendable out;
  private final Scanner scan;

  /**
   * this is the constructor.
   *
   * @param in  the user input.
   * @param out the output.
   */
  public ControllerImpl(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    this.scan = new Scanner(in);
  }

  @Override
  public void playGame(Dungeon dungeon) throws IOException {
    try {
      if (dungeon == null) {
        throw new IllegalArgumentException("dungeon is not a Dungeon ");
      }
      while (!dungeon.isGameOver()) {
        ArrayList<String> items =
            dungeon.getItemsInCave().get(dungeon.getPlayer().getCurrentLocation());

        if (items.contains("Otyugh")) {
          out.append("Chomp, chomp, chomp, you are eaten by an Otyugh!" + "\n");
          out.append("Better luck next time");
          return;
        }

        if (items.contains("halfLifeOtyugh")) {
          RandomMethod random = new TrueRandomMethod("True", 1, 0);
          if (random.getRandomNumber() == 0) {
            out.append("the Otyugh find you but didn't want to fight with you\n");
          } else {
            out.append("Chomp, chomp, chomp, you are eaten by an Otyugh!" + "\n");
            out.append("Better luck next time");
            return;
          }
        }

        if (dungeon.detectOtyugh() == 1) {
          out.append("You smell something terrible nearby" + "\n");
        }
        if (dungeon.detectOtyugh() == 2) {
          out.append("You smell something very very terrible nearby" + "\n");
        }
        out.append("You are in a ").append(
                String.valueOf(dungeon.getCaveTypeList()
                    .get(dungeon.getPlayer().getCurrentLocation())))
            .append("\n");
        if (items.size() > 1) {
          out.append("You find ");
          for (String string : items) {
            if (!string.equals("empty")) {
              out.append(string).append(" ");
            }
          }
          out.append("here" + "\n");
        }
        out.append("Doors lead to the ");
        for (Direction direction : dungeon.getPossibleMoves()) {
          out.append(direction.toString()).append(" ");
        }
        out.append("\n").append("\n").append("Move, Pickup, or Shoot (M-P-S)?");
        String element = scan.next();
        if (element.equals("M")) {
          out.append("where to?");
          switch (scan.next()) {
            case "NORTH":
              dungeon.makeMove(Direction.NORTH);
              out.append("\n");
              break;
            case "EAST":
              dungeon.makeMove(Direction.EAST);
              out.append("\n");
              break;
            case "SOUTH":
              dungeon.makeMove(Direction.SOUTH);
              out.append("\n");
              break;
            case "WEST":
              dungeon.makeMove(Direction.WEST);
              out.append("\n");
              break;
            default:
              out.append("you can only select from EAST SOUTH WEST NORTH");
              break;
          }
        } else if (element.equals("P")) {
          out.append("what?");
          switch (scan.next()) {
            case "Diamond":
              dungeon.pickUp("Diamond");
              out.append("You pick up the Diamond" + "\n\n");
              break;
            case "Rubies":
              dungeon.pickUp("Rubies");
              out.append("You pick up the Rubies" + "\n\n");
              break;
            case "Sapphires":
              dungeon.pickUp("Sapphires");
              out.append("You pick up the Sapphires" + "\n\n");
              break;
            case "Arrow":
              dungeon.pickUp("Arrow");
              out.append("You pick up the Arrow" + "\n\n");
              break;
            default:
              out.append("you can only select from Diamond Rubies Sapphires Arrow");
              break;
          }
        } else if (element.equals("S")) {
          out.append("No. of caves (1-2)?");
          String numberOfCave = scan.next();
          out.append("Where to?");
          String whereTo = scan.next();
          int result = dungeon.shoot(numberOfCave, whereTo);
          if (result == 0) {
            out.append("You shoot an arrow into the darkness \n\n");
          } else if (result == 1) {
            out.append("You hear a great howl in the distance\n\n");
          } else if (result == 2) {
            out.append("you kill an Otyugh\n\n");
          } else {
            out.append("you don't have arrow, you can pick some arrow\n\n");
          }
        } else {
          out.append("You Can only select M or P or S" + "\ngame end");
          break;
        }
      }
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
    if (dungeon.isGameOver()) {
      out.append("You arrive the end point. end game");
    }
  }
}

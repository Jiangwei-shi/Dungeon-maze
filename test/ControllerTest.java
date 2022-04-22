
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.io.StringReader;
import controller.Controller;
import controller.ControllerImpl;
import model.dungeon.AbstractDungeon;
import model.dungeon.Dungeon;
import model.dungeon.NonWrapDungeon;
import org.junit.Before;
import org.junit.Test;

/**
 * this is the junit test for controllerTest.
 */
public class ControllerTest {
  private Dungeon model;
  private Controller controller;

  @Before
  public void setUp() throws Exception {
    model = new AbstractDungeon();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRow() {
    StringReader input = new StringReader("");
    Appendable out = new StringBuilder();
    controller = new ControllerImpl(input, out);
    model = new NonWrapDungeon(4, 5, 0, 50, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCol() {
    StringReader input = new StringReader("");
    Appendable out = new StringBuilder();
    controller = new ControllerImpl(input, out);
    model = new NonWrapDungeon(5, 4, 0, 50, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInter() {
    StringReader input = new StringReader("");
    Appendable out = new StringBuilder();
    controller = new ControllerImpl(input, out);
    model = new NonWrapDungeon(5, 5, 50, 50, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTreasurePer() {
    StringReader input = new StringReader("");
    Appendable out = new StringBuilder();
    controller = new ControllerImpl(input, out);
    model = new NonWrapDungeon(5, 5, 50, 1000, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidArrowPer() {
    StringReader input = new StringReader("");
    Appendable out = new StringBuilder();
    controller = new ControllerImpl(input, out);
    model = new NonWrapDungeon(5, 5, 50, 1000, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMonsterNum() {
    StringReader input = new StringReader("");
    Appendable out = new StringBuilder();
    controller = new ControllerImpl(input, out);
    model = new NonWrapDungeon(5, 5, 50, 50, 50);
  }

  @Test
  public void testMoveToAllCells() throws IOException {
    StringReader input = new StringReader("M SOUTH M NORTH M EAST M WEST M EAST M EAST M SOUTH "
        + "M SOUTH M SOUTH M SOUTH");
    Appendable out = new StringBuilder();
    controller = new ControllerImpl(input, out);
    model = new AbstractDungeon("nonOtyugh");
    controller.playGame(model);
    String expect = "You are in a Caves\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?where to?\n" +
        "You are in a Tunnels\n" +
        "Doors lead to the NORTH SOUTH \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?where to?\n" +
        "You are in a Caves\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?where to?\n" +
        "You are in a Caves\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?where to?\n" +
        "You are in a Caves\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?where to?\n" +
        "You are in a Caves\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?where to?\n" +
        "You are in a Tunnels\n" +
        "Doors lead to the SOUTH WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?where to?\n" +
        "You are in a Tunnels\n" +
        "Doors lead to the NORTH SOUTH \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?where to?\n" +
        "You are in a Tunnels\n" +
        "Doors lead to the NORTH SOUTH \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?where to?\n" +
        "You are in a Tunnels\n" +
        "Doors lead to the NORTH SOUTH \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?where to?\n" +
        "You arrive the end point. end game";
    assertEquals(expect, out.toString());
  }

  @Test
  public void testValidDirection() throws IOException {
    StringReader input = new StringReader("M SOUTH END");
    Appendable out = new StringBuilder();
    controller = new ControllerImpl(input, out);
    controller.playGame(model);
    String expect = "You smell something very very terrible nearby\n" +
        "You are in a Caves\n" +
        "You find Diamond Arrow here\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?where to?\n" +
        "You smell something very very terrible nearby\n" +
        "You are in a Tunnels\n" +
        "You find Arrow here\n" +
        "Doors lead to the NORTH SOUTH \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?You Can only select M or P or S\n" +
        "game end";
    assertEquals(expect, out.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDirection() throws IOException {
    StringReader input = new StringReader("M NORTH END");
    Appendable out = new StringBuilder();
    controller = new ControllerImpl(input, out);
    controller.playGame(model);
  }

  @Test
  public void testInvalidPickUp() throws IOException {
    StringReader input = new StringReader("P shit END");
    Appendable out = new StringBuilder();
    controller = new ControllerImpl(input, out);
    controller.playGame(model);
    String expect = "You smell something very very terrible nearby\n" +
        "You are in a Caves\n" +
        "You find Diamond Arrow here\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?what?you can only select from Diamond Rubies Sapphires " +
        "ArrowYou smell something very very terrible nearby\n" +
        "You are in a Caves\n" +
        "You find Diamond Arrow here\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?You Can only select M or P or S\n" +
        "game end";
    assertEquals(expect, out.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShoot() throws IOException {
    StringReader input = new StringReader("S 20 NORTH END");
    Appendable out = new StringBuilder();
    controller = new ControllerImpl(input, out);
    controller.playGame(model);
  }

  @Test
  public void testMorePungentSmell() throws IOException {
    StringReader input = new StringReader("end");
    Appendable out = new StringBuilder();
    controller = new ControllerImpl(input, out);
    controller.playGame(model);
    String expect = "You smell something very very terrible nearby\n"
        + "You are in a Caves\n"
        + "You find Diamond Arrow here\n"
        + "Doors lead to the SOUTH EAST WEST \n"
        + "\n"
        + "Move, Pickup, or Shoot (M-P-S)?You Can only select M or P or S\n"
        + "game end";
    assertEquals(expect, out.toString());
  }

  @Test
  public void testLessPungentSmell() throws IOException {
    StringReader input = new StringReader("M SOUTH M SOUTH end");
    Appendable out = new StringBuilder();
    controller = new ControllerImpl(input, out);
    controller.playGame(model);
    String expect = "You smell something very very terrible nearby\n" +
        "You are in a Caves\n" +
        "You find Diamond Arrow here\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?where to?\n" +
        "You smell something very very terrible nearby\n" +
        "You are in a Tunnels\n" +
        "You find Arrow here\n" +
        "Doors lead to the NORTH SOUTH \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?where to?\n" +
        "You smell something terrible nearby\n" +
        "You are in a Tunnels\n" +
        "You find Arrow here\n" +
        "Doors lead to the NORTH SOUTH \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?You Can only select M or P or S\n" +
        "game end";
    assertEquals(expect, out.toString());
  }

  @Test
  public void testPickUpTreasure() throws IOException {
    StringReader input = new StringReader("P Diamond end");
    Appendable out = new StringBuilder();
    controller = new ControllerImpl(input, out);
    controller.playGame(model);
    String expect = "You smell something very very terrible nearby\n" +
        "You are in a Caves\n" +
        "You find Diamond Arrow here\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?what?You pick up the Diamond\n" +
        "\n" +
        "You smell something very very terrible nearby\n" +
        "You are in a Caves\n" +
        "You find Arrow here\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?You Can only select M or P or S\n" +
        "game end";
    assertEquals(expect, out.toString());
  }

  @Test
  public void testPickUpArrow() throws IOException {
    StringReader input = new StringReader("P Arrow end");
    Appendable out = new StringBuilder();
    controller = new ControllerImpl(input, out);
    controller.playGame(model);
    String expect = "You smell something very very terrible nearby\n" +
        "You are in a Caves\n" +
        "You find Diamond Arrow here\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?what?You pick up the Arrow\n" +
        "\n" +
        "You smell something very very terrible nearby\n" +
        "You are in a Caves\n" +
        "You find Diamond here\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?You Can only select M or P or S\n" +
        "game end";
    assertEquals(expect, out.toString());
  }

  @Test
  public void testKillOtyugh() throws IOException {
    StringReader input = new StringReader("S 1 EAST S 1 EAST end");
    Appendable out = new StringBuilder();
    controller = new ControllerImpl(input, out);
    controller.playGame(model);
    String expect = "You smell something very very terrible nearby\n" +
        "You are in a Caves\n" +
        "You find Diamond Arrow here\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?No. of caves (1-2)?" +
        "Where to?You hear a great howl in the distance\n" +
        "\n" +
        "You smell something very very terrible nearby\n" +
        "You are in a Caves\n" +
        "You find Diamond Arrow here\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?No. of caves (1-2)?Where to?you kill an Otyugh\n" +
        "\n" +
        "You smell something very very terrible nearby\n" +
        "You are in a Caves\n" +
        "You find Diamond Arrow here\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?You Can only select M or P or S\n" +
        "game end";
    assertEquals(expect, out.toString());
  }

  @Test
  public void testBeKilledByOtyugh() throws IOException {
    StringReader input = new StringReader("M EAST");
    Appendable out = new StringBuilder();
    controller = new ControllerImpl(input, out);
    controller.playGame(model);
    String expect = "You smell something very very terrible nearby\n" +
        "You are in a Caves\n" +
        "You find Diamond Arrow here\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?where to?\n" +
        "Chomp, chomp, chomp, you are eaten by an Otyugh!\n" +
        "Better luck next time";
    assertEquals(expect, out.toString());
  }

  @Test
  public void testEscapingFromOtyugh() throws IOException {
    StringReader input = new StringReader("S 1 EAST M EAST end");
    Appendable out = new StringBuilder();
    controller = new ControllerImpl(input, out);
    controller.playGame(model);
    String expect = "You smell something very very terrible nearby\n" +
        "You are in a Caves\n" +
        "You find Diamond Arrow here\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?No. of caves (1-2)?" +
        "Where to?You hear a great howl in the distance\n" +
        "\n" +
        "You smell something very very terrible nearby\n" +
        "You are in a Caves\n" +
        "You find Diamond Arrow here\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?where to?\n" +
        "the Otyugh find you but didn't want to fight with you\n" +
        "You smell something very very terrible nearby\n" +
        "You are in a Caves\n" +
        "You find Diamond Arrow halfLifeOtyugh here\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?You Can only select M or P or S\n" +
        "game end";
    assertEquals(expect, out.toString());
  }

  @Test
  public void testShootOtyugh() throws IOException {
    StringReader input = new StringReader("S 1 EAST end");
    Appendable out = new StringBuilder();
    controller = new ControllerImpl(input, out);
    controller.playGame(model);
    String expect = "You smell something very very terrible nearby\n" +
        "You are in a Caves\n" +
        "You find Diamond Arrow here\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?No. of caves (1-2)?Where to?" +
        "You hear a great howl in the distance\n" +
        "\n" +
        "You smell something very very terrible nearby\n" +
        "You are in a Caves\n" +
        "You find Diamond Arrow here\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?You Can only select M or P or S\n" +
        "game end";
    assertEquals(expect, out.toString());
  }

  @Test
  public void testShootThroughTunnel() throws IOException {
    StringReader input = new StringReader("S 1 SOUTH end");
    Appendable out = new StringBuilder();
    controller = new ControllerImpl(input, out);
    controller.playGame(model);
    String expect = "You smell something very very terrible nearby\n" +
        "You are in a Caves\n" +
        "You find Diamond Arrow here\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?No. of caves (1-2)?" +
        "Where to?You hear a great howl in the distance\n" +
        "\n" +
        "You smell something very very terrible nearby\n" +
        "You are in a Caves\n" +
        "You find Diamond Arrow here\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?You Can only select M or P or S\n" +
        "game end";
    assertEquals(expect, out.toString());
  }

  @Test
  public void testShootThroughCave() throws IOException {
    StringReader input = new StringReader("S 2 EAST end");
    Appendable out = new StringBuilder();
    controller = new ControllerImpl(input, out);
    controller.playGame(model);
    String expect = "You smell something very very terrible nearby\n" +
        "You are in a Caves\n" +
        "You find Diamond Arrow here\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?No. of caves (1-2)?" +
        "Where to?You shoot an arrow into the darkness \n" +
        "\n" +
        "You smell something very very terrible nearby\n" +
        "You are in a Caves\n" +
        "You find Diamond Arrow here\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?You Can only select M or P or S\n" +
        "game end";
    assertEquals(expect, out.toString());
  }

  @Test
  public void testShootOnTheWall() throws IOException {
    StringReader input = new StringReader("S 2 NORTH end");
    Appendable out = new StringBuilder();
    controller = new ControllerImpl(input, out);
    controller.playGame(model);
    String expect = "You smell something very very terrible nearby\n" +
        "You are in a Caves\n" +
        "You find Diamond Arrow here\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?No. of caves (1-2)?" +
        "Where to?You shoot an arrow into the darkness \n" +
        "\n" +
        "You smell something very very terrible nearby\n" +
        "You are in a Caves\n" +
        "You find Diamond Arrow here\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?You Can only select M or P or S\n" +
        "game end";
    assertEquals(expect, out.toString());
  }

  @Test
  public void testWin() throws IOException {
    StringReader input = new StringReader("M EAST M EAST M SOUTH M SOUTH M SOUTH M SOUTH");
    Appendable out = new StringBuilder();
    controller = new ControllerImpl(input, out);
    model = new AbstractDungeon("nonOtyugh");
    controller.playGame(model);
    String expect = "You are in a Caves\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?where to?\n" +
        "You are in a Caves\n" +
        "Doors lead to the SOUTH EAST WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?where to?\n" +
        "You are in a Tunnels\n" +
        "Doors lead to the SOUTH WEST \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?where to?\n" +
        "You are in a Tunnels\n" +
        "Doors lead to the NORTH SOUTH \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?where to?\n" +
        "You are in a Tunnels\n" +
        "Doors lead to the NORTH SOUTH \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?where to?\n" +
        "You are in a Tunnels\n" +
        "Doors lead to the NORTH SOUTH \n" +
        "\n" +
        "Move, Pickup, or Shoot (M-P-S)?where to?\n" +
        "You arrive the end point. end game";
    assertEquals(expect, out.toString());
  }
}
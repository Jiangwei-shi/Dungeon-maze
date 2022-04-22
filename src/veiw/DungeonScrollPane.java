package veiw;

import model.dungeon.Dungeon;
import model.dungeon.Pair;
import model.kruskalalgorithm.Direction;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * this is the scrollPane part. it can make the screen roll.
 */
public class DungeonScrollPane extends JPanel {
  private final Dungeon dungeon;
  private BufferedImage updateImage;
  private int row;
  private int column;
  private JLabel[] label;
  private MouseListener listener1;
  private final DungeonView view;


  /**
   * this is the constructor of DungeonScrollPane class.
   *
   * @param model the model.
   * @param view  the view.
   * @throws IllegalStateException if model is null or view is null. throw Exception.
   */
  public DungeonScrollPane(Dungeon model, DungeonView view) throws IllegalStateException {
    this.view = view;
    this.dungeon = model;
    if (model == null || view == null) {
      throw new IllegalStateException("model cannot be null.");
    }
    GridLayout gridLayout = new GridLayout(model.getNumberOfRows(), model.getNumberOfColumns());
    this.setLayout(gridLayout);
    addLabel(model.getNumberOfRows(), model.getNumberOfColumns());
  }

  private void addLabel(int row, int column) {
    this.row = row;
    this.column = column;
    label = new JLabel[row * column];
    File file = new File("data/blank.png");
    try {
      BufferedImage image = ImageIO.read(file);
      updateImage = resizeImage(image, 150, 150);
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (int i = 0; i < row * column; i++) {
      label[i] = new JLabel();
      label[i].setIcon(new ImageIcon(updateImage));
      this.add(label[i]);
    }
    int startRow = dungeon.getPlayer().getCurrentLocation().fst;
    int startColumn = dungeon.getPlayer().getCurrentLocation().snd;
    int aim = column * startRow + startColumn;
    label[aim].setIcon(new ImageIcon(setStart()));
    listener1 = new MouseListener(dungeon, this);
    label[aim].addMouseListener(listener1);
  }

  /**
   * update the current location image.
   *
   * @param rows    the row of current location.
   * @param columns the column of current location.
   */
  public void updateCurrentLocation(int rows, int columns) {
    BufferedImage updateImage = getAimImage(rows, columns);
    int aim = column * rows + columns;
    try {
      File file3 = new File("data/player.png");
      BufferedImage image = ImageIO.read(file3);
      BufferedImage image2 = resizeImage(image, 40, 40);
      updateImage = overlay2(updateImage, image2, 55);

      if (dungeon.detectOtyugh() == 1) {
        File smallSmell = new File("data/stench01.png");
        BufferedImage smallSmell1 = ImageIO.read(smallSmell);
        smallSmell1 = resizeImage(smallSmell1, 60, 60);
        updateImage = overlay2(updateImage, smallSmell1, 70);
      } else if (dungeon.detectOtyugh() == 2) {
        File smallSmell = new File("data/stench02.png");
        BufferedImage smallSmell1 = ImageIO.read(smallSmell);
        smallSmell1 = resizeImage(smallSmell1, 60, 60);
        updateImage = overlay2(updateImage, smallSmell1, 70);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    label[aim].setIcon(new ImageIcon(updateImage));
  }


  /**
   * update the image after Move.
   *
   * @param direction the direction that user move.
   */
  public void updateAfterMove(Direction direction) {
    int previousRow;
    int previousColumn;
    int startRow = dungeon.getPlayer().getCurrentLocation().fst;
    int startColumn = dungeon.getPlayer().getCurrentLocation().snd;
    if (!dungeon.getIsWrapped()) {
      if (direction == Direction.NORTH) {
        previousRow = startRow + 1;
        previousColumn = startColumn;
      } else if (direction == Direction.WEST) {
        previousRow = startRow;
        previousColumn = startColumn + 1;
      } else if (direction == Direction.EAST) {
        previousRow = startRow;
        previousColumn = startColumn - 1;
      } else {
        previousRow = startRow - 1;
        previousColumn = startColumn;
      }
    } else {
      if (direction == Direction.NORTH) {
        if (startRow == row - 1) {
          previousRow = 0;
        } else {
          previousRow = startRow + 1;
        }
        previousColumn = startColumn;
      } else if (direction == Direction.WEST) {
        if (startColumn == column - 1) {
          previousColumn = 0;
        } else {
          previousColumn = startColumn + 1;
        }
        previousRow = startRow;
      } else if (direction == Direction.EAST) {
        if (startColumn == 0) {
          previousColumn = column - 1;
        } else {
          previousColumn = startColumn - 1;
        }
        previousRow = startRow;
      } else {
        if (startRow == 0) {
          previousRow = row - 1;
        } else {
          previousRow = startRow - 1;
        }
        previousColumn = startColumn;
      }
    }

    int aim = column * previousRow + previousColumn;
    BufferedImage image = getAimImage(previousRow, previousColumn);
    label[aim].setIcon(new ImageIcon(image));
    label[aim].removeMouseListener(listener1);
    int aim2 = column * startRow + startColumn;
    BufferedImage image2 = getAimImage(startRow, startColumn);

    try {
      if (dungeon.detectOtyugh() == 1) {
        File smallSmell = new File("data/stench01.png");
        BufferedImage smallSmell1 = ImageIO.read(smallSmell);
        smallSmell1 = resizeImage(smallSmell1, 60, 60);
        image2 = overlay2(image2, smallSmell1, 70);
      } else if (dungeon.detectOtyugh() == 2) {
        File smallSmell = new File("data/stench02.png");
        BufferedImage smallSmell1 = ImageIO.read(smallSmell);
        smallSmell1 = resizeImage(smallSmell1, 60, 60);
        image2 = overlay2(image2, smallSmell1, 70);
      }
      File file3 = new File("data/player.png");
      BufferedImage image3 = ImageIO.read(file3);
      image3 = resizeImage(image3, 40, 40);
      image2 = overlay2(image2, image3, 55);
    } catch (IOException e) {
      e.printStackTrace();
    }
    label[aim2].setIcon(new ImageIcon(image2));
    if (dungeon.isGameOver()) {
      JDialog dlg = new GameOverDialog(view);
      dlg.setTitle("Game over");
      dlg.setModal(true);
      dlg.setSize(400, 400);
      dlg.setVisible(true);
    }
    if (dungeon.getItemsInCave().get(dungeon.getPlayer().getCurrentLocation()).contains("Otyugh")
        || dungeon.getItemsInCave().get(dungeon.getPlayer().getCurrentLocation())
        .contains("halfLifeOtyugh")) {
      JDialog dlg = new KilledDialog(view);
      dlg.setTitle("Game over");
      dlg.setModal(true);
      dlg.setSize(400, 400);
      dlg.setVisible(true);
    }

    label[aim2].addMouseListener(listener1);
  }


  private BufferedImage setStart() {
    BufferedImage updated = null;
    try {
      BufferedImage image2 = getAimImage(dungeon.getPlayer().getCurrentLocation().fst,
          dungeon.getPlayer().getCurrentLocation().snd);
      BufferedImage updateImage2 = resizeImage(image2, 150, 150);
      BufferedImage update = overlay2(updateImage, updateImage2, 5);
      File file3 = new File("data/player.png");
      BufferedImage image3 = ImageIO.read(file3);
      BufferedImage resizeImage2 = resizeImage(image3, 40, 40);
      updated = overlay2(update, resizeImage2, 55);

    } catch (IOException e) {
      e.printStackTrace();
    }
    return updated;
  }

  /**
   * get the aim Image.
   *
   * @param aimRow    the row of you want to know.
   * @param aimColumn the column of you want to know.
   * @return the Buffered Image.
   */
  public BufferedImage getAimImage(int aimRow, int aimColumn) {
    Pair pair = new Pair(aimRow, aimColumn);
    BufferedImage result = null;
    try {
      File file = new File(getPhoto(dungeon.getPossibleMoves(aimRow, aimColumn)));
      result = ImageIO.read(file);
      if (dungeon.getItemsInCave().get(pair).contains("Otyugh")
          || dungeon.getItemsInCave().get(pair).contains("halfLifeOtyugh")) {
        File file2 = new File("data/otyugh.png");
        BufferedImage image2 = ImageIO.read(file2);
        image2 = resizeImage(image2, 10, 10);
        result = overlay2(result, image2, 0);
      }
      if (dungeon.getItemsInCave().get(pair).contains("Rubies")) {
        File file2 = new File("data/ruby.png");
        BufferedImage image2 = ImageIO.read(file2);
        image2 = resizeImage(image2, 10, 10);
        result = overlay2(result, image2, 10);
      }
      if (dungeon.getItemsInCave().get(pair).contains("Diamond")) {
        File file2 = new File("data/diamond.png");
        BufferedImage image2 = ImageIO.read(file2);
        image2 = resizeImage(image2, 10, 10);
        result = overlay2(result, image2, 20);
      }
      if (dungeon.getItemsInCave().get(pair).contains("Sapphires")) {
        File file2 = new File("data/emerald.png");
        BufferedImage image2 = ImageIO.read(file2);
        image2 = resizeImage(image2, 10, 10);
        result = overlay2(result, image2, 30);
      }
      if (dungeon.getItemsInCave().get(pair).contains("Arrow")) {
        File file2 = new File("data/arrow-white.png");
        BufferedImage image2 = ImageIO.read(file2);
        image2 = resizeImage(image2, 30, 10);
        result = overlay2(result, image2, 40);
      }
      result = resizeImage(result, 150, 150);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * this code is given by professor.
   *
   * @param starting the basic image.
   * @param end      the Image you want to add.
   * @param offset   the offset possible.
   * @return the Image.
   * @throws IOException while the file cannot read. throw IPException.
   */
  private BufferedImage overlay2(BufferedImage starting, BufferedImage end, int offset) throws
      IOException {
    int w = Math.max(starting.getWidth(), end.getWidth());
    int h = Math.max(starting.getHeight(), end.getHeight());
    BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics g = combined.getGraphics();
    g.drawImage(starting, 0, 0, null);
    g.drawImage(end, offset, offset, null);
    return combined;
  }

  /**
   * refer https://www.baeldung.com/java-resize-image, can resize the Image.
   *
   * @param originalImage the originalImage.
   * @param targetWidth   the width you want to get.
   * @param targetHeight  the Height you want to get.
   * @return the Buffered Image.
   * @throws IOException when could not get Image.
   */
  public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth,
                                          int targetHeight) throws IOException {
    Image resultingImage =
        originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
    BufferedImage outputImage =
        new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
    return outputImage;
  }

  /**
   * get the possible move Image path.
   *
   * @param set the set of possible move.
   * @return the path of Image.
   */
  public String getPhoto(EnumSet set) {
    if (set.contains(Direction.EAST) && set.contains(Direction.WEST)
        && set.contains(Direction.SOUTH)
        && set.contains(Direction.NORTH)) {
      return "data/color-cells/NSEW.png";
    } else if (set.contains(Direction.EAST) && set.contains(Direction.WEST)
        && set.contains(Direction.SOUTH)) {
      return "data/color-cells/SEW.png";
    } else if (set.contains(Direction.EAST) && set.contains(Direction.WEST)
        && set.contains(Direction.NORTH)) {
      return "data/color-cells/NEW.png";
    } else if (set.contains(Direction.EAST) && set.contains(Direction.SOUTH)
        && set.contains(Direction.NORTH)) {
      return "data/color-cells/NSE.png";
    } else if (set.contains(Direction.WEST) && set.contains(Direction.SOUTH)
        && set.contains(Direction.NORTH)) {
      return "data/color-cells/NSW.png";
    } else if (set.contains(Direction.EAST) && set.contains(Direction.WEST)) {
      return "data/color-cells/EW.png";
    } else if (set.contains(Direction.EAST) && set.contains(Direction.NORTH)) {
      return "data/color-cells/NE.png";
    } else if (set.contains(Direction.SOUTH) && set.contains(Direction.NORTH)) {
      return "data/color-cells/NS.png";
    } else if (set.contains(Direction.WEST) && set.contains(Direction.NORTH)) {
      return "data/color-cells/NW.png";
    } else if (set.contains(Direction.SOUTH) && set.contains(Direction.EAST)) {
      return "data/color-cells/SE.png";
    } else if (set.contains(Direction.SOUTH) && set.contains(Direction.WEST)) {
      return "data/color-cells/SW.png";
    } else if (set.contains(Direction.EAST)) {
      return "data/color-cells/E.png";
    } else if (set.contains(Direction.NORTH)) {
      return "data/color-cells/N.png";
    } else if (set.contains(Direction.SOUTH)) {
      return "data/color-cells/S.png";
    } else if (set.contains(Direction.WEST)) {
      return "data/color-cells/W.png";
    } else {
      return null;
    }
  }

}

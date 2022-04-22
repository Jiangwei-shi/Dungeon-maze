package veiw;

import controller.DungeonController;
import model.dungeon.Dungeon;
import model.dungeon.Pair;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * this is the frame while player want to pick up.
 */
public class PickUpFrame extends JFrame {

  private final JPanel subPanel;
  private final Dungeon dungeon;
  private JCheckBox checkBox1;
  private JCheckBox checkBox2;
  private JCheckBox checkBox3;
  private JCheckBox checkBox4;
  private final DungeonController controller;

  /**
   * this is the constructor class of PickUpFrame.
   * @param dungeon the model.
   * @param controller the controller.
   * @throws IOException while the file cannot read.
   */
  public PickUpFrame(Dungeon dungeon, DungeonController controller)
      throws IOException {
    super("Pick up");
    this.controller = controller;
    this.dungeon = dungeon;
    subPanel = new JPanel();
    add(subPanel);
    subPanel.setLayout(null);
    addPickLabel();

  }

  private void addPickLabel() throws IOException {
    JLabel label1 = new JLabel();
    File file1 = new File("data/diamond.png");
    BufferedImage image1 = ImageIO.read(file1);
    label1.setIcon(new ImageIcon(image1));
    label1.setBounds(100, 70, 200, 100);
    subPanel.add(label1);

    JLabel label2 = new JLabel();
    File file2 = new File("data/emerald.png");
    BufferedImage image2 = ImageIO.read(file2);
    label2.setIcon(new ImageIcon(image2));
    label2.setBounds(100, 170, 200, 100);
    subPanel.add(label2);

    JLabel label3 = new JLabel();
    File file3 = new File("data/ruby.png");
    BufferedImage image3 = ImageIO.read(file3);
    label3.setIcon(new ImageIcon(image3));
    label3.setBounds(100, 270, 200, 100);
    subPanel.add(label3);

    JLabel label4 = new JLabel();
    File file4 = new File("data/arrow-black.png");
    BufferedImage image4 = ImageIO.read(file4);
    label4.setIcon(new ImageIcon(image4));
    label4.setBounds(100, 370, 200, 100);
    subPanel.add(label4);

    JLabel label5 = new JLabel("Pick up");
    label5.setBounds(350, 30, 50, 50);
    subPanel.add(label5);


    checkBox1 = new JCheckBox();
    checkBox1.setBounds(350, 100, 30, 30);
    subPanel.add(checkBox1);

    checkBox2 = new JCheckBox();
    checkBox2.setBounds(350, 200, 30, 30);
    subPanel.add(checkBox2);

    checkBox3 = new JCheckBox();
    checkBox3.setBounds(350, 300, 30, 30);
    subPanel.add(checkBox3);

    checkBox4 = new JCheckBox();
    checkBox4.setBounds(350, 400, 30, 30);
    subPanel.add(checkBox4);

    JButton button1 = new JButton("Pick up");
    button1.setBounds(150, 500, 100, 20);
    button1.addActionListener(new Pickup());
    subPanel.add(button1);

    JButton button2 = new JButton("Exit");
    button2.setBounds(250, 500, 100, 20);
    button2.addActionListener(new Button2Action());
    subPanel.add(button2);
  }

  private class Button2Action implements ActionListener {

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      dispose();
    }
  }

  private class Pickup implements ActionListener {
    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      Pair playerLocation = dungeon.getPlayer().getCurrentLocation();
      if (checkBox1.isSelected()) {
        if (dungeon.getItemsInCave().get(playerLocation).contains("Diamond")) {
          controller.handlePickUpTreasure("Diamond");
        }
      }
      if (checkBox2.isSelected()) {
        if (dungeon.getItemsInCave().get(playerLocation).contains("Sapphires")) {
          controller.handlePickUpTreasure("Sapphires");
        }
      }
      if (checkBox3.isSelected()) {
        if (dungeon.getItemsInCave().get(playerLocation).contains("Rubies")) {
          controller.handlePickUpTreasure("Rubies");
        }
      }
      if (checkBox4.isSelected()) {
        if (dungeon.getItemsInCave().get(playerLocation).contains("Arrow")) {
          controller.handlePickUpTreasure("Arrow");
        }
      }
      dispose();
    }
  }
}

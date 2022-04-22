package veiw;

import model.dungeon.Dungeon;

import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * this is the below panel in the JFrame.
 */
public class DungeonPanel extends JPanel {
  private final Dungeon model;
  private JLabel label5;
  private JLabel label6;
  private JLabel label7;
  private JLabel label8;
  private JLabel label10;

  /**
   * this is the constructor.
   * @param model the model.
   * @throws IllegalStateException when the model is null. throw exception.
   */
  public DungeonPanel(Dungeon model) throws IllegalStateException {
    if (model == null) {
      throw new IllegalStateException("model cannot be null.");
    }
    this.model = model;
    this.setLayout(null);
    setIcon();
  }

  private void setIcon() {
    JLabel label1 = new JLabel();
    label1.setIcon(new ImageIcon("data/diamond.png"));
    label1.setBounds(50, 20, 50, 50);
    this.add(label1);

    JLabel label2 = new JLabel();
    label2.setIcon(new ImageIcon("data/ruby.png"));
    label2.setBounds(150, 20, 50, 50);
    this.add(label2);

    JLabel label3 = new JLabel();
    label3.setIcon(new ImageIcon("data/emerald.png"));
    label3.setBounds(250, 20, 50, 50);
    this.add(label3);

    JLabel label4 = new JLabel();
    label4.setIcon(new ImageIcon("data/arrow-black.png"));
    label4.setBounds(350, 20, 50, 50);
    this.add(label4);

    label5 = new JLabel("0");
    label5.setBounds(60, 80, 50, 50);
    this.add(label5);

    label6 = new JLabel("0");
    label6.setBounds(160, 80, 50, 50);
    this.add(label6);

    label7 = new JLabel("0");
    label7.setBounds(260, 80, 50, 50);
    this.add(label7);

    label8 = new JLabel("3");
    label8.setBounds(365, 80, 50, 50);
    this.add(label8);

    JLabel label9 = new JLabel("shoot result");
    label9.setBounds(450, 20, 100, 50);
    this.add(label9);

    label10 = new JLabel();
    label10.setBounds(450, 80, 500, 50);
    this.add(label10);
  }

  /**
   * update the label Image or data.
   */
  public void updateLabel() {
    int diamondNumber = 0;
    int rubyNumber = 0;
    int emeraldNumber = 0;
    int arrowNumber = 0;
    for (int i = 0; i < model.getPlayer().getTreasureList().size(); i++) {
      if (Objects.equals(model.getPlayer().getTreasureList().get(i), "Diamond")) {
        diamondNumber++;
      }
      if (Objects.equals(model.getPlayer().getTreasureList().get(i), "Rubies")) {
        rubyNumber++;
      }
      if (Objects.equals(model.getPlayer().getTreasureList().get(i), "Sapphires")) {
        emeraldNumber++;
      }
      if (Objects.equals(model.getPlayer().getTreasureList().get(i), "Arrow")) {
        arrowNumber++;
      }
    }
    label5.setText(String.valueOf(diamondNumber));
    label6.setText(String.valueOf(rubyNumber));
    label7.setText(String.valueOf(emeraldNumber));
    label8.setText(String.valueOf(arrowNumber));
  }

  /**
   * update the information in panel.
   * @param result the result after shoot.
   */
  public void updateInformation(int result) {
    if (result == 0) {
      label10.setText("You shoot an arrow into the darkness \n\n");
    } else if (result == 1) {
      label10.setText("You hear a great howl in the distance\n\n");
    } else if (result == 2) {
      label10.setText("you kill an Otyugh\n\n");
    } else {
      label10.setText("you don't have arrow, you can pick some arrow\n\n");
    }
  }
}

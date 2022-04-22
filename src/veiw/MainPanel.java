package veiw;

import model.dungeon.Dungeon;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * this is the main panel. which hold the DungeonPane and the DungeonPanel.
 * put those two JPanel in to a panel.
 */
public class MainPanel extends JPanel {
  private DungeonScrollPane panel;
  private DungeonPanel panel2;

  /**
   * this is the constructor of mainPanel class.
   * @param model the model.
   * @param view the view.
   */
  public MainPanel(Dungeon model, DungeonView view) {

    panel = new DungeonScrollPane(model, view);
    JPanel panel3 = new JPanel();
    panel3.add(panel);
    JScrollPane panel1 = new JScrollPane(panel3);
    panel1.setPreferredSize(new Dimension(600, 400));
    panel2 = new DungeonPanel(model);
    panel2.setPreferredSize(new Dimension(300, 200));
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.add(panel1);
    this.add(panel2);

  }

  public DungeonScrollPane getDungeonScrollPane() {
    return panel;
  }

  public DungeonPanel getBelowPanel() {
    return panel2;
  }
}

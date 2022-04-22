package veiw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * this is the dialog part when you arrived the end and game is over..
 */
public class GameOverDialog extends JDialog {
  private final JPanel panel;
  private final DungeonView view;

  /**
   * this is the constructor of GameOverDialog class.
   * @param view the view.
   */
  public GameOverDialog(DungeonView view) {
    this.view = view;
    panel = new JPanel();
    this.setContentPane(panel);
    panel.setLayout(null);
    addLabel();
  }

  private void addLabel() {
    JLabel label = new JLabel("you arrived the end point.Game is over");
    label.setBounds(50, 20, 400, 200);
    panel.add(label);

    JButton button = new JButton("restart game");
    button.setBounds(150, 200, 200, 50);
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          view.reStartGame();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
        dispose();
      }
    });
    panel.add(button);
  }
}

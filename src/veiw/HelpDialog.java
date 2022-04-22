package veiw;

import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * this is the dialog when user press help.
 */
public class HelpDialog extends JDialog {

  /**
   * this is the constructor of helpDialog.
   * @param owner the view.
   */
  public HelpDialog(Window owner) {
    super(owner);
    JPanel panel = new JPanel();
    this.setContentPane(panel);
    JLabel label1 = new JLabel("please read the read the readme file carefully.");
    panel.add(label1);
  }
}

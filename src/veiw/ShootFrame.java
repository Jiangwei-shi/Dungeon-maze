package veiw;

import controller.DungeonController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * this is the shoot frame while user want to shoot.
 */
public class ShootFrame extends JFrame {
  private final JPanel subPanel;
  private final DungeonController controller;
  private int caveNumber = 1;
  private String direction = "EAST";
  private JLabel label3;
  private JLabel label4;

  /**
   * this is the constructor of ShootFrame.
   * @param controller controller.
   */
  public ShootFrame(DungeonController controller) {
    super("Project 5");
    this.controller = controller;
    subPanel = new JPanel();
    add(subPanel);
    subPanel.setLayout(null);
    addShootLabel();
    setFocusable(true);
    addKeyListener(new KeyAccept());
  }

  private class KeyAccept extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
      super.keyPressed(e);
      super.keyPressed(e);
      int keyCode = e.getKeyCode();
      switch (keyCode) {
        case KeyEvent.VK_UP:
          direction = "NORTH";
          label4.setText("NORTH");
          break;
        case KeyEvent.VK_DOWN:
          direction = "SOUTH";
          label4.setText("SOUTH");
          break;
        case KeyEvent.VK_LEFT:
          direction = "WEST";
          label4.setText("WEST");
          break;
        case KeyEvent.VK_RIGHT:
          direction = "EAST";
          label4.setText("EAST");
          break;
        case KeyEvent.VK_1:
          label3.setText("1");
          caveNumber = 1;
          break;
        case KeyEvent.VK_2:
          caveNumber = 2;
          label3.setText("2");
          break;
        default:
          //do nothing.
      }
    }
  }


  private void addShootLabel() {
    JLabel label1 = new JLabel("the caveNumber you want to shoot. (press 1 or 2 change)");
    label1.setBounds(50, 50, 500, 100);
    subPanel.add(label1);

    JLabel label2 = new JLabel("the direction you want to shoot (press direction key to change)");
    label2.setBounds(50, 150, 500, 100);
    subPanel.add(label2);

    label3 = new JLabel();
    label3.setText("1");
    label3.setBounds(50, 100, 200, 100);
    subPanel.add(label3);

    label4 = new JLabel();
    label4.setText("EAST");
    label4.setBounds(50, 200, 200, 100);
    subPanel.add(label4);

    JButton shootButton = new JButton("Shoot");
    shootButton.setBounds(200, 270, 100, 30);
    shootButton.addActionListener(new ShootButtonListener());
    subPanel.add(shootButton);

    JButton exitButton = new JButton("exit");
    exitButton.setBounds(400, 270, 100, 30);
    exitButton.addActionListener(new ExitButtonListener());
    subPanel.add(exitButton);
  }

  private class ExitButtonListener implements ActionListener {

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

  private class ShootButtonListener implements ActionListener {

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      String caveAmount = String.valueOf(caveNumber);
      controller.handleShooting(caveAmount, direction);
      dispose();
    }
  }
}

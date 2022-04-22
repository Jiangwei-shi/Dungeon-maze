package veiw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 * this is the dialog when user want to start a new game.
 */
public class SettingDialog extends JDialog {

  private JPanel subPanel;
  private JSpinner spinner1;
  private JSpinner spinner2;
  private JSpinner spinner4;
  private JSpinner spinner5;
  private JSpinner spinner6;
  private JCheckBox checkBox;
  private final DungeonView view;

  /**
   * this is the constructor of SettingDialog class.
   * @param view the view.
   */
  public SettingDialog(DungeonView view) {
    this.view = view;
    subPanel = new JPanel();
    this.setContentPane(subPanel);
    //use myself Layout.
    subPanel.setLayout(null);
    addSettingLabel();
  }

  private void addSettingLabel() {
    JLabel label1 = new JLabel("rows");
    subPanel.add(label1);
    label1.setBounds(40, 20, 200, 50);

    JLabel label2 = new JLabel("columns");
    label2.setBounds(40, 60, 200, 50);
    subPanel.add(label2);

    JLabel label3 = new JLabel("isWrap(true or false)");
    label3.setBounds(40, 100, 200, 50);
    subPanel.add(label3);

    JLabel label4 = new JLabel("interconnectivity");
    label4.setBounds(40, 140, 200, 50);
    subPanel.add(label4);

    JLabel label5 = new JLabel("treasurePercentage");
    label5.setBounds(40, 180, 200, 50);
    subPanel.add(label5);

    JLabel label6 = new JLabel("numberOfOtyugh");
    label6.setBounds(40, 220, 200, 50);
    subPanel.add(label6);

    SpinnerModel rowModel = new SpinnerNumberModel(5, // initial value
        5, // min
        10, // max
        1);// step
    spinner1 = new JSpinner(rowModel);
    spinner1.setBounds(240, 35, 50, 25);
    subPanel.add(spinner1);

    SpinnerModel columnModel = new SpinnerNumberModel(5, // initial value
        5, // min
        10, // max
        1);// step
    spinner2 = new JSpinner(columnModel);
    spinner2.setBounds(240, 75, 50, 25);
    subPanel.add(spinner2);

    checkBox = new JCheckBox();
    checkBox.setSelected(false);
    checkBox.setBounds(240, 115, 50, 25);
    subPanel.add(checkBox);

    SpinnerModel interconnectivityModel = new SpinnerNumberModel(0, // initial value
        0, // min
        10, // max
        1);// step
    spinner4 = new JSpinner(interconnectivityModel);
    spinner4.setBounds(240, 155, 50, 25);
    subPanel.add(spinner4);

    SpinnerModel rateModel = new SpinnerNumberModel(0, // initial value
        0, // min
        100, // max
        10);// step
    spinner5 = new JSpinner(rateModel);
    spinner5.setBounds(240, 195, 50, 25);
    subPanel.add(spinner5);

    SpinnerModel countModel = new SpinnerNumberModel(0, // initial value
        0, // min
        5, // max
        1);// step
    spinner6 = new JSpinner(countModel);
    spinner6.setBounds(240, 235, 50, 25);
    subPanel.add(spinner6);

    JButton button1 = new JButton("Apply");
    button1.setBounds(100, 275, 75, 35);
    button1.addActionListener(new Button1Action());
    subPanel.add(button1);


    JButton button2 = new JButton("Cancel");
    button2.setBounds(175, 275, 75, 35);
    button2.addActionListener(new Button2Action());
    subPanel.add(button2);
  }

  private class Button1Action implements ActionListener {

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      int row = (int) spinner1.getValue();
      int column = (int) spinner2.getValue();
      boolean isWrap = checkBox.isSelected();
      int connectivity = (int) spinner4.getValue();
      int treasurePercent = (int) spinner5.getValue();
      Double treasurePercents = (double) treasurePercent;
      int numberOfMonster = (int) spinner6.getValue();
      try {
        view.startNewGame(row, column, isWrap, connectivity, treasurePercents, numberOfMonster);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      dispose();
    }
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
}

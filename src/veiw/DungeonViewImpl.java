package veiw;

import controller.DungeonController;
import controller.DungeonControllerImpl;
import model.dungeon.AbstractDungeon;
import model.dungeon.Dungeon;
import model.kruskalalgorithm.Direction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;


/**
 * this is the view class implement class. Which accept the result from controller and update view.
 */
public class DungeonViewImpl extends JFrame implements DungeonView {

  private MainPanel mainPanel;
  private DungeonScrollPane scrollPane;
  private Dungeon model;

  /**
   * this is the constructor of DungeonViewImpl class.
   *
   * @param dungeon the model.
   * @throws IllegalStateException while the parameter is null.
   * @throws IOException           while the file cannot read.
   */
  public DungeonViewImpl(Dungeon dungeon) throws IllegalStateException, IOException {
    super("Project 5");
    if (dungeon == null) {
      throw new IllegalArgumentException("Model can not be null");
    }
    this.setSize(950, 1300);
    model = dungeon;
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    mainPanel = new MainPanel(dungeon, this);
    add(mainPanel);
    scrollPane = mainPanel.getDungeonScrollPane();
    addJMenu();
  }

  private void addJMenu() {
    JMenuBar jmb = new JMenuBar();
    this.setJMenuBar(jmb);

    JMenu game = new JMenu("Game");
    jmb.add(game);

    JMenu help = new JMenu("Help");
    jmb.add(help);

    JMenuItem start_new_game = new JMenuItem("start New game");
    start_new_game.addActionListener(new SettingActionListener());
    game.add(start_new_game);

    JMenuItem restart_game = new JMenuItem("restart game");
    restart_game.addActionListener(new RestartActionListener());
    game.add(restart_game);

    JMenuItem exit = new JMenuItem("Exit");
    exit.addActionListener(new ExitActionListener());
    game.add(exit);


    JMenuItem helpItem = new JMenuItem("Help");
    helpItem.addActionListener(new HelpActionListener());
    help.add(helpItem);
  }


  private class RestartActionListener implements ActionListener {

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        reStartGame();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  private class SettingActionListener implements ActionListener {

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      setSetting();
    }
  }

  private void setSetting() {
    SettingDialog dlg = new SettingDialog(this);
    dlg.setTitle("Setting");
    dlg.setModal(true);
    dlg.setSize(400, 400);
    dlg.setVisible(true);
  }

  private class ExitActionListener implements ActionListener {

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


  private class HelpActionListener implements ActionListener {

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      helpSetting();
    }
  }

  private void helpSetting() {
    HelpDialog dlg = new HelpDialog(this);
    dlg.setTitle("Help");
    dlg.setModal(true);
    dlg.setSize(200, 100);
    dlg.setVisible(true);
  }

  @Override
  public void addKeyListener(DungeonController controller) throws IllegalStateException {

    if (controller == null) {
      throw new IllegalStateException("the controller can not be null");
    }

    //create the MouseAdapter
    KeyAdapter keyListener = new KeyListener() {
      @SuppressWarnings("checkstyle:FallThrough")
      @Override
      public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        int keyCode = e.getKeyCode();
        switch (keyCode) {
          case KeyEvent.VK_UP:
            controller.handleKeyInput(Direction.NORTH);
            break;
          case KeyEvent.VK_DOWN:
            controller.handleKeyInput(Direction.SOUTH);
            break;
          case KeyEvent.VK_LEFT:
            controller.handleKeyInput(Direction.WEST);
            break;
          case KeyEvent.VK_RIGHT:
            controller.handleKeyInput(Direction.EAST);
            break;
          case KeyEvent.VK_P:
            try {
              JFrame frame = new PickUpFrame(model, controller);
              frame.setSize(600, 600);
              frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
              frame.setVisible(true);
            } catch (IOException ex) {
              ex.printStackTrace();
            }
            break;
          case KeyEvent.VK_S:
            JFrame shootFrame = new ShootFrame(controller);
            shootFrame.setSize(600, 400);
            shootFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            shootFrame.setVisible(true);
            break;
          default:
            //Do nothing.
        }
      }
    };
    addKeyListener(keyListener);

  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void refreshAfterMove(Direction direction) {
    scrollPane.updateAfterMove(direction);
  }

  @Override
  public void refreshAfterPickUp() {
    int playerRow = model.getPlayer().getCurrentLocation().fst;
    int playerColumn = model.getPlayer().getCurrentLocation().snd;
    scrollPane.updateCurrentLocation(playerRow, playerColumn);
    mainPanel.getBelowPanel().updateLabel();
  }

  @Override
  public void refreshAfterShoot(int result) {
    int playerRow = model.getPlayer().getCurrentLocation().fst;
    int playerColumn = model.getPlayer().getCurrentLocation().snd;
    scrollPane.updateCurrentLocation(playerRow, playerColumn);
    mainPanel.getBelowPanel().updateLabel();
    mainPanel.getBelowPanel().updateInformation(result);
  }

  @Override
  public void reStartGame() throws IOException {
    DungeonController controller = new DungeonControllerImpl(model, this);
    controller.reStartGame();
    dispose();
  }

  @Override
  public void startNewGame(int numberOfRows, int numberOfColumns, boolean isWrapped,
                           int interconnectivity, double treasurePercentage, int numberOfOtyugh)
      throws IOException {
    Dungeon newDungeon = new AbstractDungeon(numberOfRows, numberOfColumns, isWrapped,
        interconnectivity, treasurePercentage, numberOfOtyugh);
    DungeonController controller = new DungeonControllerImpl(model, this);
    controller.startNewGame(newDungeon);
    dispose();
  }


}

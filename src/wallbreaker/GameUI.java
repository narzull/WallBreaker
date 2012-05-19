package wallbreaker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author nexus_21
 */
public class GameUI extends JFrame implements Observer, KeyListener {

    /**
     * JPanel pause
     */
    private PauseUI pauseUI;
    /**
     * JPanel menu
     */
    private MenuUI menuUI;
    /**
     * JPanel level
     */
    private LevelUI levelUI;
    /**
     * linekd Game
     */
    private Game game;

    /**
     * constructor of game user interface
     * @param game 
     */
    public GameUI(Game game) {
        this.game = game;

        this.game.addObs(this);

        this.setSize(800, 600);
        this.setBackground(Color.black);

        BorderLayout centerLayout = new BorderLayout(0, 20);
        this.setLayout(centerLayout);

        /* create level UI */
        this.levelUI = new LevelUI(this.game.getLevel());
        this.add(this.levelUI);

        /* create menu UI */
        this.menuUI = new MenuUI();

        /* create pause UI */
        this.pauseUI = new PauseUI();

        /* add components in layout */
        this.add("Center", this.levelUI);
        this.add("East", this.menuUI);


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        addKeyListener(this);

        this.setResizable(false);
        
    }

    @Override
    public void update() {

        System.out.println("Game updated");

        //if(this.levelUI)
        this.getContentPane().remove(this.levelUI);
        this.getContentPane().validate();
        this.levelUI = new LevelUI(this.game.getLevel());
        this.getContentPane().add("Center", this.levelUI);

        /* updater le menu */
        /*
        this.menuUI.updateDisplays(Game.getInstance().getScore()
        , Game.getInstance().getLives()
        , Game.getInstance().getIdLvl()
        );
         * 
         */
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {

        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {

            if (!this.game.isOnPause()) {
                /* show PauseUI */
                this.getContentPane().remove(this.levelUI);
                this.getContentPane().add("Center", this.pauseUI);
                this.getContentPane().validate();
                System.out.println(" Pause On ");
                this.game.setOnPause(true);
            } else {
                this.getContentPane().remove(this.pauseUI);
                this.pauseUI = new PauseUI();
                this.getContentPane().add("Center", this.levelUI);
                this.getContentPane().validate();
                System.out.println(" Pause Off ");
                this.game.setOnPause(false);
            }


        } else if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
            Game.getInstance().getLevel().validateWord(this.pauseUI.getWord());
        } else if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            this.pauseUI.setWord(this.pauseUI.getWord().substring(0, this.pauseUI.getWord().length() - 1));
            this.pauseUI.repaint();
        } else {

            this.pauseUI.setWord(this.pauseUI.getWord() + ke.getKeyChar());
            System.out.println(this.pauseUI.getWord());
            this.pauseUI.repaint();
        }
    }
}

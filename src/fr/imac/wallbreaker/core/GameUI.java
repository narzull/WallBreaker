package fr.imac.wallbreaker.core;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author nexus_21
 */
public class GameUI extends JFrame implements Observer, KeyListener {

    /**
     * instance of Game
     */
    private static GameUI m_gameUI = new GameUI(Game.getInstance());
    /**
     * JPanel start
     */
    private StartUI m_StartUI;
    /**
     * JPanel pause
     */
    private PauseUI m_PauseUI;
    /**
     * JPanel menu
     */
    private MenuUI m_MenuUI;
    /**
     * JPanel level
     */
    private LevelUI m_LevelUI;
    /**
     * linekd Game
     */
    private Game m_Game;

    /**
     * constructor of game user interface
     * @param game 
     */
    private GameUI(Game game) {

        m_Game = game;

        m_Game.addObs(this);


        /* create level UI */
        m_LevelUI = new LevelUI(this.m_Game.getLevel());

        /* create start UI */
        m_StartUI = new StartUI();

        /* create menu UI */
        m_MenuUI = new MenuUI();

        /* create pause UI */
        m_PauseUI = new PauseUI();
        
        /* initialize */
        this.initUI();

    }

    /**
     * return the unique instance on singleton Game
     * @return m_gameUI
     */
    public static GameUI getInstance() {
        return m_gameUI;
    }

    /**
     * initialize the configuration of the UI
     */
    public void initUI() {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setFocusable(true);
        
        this.getContentPane().add(m_StartUI);
        
        addKeyListener(this);
                
    }
    
    public void launchGame()
    {
        System.out.println("Game launch");

        
        BorderLayout centerLayout = new BorderLayout(0, 20);
        this.getContentPane().setLayout(centerLayout);

        
        /* add components in layout */

        this.getContentPane().add("Center", m_LevelUI);
        this.getContentPane().add("East", m_MenuUI);
        this.getContentPane().add("Center",m_PauseUI);
        
        this.remove(m_StartUI);
        //this.add(m_LevelUI);
        
        /* mask components */
        m_PauseUI.setVisible(false);
        //m_LevelUI.setVisible(false);
        
        KeyListener keyListener = new KeyListener() {
            public void keyPressed(KeyEvent ke) {
            	//System.out.println(keyEvent.getKeyCode());
              if(ke.getKeyCode() == KeyEvent.VK_ESCAPE){
            	  
                    if (!m_Game.isOnPause()) {
                        /* show PauseUI */
                        System.out.println(" Pause On ");
                        m_LevelUI.setVisible(false);
                        m_PauseUI.setVisible(true);
                        //this.getContentPane().remove(m_LevelUI);
                        //this.getContentPane().add("Center", m_PauseUI);
                        //this.getContentPane().validate();

                        m_Game.setOnPause(true);
                      }
                    else {
                
                        m_LevelUI.setVisible(true);
                        m_PauseUI.setVisible(false);
                        m_Game.setOnPause(false);
                        System.out.println(" Pause Off ");
                        /*
                        this.getContentPane().remove(m_PauseUI);
                        m_PauseUI = new PauseUI();
                        this.getContentPane().add("Center", m_LevelUI);
                        this.getContentPane().validate();
                        System.out.println(" Pause Off ");
                        m_Game.setOnPause(false);
                         * 
                         */
                    }
              }
              
            }

            public void keyReleased(KeyEvent keyEvent) {}
            public void keyTyped(KeyEvent keyEvent) {}

        };
        
        this.getContentPane().addKeyListener(keyListener);
        
        
        Thread t1 = new Thread(m_Game);
        t1.start();
        
        
    }
    

    @Override
    public void update() {

        System.out.println("Game updated");

        //if(this.levelUI)
        this.getContentPane().remove(m_LevelUI);
        this.getContentPane().validate();
        m_LevelUI = new LevelUI(m_Game.getLevel());
        this.getContentPane().add("Center", m_LevelUI);

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

            if (!m_Game.isOnPause()) {
                /* show PauseUI */
                System.out.println(" Pause On ");
                m_LevelUI.setVisible(false);
                m_PauseUI.setVisible(true);
                //this.getContentPane().remove(m_LevelUI);
                //this.getContentPane().add("Center", m_PauseUI);
                //this.getContentPane().validate();
                
                m_Game.setOnPause(true);
            } else {
                
                m_LevelUI.setVisible(true);
                m_PauseUI.setVisible(false);
                m_Game.setOnPause(false);
                System.out.println(" Pause Off ");
                /*
                this.getContentPane().remove(m_PauseUI);
                m_PauseUI = new PauseUI();
                this.getContentPane().add("Center", m_LevelUI);
                this.getContentPane().validate();
                System.out.println(" Pause Off ");
                m_Game.setOnPause(false);
                 * 
                 */
            }


        } else if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
            Game.getInstance().getLevel().validateWord(m_PauseUI.getWord());
        } else if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            m_PauseUI.setWord(m_PauseUI.getWord().substring(0, m_PauseUI.getWord().length() - 1));
            m_PauseUI.repaint();
        } else {

            m_PauseUI.setWord(m_PauseUI.getWord() + ke.getKeyChar());
            System.out.println(m_PauseUI.getWord());
            m_PauseUI.repaint();
        }
    }
}

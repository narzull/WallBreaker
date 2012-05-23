package fr.imac.wallbreaker.core;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author nexus_21
 */
public class GameUI extends JFrame implements Observer{

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
     * victory level image
     */
    private BasicPanel m_VictoryLevel;
    
    private BasicPanel m_VictoryGame;
    
    private BasicPanel m_DefeatLevel;
    
    private BasicPanel m_DefeatGame;
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
        m_StartUI = new StartUI(800,600,"src/fr/imac/wallbreaker/img/start.png");

        /* create menu UI */
        m_MenuUI = new MenuUI();

        /* create pause UI */
        m_PauseUI = new PauseUI();
        
        /* create basic panels */
        m_VictoryGame = new BasicPanel(600,600,"src/fr/imac/wallbreaker/img/victory.png");
        m_VictoryLevel = new BasicPanel(600,600,"src/fr/imac/wallbreaker/img/victory_center.png");
        m_DefeatGame = new BasicPanel(600,600,"src/fr/imac/wallbreaker/img/defeat.png");
        m_DefeatLevel = new BasicPanel(600,600,"src/fr/imac/wallbreaker/img/defeat_center.png");

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
                
    }
    
    public void launchGame() {
        BorderLayout centerLayout = new BorderLayout(0, 20);
        this.getContentPane().setLayout(centerLayout);


        /* add components in layout */
        
        this.getContentPane().add("Center", m_LevelUI);
        this.getContentPane().add("East", m_MenuUI);
        this.getContentPane().add("Center", m_PauseUI);    
        this.remove(m_StartUI);

        /* mask components */
        m_PauseUI.setVisible(false);
        KeyListener keyListener = new KeyListener() {
            
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    
                    if (!m_Game.isOnPause()) {
                        /* show PauseUI */
                        m_LevelUI.setVisible(false);
                        m_PauseUI.setVisible(true);

                        m_Game.setOnPause(true);
                    } else {
                        
                        m_LevelUI.setVisible(true);
                        m_PauseUI.setVisible(false);
                        m_Game.setOnPause(false);
                    }
                } else if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (Game.getInstance().isOnPause()) {
                        m_PauseUI.setVisible(false);
                        m_Game.setOnPause(false);
                        
                        if (Game.getInstance().getLevel().validateWord(m_PauseUI.getWord())) {
                            GameUI.getInstance().getContentPane().remove(m_DefeatLevel);
                            GameUI.getInstance().getContentPane().add("Center", m_VictoryLevel);
                            m_VictoryLevel.setVisible(true); 
                            m_PauseUI.setWord("");
                            
                        } else {
                            GameUI.getInstance().getContentPane().remove(m_VictoryLevel);
                            GameUI.getInstance().getContentPane().add("Center", m_DefeatLevel);
                            m_DefeatLevel.setVisible(true);
                            Game.getInstance().setOnPause(false);
                            m_PauseUI.setWord("");
                            
                            
                        }
                    }
                    
                    
                } else if(ke.getKeyCode() == KeyEvent.VK_SPACE) {
                    
                } else if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    m_PauseUI.setWord(m_PauseUI.getWord().substring(0, m_PauseUI.getWord().length() - 1));
                    m_PauseUI.repaint();
                } else {
                    
                    m_PauseUI.setWord(m_PauseUI.getWord() + ke.getKeyChar());
                    m_PauseUI.repaint();
                }
                
            }
            
            public void keyReleased(KeyEvent keyEvent) {
            }
            
            public void keyTyped(KeyEvent keyEvent) {
            }
        };
        
        this.getContentPane().addKeyListener(keyListener);
        
        
        Thread t1 = new Thread(m_Game);
        t1.start();
        update();
        
        
    }
    
    @Override
    public void update() {        
        if (!m_Game.getLvlisRunning()) {            
            this.getContentPane().remove(m_LevelUI);
            this.getContentPane().validate();
            m_LevelUI = new LevelUI(m_Game.getLevel());
            this.getContentPane().add("Center", m_LevelUI);            
        }
        
        /* updater le menu */
        m_MenuUI.updateDisplays();
        
        if(!m_Game.isRunning())
        {
            m_LevelUI.setVisible(false);
            m_PauseUI.setVisible(false);
            m_DefeatLevel.setVisible(false);
            m_VictoryLevel.setVisible(false);
            this.validate();
            
            if(m_Game.isFinished()){
                this.getContentPane().add(m_VictoryGame);
                m_VictoryGame.setVisible(true);
                m_VictoryGame.repaint();
            }
            else
            {
                this.getContentPane().add(m_DefeatGame);
                m_DefeatGame.setVisible(true);
                m_DefeatGame.repaint();
            }
                
            
        }
        
    }
}

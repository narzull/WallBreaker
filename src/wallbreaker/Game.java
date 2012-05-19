package wallbreaker;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;
import org.jbox2d.dynamics.World;

/**
 *
 * @author nexus_21
 */
public class Game implements Runnable, Observable {

    /**
     * instance of Game
     */
    private static Game m_game = new Game();
    
    
    
    /**
     * number of lifes
     */
    private int m_Lifes;
    /**
     * game's score
     */

    private int m_Score;
	
    /**
     * index of current level
     */
    private int m_IdCurrentLvl;
    /**
     * Max id for m_IdCurrentLvl
     */
    private int m_IdFinalLvl;
    /**
     * list of game's levels
     */
    private Level m_Level;
    /**
     * file where the current play is saved
     */
    private File save;
    /**
     * file which contains the high scores
     */
    private File highScores;
    /**
     * file which contains the words to find
     */
    private File dictionnary;
    /**
     * Physic World
     */
    private World world;
    /**
     * list of observer
     */
    private ArrayList<Observer> listObs;
    
    
    private boolean isRunning;
    /**
     * If current level is ok
     */
    private boolean m_LvlIsRunning;
    
    private boolean m_onPause;

    /**
     * game's constructor
     */
    private Game() {
        this.m_Lifes = 3;
        this.m_Score = 0;

        this.m_IdCurrentLvl = 0;
        this.m_IdFinalLvl = 1;
        this.highScores = new File("highscores.txt");
        this.save = new File("save.txt");
        this.dictionnary = new File("dictionnary.txt");

        this.listObs = new ArrayList<Observer>();
        this.isRunning = true;
        this.m_LvlIsRunning = false;
        this.m_onPause = false;

    }
    
    /**
     * return the unique instance on singleton Game
     * @return m_game
     */
    public static Game getInstance()
    {
        return m_game;
    }

    /**
     * initialize
     */
    public void initializeGame() {
        System.out.println("Game initialized");

        m_Level = new Level(m_IdCurrentLvl);
        m_Level.initializeLevel();
        m_LvlIsRunning = true;
    }

    /**
     * save the current game
     */
    public void save() {
    }

    /**
     * load the previous game
     */
    public void load() {
    }

    @Override
    public void run() {
        while (this.isRunning) {
            try {
                Thread.sleep(15);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            if(!m_onPause){
				this.m_Level.updatePosition();
            }
            
            //End of current lvl
            if (m_LvlIsRunning == false) {
                startNextLvl();
            }
            System.out.println("Score: "+m_Score+" Lifes: "+m_Lifes);
        }
    }

    @Override
    public void addObs(Observer o) {
        this.listObs.add(o);

    }

    /**
     * delete observer o
     * @param o 
     */
    @Override
    public void delObs(Observer o) {
        this.listObs.remove(o);
    }

    /**
     * update all observers
     */
    @Override
    public void updateObs() {
        for (Observer o : this.listObs) {
            o.update();
        }
    }

    /**
     * Return current level
     * @return currentLevel
     */
    public Level getLevel() {
        return m_Level;
    }

    public void finishCurrentLvl() {
        m_LvlIsRunning = false;
    }

    public void startNextLvl() {

        System.out.println("Start Game " + m_IdCurrentLvl);

        if (m_IdCurrentLvl == m_IdFinalLvl) {
            System.out.println("It's OVER");
            System.exit(0);
        } else {
            ++m_IdCurrentLvl;
            PhysicWorld.getInstance().reset();
            m_Level = new Level(m_IdCurrentLvl);
            m_Level.initializeLevel();
            updateObs();
            m_LvlIsRunning = true;
        }
    }
    
    public void setOnPause(boolean pause)
    {
        this.m_onPause = pause;
    }
    
    public boolean isOnPause()
    {
        return this.m_onPause;
    }
    
    public void addScore(int score){
            m_Score += score;
    }

    public void addLife(){
            ++m_Lifes;
    }

}

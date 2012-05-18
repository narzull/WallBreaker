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
     * number of lifes
     */
    private int lifes;
    /**
     * game's score
     */
    private int score;
	
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

    /**
     * game's constructor
     */
    public Game() {
        this.lifes = 3;
        this.score = 0;

        this.m_IdCurrentLvl = 0;
		this.m_IdFinalLvl = 1;
        this.highScores = new File("highscores.txt");
        this.save = new File("save.txt");
        this.dictionnary = new File("dictionnary.txt");

        this.listObs = new ArrayList<Observer>();
        this.isRunning = true;
		this.m_LvlIsRunning = false;

    }

    /**
     * initialize
     */
    public void initializeGame() {
        System.out.println("Game initialized");

        m_Level = new Level(this, m_IdCurrentLvl);
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
            this.m_Level.updatePosition();
			
			//End of current lvl
			if(m_LvlIsRunning == false)
				startNextLvl();
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
	public Level getLevel(){
		return m_Level;
	}
	
	public void finishCurrentLvl(){
		m_LvlIsRunning = false;
	}
	
	public void startNextLvl(){
		
		System.out.println("Start Game "+m_IdCurrentLvl);
		
		if(m_IdCurrentLvl == m_IdFinalLvl){
			System.out.println("It's OVER");
			System.exit(0);
		}
		else{
			++m_IdCurrentLvl;
			PhysicWorld.getInstance().reset();
			m_Level = new Level(this, m_IdCurrentLvl);
			m_Level.initializeLevel();
			updateObs();
			m_LvlIsRunning = true;
		}
	}
}

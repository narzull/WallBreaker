package wallbreaker;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;
import org.jbox2d.dynamics.World;

/**
 *
 * @author nexus_21
 */
public class Game implements Runnable, Observable{
    
    
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
    private int indCurrentLevel;
    /**
     * list of game's levels
     */
    private ArrayList<Level> levels;
    
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
     * game's constructor
     */
    public Game()
    {
        this.lifes = 3;
        this.score = 0;
        
        this.indCurrentLevel = 0;
        this.levels = new ArrayList<Level>();
        
        this.highScores = new File("highscores.txt");
        this.save = new File("save.txt");
        this.dictionnary = new File("dictionnary.txt");
        
        this.listObs = new ArrayList<Observer>();
        this.isRunning = true;
        
    }
    
    
    /**
     * initialize
     */
    public void initializeGame()
    {
        System.out.println("Game initialized");
		
        Level lvl = new Level("test");
        lvl.initializeLevel();
        this.addLevel(lvl);
    }
	
    /**
     * save the current game
     */
    public void save()
    {
        
    }
    
    /**
     * load the previous game
     */
    public void load()
    {
        
    }
    
    public void addLevel(Level level)
    {
        this.levels.add(level);

        //this.indCurrentLevel++;
    }
    
    public Level getCurrentLevel()
    {
        return this.levels.get(this.indCurrentLevel);
    }
    
    
    
    
    public void run(){
        while(this.isRunning)
        {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            this.getCurrentLevel().updatePosition();
        }
            
    }
    
    
    
        @Override
    public void addObs(Observer o)
    {
       this.listObs.add(o);
       
    }
    
    /**
     * delete observer o
     * @param o 
     */
    @Override
    public void delObs(Observer o)
    {
        this.listObs.remove(o);
    }
    
    /**
     * update all observers
     */
    @Override
    public void updateObs()
    {
        for(Observer o : this.listObs)
            o.update();
    }
    
}

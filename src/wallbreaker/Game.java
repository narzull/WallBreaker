package wallbreaker;

import java.util.ArrayList;
import java.util.logging.Logger;
import org.jbox2d.dynamics.World;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.collision.shapes.PolygonShape;
import java.io.File;

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
        
        //Init World Physic
        createPhysicWorld(400, 400);
		
        Level lvl = new Level("test", world);
        lvl.initializeLevel();
        this.addLevel(lvl);
    }
	
    public void createPhysicWorld(int worldWidth, int worldHeight)
    {

        //TODO WORLD HEIGHT
        Vec2 gravity = new Vec2(0.0f, 4.81f);
        boolean doSleep = true;
        world = new World(gravity, doSleep);

        BodyDef groundBodyDef = new BodyDef(); // body definition
        groundBodyDef.position.set(worldWidth/2.0f, -10.0f); // set bodydef position
        Body groundBody = world.createBody(groundBodyDef); // create body based on definition
        PolygonShape groundBox = new PolygonShape(); // make a shape representing ground
        groundBox.setAsBox(worldWidth/2.0f, 10.0f); // shape is a rect: 100 wide, 20 high
        groundBody.createFixture(groundBox, 0.0f); // bind shape to ground body
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

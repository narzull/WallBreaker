package wallbreaker;

import org.jbox2d.dynamics.World;
import java.util.ArrayList;
import org.jbox2d.common.Vec2;

/**
 *
 * @author nexus_21
 */
public class Level implements Observable{

    /**
     * word to find to end the level
     */
    private String word;
    
    /**
     * list of bricks
     */
    private ArrayList<Brick> Bricks;
    
    /**
     * paler
     */
    private Palet palet;
    
    /**
     * list of observer
     */
    private ArrayList<Observer> listObs;
    
    /**
     * number of rows 
     */
    private int nbBricksXMax;
    
    /**
     * number of lines
     */
    private int nbBricksYMax;
    
	/**
	 * Physic World
	 */
	private World world;
	
	/**
	 * Ball
	 */
	private Ball ball;
	
    /**
     * constructor of level
     * @param word 
     */
    public Level(String word, World world)
    {
        this.word = word;
        this.Bricks = new ArrayList<Brick>();
        this.listObs = new ArrayList<Observer>();
        this.nbBricksXMax = 3;
        this.nbBricksYMax = 2;
		this.world = world;
		this.ball = new Ball(world);
    }
    
    /**
     * initialize the set of bricks with random bricks
     */
    public void initializeLevel()
    {
        
        for(Integer i=0 ; i <= this.nbBricksXMax ; i++)
			for(Integer j=0 ; j <= this.nbBricksYMax ; j++)
                                    this.Bricks.add(new Brick(i*60,j*20,60,20));
                            
        
        System.out.println("Level initialized");
    }
    
    
    public ArrayList<Brick> getBricks()
    {
        return this.Bricks;
    }
    
    
    public String getWord()
    {
        return word;
    }
    
    public Ball getBall()
    {
        return ball;
    }
    
    
    /**
     * add observer o to the list of observer
     * @param o 
     */
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
	
	public void updatePosition(){
		float timeStep = 1.0f / 60.f;
		int velocityIterations = 6;
		int positionIterations = 2;

		world.step(timeStep, velocityIterations, positionIterations);
	}
}

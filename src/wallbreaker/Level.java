package wallbreaker;

import java.util.ArrayList;

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
    private ArrayList<Brick> bricks;
    
    /**
     * paler
     */
    private Paddle palet;
    
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
     * list of balls
     */
    private ArrayList<Ball> balls;
	
    /**
     * constructor of level
     * @param word 
     */
    public Level(String word)
    {
        this.word = word;
        this.bricks = new ArrayList<Brick>();
        this.listObs = new ArrayList<Observer>();

        this.nbBricksXMax = 8;
        this.nbBricksYMax = 4;

        this.balls = new ArrayList<Ball>();
    }
    
    /**
     * initialize the set of bricks with random bricks
     */
    public void initializeLevel()
    {
        /* generate balls */
        for(Integer i=0 ; i <= this.nbBricksXMax ; i++)
			for(Integer j=0 ; j <= this.nbBricksYMax ; j++)
                                    this.bricks.add(new Brick(i*60,j*30,60,30));
        
        Ball ball = new Ball(0,0,10,10);
        this.addBall(ball);
        
        System.out.println("Level initialized");
    }
    
    /**
     * getter for bricks
     * @return list of bricks
     */
    public ArrayList<Brick> getBricks()
    {
        return this.bricks;
    }
    
    
    /**
     * getter for word
     * @return String
     */
    public String getWord()
    {
        return word;
    }
    
    /**
     * add a ball
     * @param ball 
     */
    public void addBall(Ball ball)
    {
        this.balls.add(ball);
    }
    
    
    /**
     * getter for balls
     * @return 
     */
    public ArrayList<Ball> getBalls()
    {
        return balls;
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
		float timeStep = 1.0f / 60.0f;
		int velocityIterations = 6;
		int positionIterations = 2;

		PhysicWorld.getInstance().step(timeStep, velocityIterations, positionIterations);
		this.updateObs();

    }
}

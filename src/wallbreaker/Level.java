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
    private ArrayList<Brick> Bricks;
    
    /**
     * paler
     */
    private Palet palet;
    
    /**
     * list of observer
     */
    private ArrayList<Observers> listObs;
    
    /**
     * number of rows 
     */
    private int nbBricksXMax;
    
    /**
     * number of lines
     */
    private int nbBricksYMax;
    
    /**
     * constructor of level
     * @param word 
     */
    public Level(String word)
    {
        this.word = word;
        this.Bricks = new ArrayList<Brick>();
        this.listObs = new ArrayList<Observers>();
        this.nbBricksXMax = 3;
        this.nbBricksYMax = 2;
    }
    
    /**
     * initialize the set of bricks with random bricks
     */
    public void initializeLevel()
    {
        
        for(Integer i=0 ; i <= this.nbBricksXMax ; i++)
			for(Integer j=0 ; j <= this.nbBricksYMax ; j++)
                                    this.Bricks.add(new Brick(i*25,j*15, 25,15));
                            
        
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
    
    
    /**
     * add observer o to the list of observer
     * @param o 
     */
    @Override
    public void addObs(Observers o)
    {
       this.listObs.add(o);
       
    }
    
    /**
     * delete observer o
     * @param o 
     */
    @Override
    public void delObs(Observers o)
    {
        this.listObs.remove(o);
    }
    
    /**
     * update all observers
     */
    @Override
    public void updateObs()
    {
        for(Observers o : this.listObs)
            o.update();
    }
}

package wallbreaker;

/**
 *
 * @author nexus_21
 */
public class Brick {

    /**
     * x value 
     */
    private int x;
    
    /**
     * y value
     */
    private int y;
    
    /**
     * width of the brick
     */
    private int width;
    
    /**
     * height of the brick
     */
    private int height;
    
    /**
     * state of the brick
     */
    private boolean destroyed;
    
    
    /**
     * constructor of Brick
     * @param x
     * @param y
     * @param width
     * @param height 
     */
    public Brick(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.destroyed = false;
    }
    
    public int getX()
    {
        return this.x;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    public int getWidth()
    {
        return this.width;
    }
    
    public int getHeight()
    {
        return this.height;
    }
    
    public boolean isDestroyed()
    {
        return this.destroyed;
    }
}

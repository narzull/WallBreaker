package wallbreaker;

/**
 *
 * @author nexus_21
 */
public class Brick extends Sprite{
        
    /**
     * constructor of Brick
     * @param x
     * @param y
     * @param width
     * @param height 
     */
    public Brick(int x, int y, int width, int height)
    {
        super(x,y,width,height);
        this.destroyed = false;
    }
}
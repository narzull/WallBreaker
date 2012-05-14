package wallbreaker;

import java.awt.Image;
import org.jbox2d.dynamics.Body;

/**
 *
 * @author nexus_21
 */
public abstract class Sprite {
    
    protected int x;
    
    protected int y;
    
    protected int width;
    
    protected int height;
    
    
    /**
     * state of the brick
     */
    protected boolean destroyed;
    
    protected Image image;
    
    protected String imagePath;
    
    protected Body physicalBody;
    
    
    
    
    public Sprite(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.destroyed = true;
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
    
    public Image getImage()
    {
        return this.image;
    }
    
    public String getImagePath()
    {
        return this.imagePath;
    }
    
    public Body getPhysicalBody()
    {
        return this.physicalBody;
    }
}

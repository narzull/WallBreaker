package wallbreaker;

import java.awt.Image;
import org.jbox2d.dynamics.Body;

/**
 *
 * @author nexus_21
 */
public abstract class Sprite {
    
    protected float width;
    
    protected float height;
    
    /**
     * state of the brick
     */
    protected boolean destroyed;
    
    protected Image image;
    
    protected String imagePath;
    
    protected Body physicalBody;
    
    
    
    
    public Sprite(float width, float height){
        this.width = width;
        this.height = height;
        this.destroyed = true;
    }
    
    
    public float getX(){
        return physicalBody.getPosition().x;
    }
    
    public float getY(){
        return physicalBody.getPosition().y;
    }
    
    public float getWidth(){
        return this.width;
    }
    
    public float getHeight(){
        return this.height;
    }
    
    public boolean isDestroyed(){
        return this.destroyed;
    }
    
    public Image getImage(){
        return this.image;
    }
    
    public String getImagePath(){
        return this.imagePath;
    }
    
    public Body getPhysicalBody(){
        return this.physicalBody;
    }
}

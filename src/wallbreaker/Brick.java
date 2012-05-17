package wallbreaker;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

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
        
        this.imagePath = "src/img/brick1.png";
        try{
            this.image = ImageIO.read(new File(this.getImagePath()));
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
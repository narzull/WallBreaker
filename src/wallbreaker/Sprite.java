package wallbreaker;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
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

    public Sprite(float width, float height, String imagePath) {

        this.width = width;
        this.height = height;
        this.destroyed = false;
        this.imagePath = imagePath;
        try {
            this.image = ImageIO.read(new File(this.getImagePath()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public float getX() {
        return physicalBody.getPosition().x;
    }

    public float getY() {
        return physicalBody.getPosition().y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public boolean isDestroyed() {
        return this.destroyed;
    }

    public Image getImage() {
        return this.image;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public Body getPhysicalBody() {
        return this.physicalBody;
    }
}

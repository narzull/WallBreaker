package wallbreaker;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

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
    public Brick(float x, float y, float width, float height)
    {
        super(width,height);
        this.destroyed = false;
        
        this.imagePath = "src/img/brick1.png";
        try{
            this.image = ImageIO.read(new File(this.getImagePath()));
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		//Physic Construction
		{
			PolygonShape shape = new PolygonShape();
			shape.setAsBox((width/2.0f), (height/2.0f));
			FixtureDef fd = new FixtureDef();
			fd.shape = shape;
			fd.density = 1.0f;

			BodyDef bd = new BodyDef();
			bd.type = BodyType.STATIC;
			bd.position.set(x, y);

			physicalBody = PhysicWorld.getInstance().createBody(bd);


			physicalBody.createFixture(fd);
		}
    }
	
	@Override
	public float getX(){
		return physicalBody.getPosition().x;
	}
	
	@Override
	public float getY(){
		return physicalBody.getPosition().y;
	}
}
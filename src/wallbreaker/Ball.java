/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wallbreaker;
import org.jbox2d.dynamics.World;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.collision.shapes.PolygonShape;

/**
 *
 * @author nexus_21
 */

public class Ball{
	/**
	 * Physical Body
	 */
	private Body physicalBody;
	
	Ball(World world){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC; 
		bodyDef.position.set(0.0f, 4.0f);
		
		physicalBody = world.createBody(bodyDef);
		PolygonShape dynamicBox = new PolygonShape();
		dynamicBox.setAsBox(1.0f, 1.0f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicBox;
		fixtureDef.density = 1.0f;
		fixtureDef.friction = 0.3f;
		physicalBody.createFixture(fixtureDef);
	}
}

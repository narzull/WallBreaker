/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wallbreaker;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.World;


/**
 *
 * @author nexus_21
 */

public class Ball extends Sprite{

    /**
     * 
     */
    private int radius;

    /**
     * Physical Body
     */
    private Body physicalBody;

    /**
     * constructor Ball
     * @param world 
     */
    public Ball(int x, int y, int width, int height){
            super(x,y,width,height);
            this.destroyed = false;
            this.radius = 15;
            
			{
				CircleShape shape = new CircleShape();
				shape.m_radius = 1.0f;

				FixtureDef fd = new FixtureDef();
				fd.shape = shape;
				fd.density = 1.0f;

				BodyDef bd = new BodyDef();
				bd.type = BodyType.DYNAMIC;
				bd.position.set(3.0f, 3.0f);

				physicalBody = PhysicWorld.getInstance().createBody(bd);

				fd.restitution = 1.0f;
				physicalBody.createFixture(fd);
			}
    }

    public int getX(){
        return (int)(physicalBody.getPosition().x*100);
    }

    public int getY(){
        return (int)((6 - physicalBody.getPosition().y)*100);
    }
    
    public int getRadius()
    {
        return this.radius;
    }
    
}

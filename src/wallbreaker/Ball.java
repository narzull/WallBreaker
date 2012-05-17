/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wallbreaker;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;


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
     * constructor Ball
     * @param world 
     */
    public Ball(float x, float y, int width, int height){
            super(width,height);
            this.destroyed = false;
            this.radius = 15;
            
			{
				CircleShape shape = new CircleShape();
				shape.m_radius = this.radius/PhysicWorld.scalePhysicWorldToRealWorld;

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
	
    public int getXWindow(){
        return (int)(physicalBody.getPosition().x*PhysicWorld.scalePhysicWorldToRealWorld);
    }

    public int getYWindow(){
        return (int)((6 - physicalBody.getPosition().y)*PhysicWorld.scalePhysicWorldToRealWorld);
    }
    
    public int getRadius()
    {
        return this.radius;
    }
    
}

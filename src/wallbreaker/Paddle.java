/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wallbreaker;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.MouseJoint;
import org.jbox2d.dynamics.joints.MouseJointDef;

/**
 *
 * @author nexus_21
 */
public class Paddle extends Sprite{
 
    
    public Paddle(float x, float y, float width, float height,String imagePath)
    {
        super(width,height,imagePath);
        
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.width/2.0f, this.height/2.0f);
        
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 1.0f;
        fd.restitution = 1.05f;

        BodyDef bd = new BodyDef();
        bd.type = BodyType.STATIC;
        bd.position.set(x,y);

        physicalBody = PhysicWorld.getInstance().createBody(bd);

        physicalBody.createFixture(fd);
        
        //MouseJointDef mouseJointDef = new MouseJointDef();
        //mouseJointDef.bodyA = this.physicalBody;
        
        //MouseJoint mouseJoint = (MouseJoint)(PhysicWorld.getInstance().createJoint(mouseJointDef));
    }

    public void setX(float x)
    {
        float y = this.physicalBody.getPosition().y;
        this.physicalBody.setTransform(new Vec2(x/PhysicWorld.scalePhysicWorldToRealWorld,y),0);
        //this.printPhysicBodyPosition();
    }
    
    public void printPhysicBodyPosition()
    {
        System.out.println("Physic Body PADDLE position : x = " + this.physicalBody.getPosition().x 
                + " ------  y = " + this.physicalBody.getPosition().y);
    }
}

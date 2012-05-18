/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wallbreaker;

import org.jbox2d.collision.shapes.PolygonShape;
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
        shape.setAsBox(this.width / PhysicWorld.scalePhysicWorldToRealWorld, this.height / PhysicWorld.scalePhysicWorldToRealWorld);
        
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 1.0f;

        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        bd.position.set(x,y);

        physicalBody = PhysicWorld.getInstance().createBody(bd);

        physicalBody.createFixture(fd);
        
        //MouseJointDef mouseJointDef = new MouseJointDef();
        //mouseJointDef.bodyA = this.physicalBody;
        
        //MouseJoint mouseJoint = (MouseJoint)(PhysicWorld.getInstance().createJoint(mouseJointDef));
    }

}

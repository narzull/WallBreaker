/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wallbreaker;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.joints.MouseJoint;
import org.jbox2d.dynamics.joints.MouseJointDef;

/**
 *
 * @author nexus_21
 */
public class Paddle extends Sprite {

    private MouseJoint m_mouseJoint;
    private float m_y;

    public Paddle(float x, float y, float width, float height, String imagePath) {
        super(width, height, imagePath);

        m_y = y;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.width / 2.0f, this.height / 2.0f);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 1.0f;
        fd.restitution = 1.80f;

        BodyDef bd = new BodyDef();
        bd.type = BodyType.DYNAMIC;
        bd.position.set(x, m_y);
        bd.angularDamping = 0.0f;
        bd.fixedRotation = true;

        physicalBody = PhysicWorld.getInstance().createBody(bd);

        physicalBody.createFixture(fd);


        MouseJointDef mjd = new MouseJointDef();
        mjd.bodyA = PhysicWorld.getInstance().getGroundBody();
        mjd.bodyB = physicalBody;
        mjd.target.set(new Vec2(x, y));
        mjd.collideConnected = true;
        mjd.maxForce = (float) (300.0 * physicalBody.getMass());

        m_mouseJoint = (MouseJoint) PhysicWorld.getInstance().createJoint(mjd);

        physicalBody.setAwake(true);
    }

    public void setX(float x) {
        if (x / PhysicWorld.scalePhysicWorldToRealWorld < (6.0f - (this.width / 2.0f))
                && x / PhysicWorld.scalePhysicWorldToRealWorld > (this.width / 2.0f)) {
            m_mouseJoint.setTarget(new Vec2(x / PhysicWorld.scalePhysicWorldToRealWorld, m_y));
        }
        //this.physicalBody.setTransform(new Vec2(x / PhysicWorld.scalePhysicWorldToRealWorld, y), 0);
        //this.printPhysicBodyPosition();
    }

    public void printPhysicBodyPosition() {
        System.out.println("Physic Body PADDLE position : x = " + this.physicalBody.getPosition().x
                + " ------  y = " + this.physicalBody.getPosition().y);
    }
}

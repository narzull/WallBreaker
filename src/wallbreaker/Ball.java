/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wallbreaker;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

/**
 *
 * @author nexus_21
 */
public class Ball extends Sprite {

    /**
     * 
     */
    private float radius;

    /**
     * constructor Ball
     * @param world 
     */
    public Ball(float x, float y, String imagePath) {
        super(0.0f, 0.0f, imagePath);
        this.destroyed = false;
        this.radius = 10 / PhysicWorld.scalePhysicWorldToRealWorld;


        {
            CircleShape shape = new CircleShape();
            shape.m_radius = this.radius;

            FixtureDef fd = new FixtureDef();
            fd.shape = shape;
            fd.density = 1.0f;

            BodyDef bd = new BodyDef();
            bd.type = BodyType.DYNAMIC;
            bd.position.set(x, y);
            bd.fixedRotation = true;
            bd.userData = this;

            physicalBody = PhysicWorld.getInstance().createBody(bd);

            fd.restitution = 1.0f;
            physicalBody.createFixture(fd);
        }
    }

    public int getXWindow() {
        return (int) (physicalBody.getPosition().x * PhysicWorld.scalePhysicWorldToRealWorld);
    }

    public int getYWindow() {
        return (int) ((6 - physicalBody.getPosition().y) * PhysicWorld.scalePhysicWorldToRealWorld);
    }

    public float getRadius() {
        return this.radius;
    }

    public void printPhysicBodyPosition() {
        System.out.println("Physic Body BALL position : x = " + this.physicalBody.getPosition().x
                + " ------  y = " + this.physicalBody.getPosition().y);
    }
}

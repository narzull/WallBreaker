/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wallbreaker;

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

public class Ball{

    /**
     * 
     */
    private int radius;

    /**
     * Physical Body
     */
    private Body physicalBody;

    /**
     * Physical World
     */
    private World world;

    /**
     * constructor Ball
     * @param world 
     */
    public Ball(World world){

            this.world = world;
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyType.DYNAMIC; 
            bodyDef.position.set(0.0f, 4.0f);

            physicalBody = this.world.createBody(bodyDef);
            PolygonShape dynamicBox = new PolygonShape();
            dynamicBox.setAsBox(1.0f, 1.0f);
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = dynamicBox;
            fixtureDef.density = 1.0f;
            fixtureDef.friction = 0.3f;
            physicalBody.createFixture(fixtureDef);
    }

    public int getX(){
        return Math.round(physicalBody.getPosition().x);
    }

    public int getY(){
        return Math.round(physicalBody.getPosition().y);
    }
}

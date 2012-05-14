/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wallbreaker;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

/**
 *
 * @author nexus_21
 */
public class Paddle {
 
    
    /**
     * Physical Body
     */
    private Body physicalBody;

    /**
     * Physical World
     */
    private World world;
    /*
    
    public Palet(World world){

            this.world = world;
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyType.DYNAMIC; 
            bodyDef.position.set(100.0f, 50.0f);

            physicalBody = this.world.createBody(bodyDef);
            //PolygonShape dynamicBox = new PolygonShape();
            CircleShape dynamicCircle = new CircleShape();
            dynamicCircle.m_p.set(100.0f, 50.0f);
            dynamicCircle.m_radius = 15.f;
            //dynamicBox.setAsBox(1.0f, 1.0f);
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = dynamicCircle;
            //fixtureDef.shape = dynamicBox;
            fixtureDef.density = 1.0f;
            fixtureDef.friction = 0.3f;
            physicalBody.createFixture(fixtureDef);
            
            this.radius = 15;
    }
     * 
     */
}

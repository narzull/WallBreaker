/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wallbreaker;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;


/**
 *
 * @author nexus_21
 */
public class PhysicWorld extends World {
    /**
     * instance de la classe
     */
    private static PhysicWorld physicWorld = new PhysicWorld(new Vec2(0, -10f), true);
    
    
    /**
     * constructeur privé pour utiliser le pattern singleton
     */
    private PhysicWorld(Vec2 gravity, boolean doSleep){
		super(gravity, doSleep);
		
		{
			BodyDef bd = new BodyDef();
			Body ground = super.createBody(bd);

			PolygonShape shape = new PolygonShape();
			shape.setAsEdge(new Vec2(-40.0f, 0.0f), new Vec2(40.0f, 0.0f));
			ground.createFixture(shape, 0.0f);
		}
	};
    
    
    /**
     * retourne l'unique instance de cette classe
     * @return physicWordl
     */
    public static PhysicWorld getInstance() { 
            return physicWorld; 
    }
}

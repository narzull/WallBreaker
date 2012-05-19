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
    private static PhysicWorld physicWorld = new PhysicWorld(new Vec2(0, -5f), true);
    public static float scalePhysicWorldToRealWorld = 100.0f;
    private Body m_Ground;

    /**
     * constructeur privé pour utiliser le pattern singleton
     */
    private PhysicWorld(Vec2 gravity, boolean doSleep) {
        super(gravity, doSleep);
        createGround();
    }

    private void createGround() {
        BodyDef bd = new BodyDef();
        m_Ground = super.createBody(bd);

        PolygonShape shapeBot = new PolygonShape();
        PolygonShape shapeRight = new PolygonShape();
        PolygonShape shapeLeft = new PolygonShape();
        PolygonShape shapeTop = new PolygonShape();

        shapeBot.setAsEdge(new Vec2(-6.0f, 0.0f), new Vec2(6.0f, 0.0f));
        shapeRight.setAsEdge(new Vec2(6.0f, -6.0f), new Vec2(6.0f, 6.0f));
        shapeLeft.setAsEdge(new Vec2(0.0f, -6.0f), new Vec2(0.0f, 6.0f));
        shapeTop.setAsEdge(new Vec2(-6.0f, 6.0f), new Vec2(6.0f, 6.0f));

        m_Ground.createFixture(shapeBot, 0.0f);
        m_Ground.createFixture(shapeRight, 0.0f);
        m_Ground.createFixture(shapeLeft, 0.0f);
        m_Ground.createFixture(shapeTop, 0.0f);
    }

    /**
     * retourne l'unique instance de cette classe
     * @return physicWordl
     */
    public static PhysicWorld getInstance() {
        return physicWorld;
    }

    public void reset() {
        Body currentBody = physicWorld.getBodyList();
        while (currentBody != null) {
            physicWorld.destroyBody(currentBody);
            currentBody = physicWorld.getBodyList();
        }

        createGround();
    }

    public Body getGroundBody() {
        return m_Ground;
    }
}

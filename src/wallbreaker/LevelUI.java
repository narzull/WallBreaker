package wallbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author nexus_21
 */
public class LevelUI extends JPanel implements Observer{
    
    /**
     * linked level
     */
    private Level level;
    
    /**
     * constructor level user interface
     * @param level 
     */
    public LevelUI(Level level){
		this.level = level;
                System.out.println(this.level.getWord());
		this.level.addObs(this);
	}
    
    /**
     * update the level user interface
     */
    public void update(){
        repaint();
        System.out.println("update");
    }
    
    /**
     * paint method
     */
    public void paint(Graphics g){
        super.paint(g);
        
        /* bricks display*/
        ArrayList<Brick> bricks = this.level.getBricks();
        for (Brick b : bricks){
            
            if(!b.isDestroyed())
            {
                g.setColor(Color.white);
                g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
               
                
                g.setColor(Color.blue);
                g.drawRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
                
                System.out.println(b.getX() + " : " + b.getY());
            }
                
        }
        
        /* ball display */
        ArrayList<Ball> balls = this.level.getBalls();
        for (Ball ball : balls)
        {
            System.out.println("ball");
            g.setColor(Color.yellow);
            g.fillOval(ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius());
            g.setColor(Color.red);
            g.drawOval(ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius());
        }
        
        
        
        /* water display*/
        g.setColor(Color.CYAN);
        g.fillRect(0, 500, 600, 100);
    }
}

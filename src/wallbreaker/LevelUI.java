package wallbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author nexus_21
 */
public class LevelUI extends JPanel implements Observers{
    
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
        
        System.out.println("paint");
        
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
        
        
        
    }
}

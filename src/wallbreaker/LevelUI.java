package wallbreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
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
    
    
    private Image bgdImg;
    
    
    private Image waterImg;
    
    /**
     * constructor level user interface
     * @param level 
     */
    public LevelUI(Level level){
		this.level = level;
		this.setSize(600, 600);
		System.out.println(this.level.getWord());
		this.level.addObs(this);
                
		try {
				bgdImg = ImageIO.read(new File("src/img/fond.jpg"));
				waterImg = ImageIO.read(new File("src/img/water.png"));
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    /**
     * update the level user interface
     */
    public void update(){
        repaint();
        System.out.println("Level updated");
    }
    
    /**
     * paint method
     */
    public void paint(Graphics g){
        super.paint(g);
        
        /* background display */
        
        g.drawImage(this.bgdImg, 0, 0, this.getWidth(), this.getHeight(), this);
        
        /* bricks display*/
        ArrayList<Brick> bricks = this.level.getBricks();
        for (Brick b : bricks){
            
            if(!b.isDestroyed())
            {
        
                g.drawImage(b.getImage(), (int)(b.getX()*PhysicWorld.scalePhysicWorldToRealWorld), (int)((6-b.getY())*PhysicWorld.scalePhysicWorldToRealWorld),(int)(b.getWidth()*100), (int)(b.getHeight()*100),this);
                
                /*
                g.setColor(Color.white);
                g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
               
                
                g.setColor(Color.blue);
                g.drawRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
                
                //System.out.println(b.getX() + " : " + b.getY());
                 * 
                 */
            }
                
        }
		/* water display*/
        g.drawImage(this.waterImg, 0, this.getHeight() - 100, 600 , 100 , this);
        
        
        /* water display*/
        g.drawImage(this.waterImg, 0, this.getHeight() - 100, 600 , 100 , this);
        
        
        /* ball display */
        ArrayList<Ball> balls = this.level.getBalls();
        for (Ball ball : balls)
        {
            g.drawImage(ball.getImage(), ball.getXWindow(),ball.getYWindow(), ball.getRadius() * 2, ball.getRadius() * 2, this);
            
            //g.setColor(Color.red);
            //g.drawOval(ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius());
            
            System.out.println(ball.getX() + " --- " + ball.getY());
        }
        
        /* paddle display */
        Paddle paddle = this.level.getPaddle();
        g.drawImage( paddle.getImage(), (int)paddle.getX() , (int)((6-paddle.getY())*100) , (int)paddle.getWidth() , (int)paddle.getHeight(), this );
    }
}

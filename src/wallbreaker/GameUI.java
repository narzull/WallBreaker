package wallbreaker;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author nexus_21
 */
public class GameUI extends JFrame{
    
    /**
     * JPanel level
     */
    private LevelUI levelUI;
    
    /**
     * linekd Game
     */
    private Game game;
    
    
    /**
     * constructor of game user interface
     * @param game 
     */
    public GameUI(Game game)
    {
        this.game = game;
        
        this.setSize(800, 600);
        this.setBackground(Color.black);
        
        
        
        this.levelUI = new LevelUI(this.game.getCurrentLevel());
        this.add(this.levelUI);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.setResizable(false);
        this.setVisible(true);
        
        
        while(true)
        {
            this.game.getCurrentLevel().updatePosition();
            this.levelUI.update();
            try
            {
                    Thread.sleep(1000);
            } 
            catch (InterruptedException e) 
            {
                    // TODO Auto-generated catch block
                    System.out.println("fail");
            } 
        }
            
        
    }
}

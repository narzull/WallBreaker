package wallbreaker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author nexus_21
 */
public class GameUI extends JFrame{
    
    
    /**
     * JPanel pause
     */
    private JPanel pause;
    
    /**
     * JPanel menu
     */
    private JPanel menu;
    
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
        
        BorderLayout layout = new BorderLayout(0,20);
        this.setLayout(layout);
        
        /* create level UI */
        this.levelUI = new LevelUI(this.game.getCurrentLevel());
        this.add(this.levelUI);
        
        /* create menu UI */
        this.menu = new JPanel();
        this.menu.setPreferredSize(new Dimension(200,600));
        this.menu.setBackground(Color.RED);
        
            
        /* add components in layout */
        this.add("Center",this.levelUI);
        this.add("East",this.menu);
        
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.setResizable(false);
        this.setVisible(true);
        
        
        while(true)
        {
            this.game.getCurrentLevel().updatePosition();
            this.levelUI.update();
            try
            {
                    Thread.sleep(100);
            } 
            catch (InterruptedException e) 
            {
                    // TODO Auto-generated catch block
                    System.out.println("fail");
            } 
        }
            
        
    }
}

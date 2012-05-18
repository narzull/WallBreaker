package wallbreaker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author nexus_21
 */
public class GameUI extends JFrame implements Observer,KeyListener{
    
    
    /**
     * JPanel pause
     */
    private JPanel pauseUI;
    
    /**
     * JPanel menu
     */
    private MenuUI menuUI;
    
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
        
        this.game.addObs(this);
        
        this.setSize(800, 600);
        this.setBackground(Color.black);
        
        BorderLayout centerLayout = new BorderLayout(0,20);
        this.setLayout(centerLayout);
        
        /* create level UI */
        this.levelUI = new LevelUI(this.game.getCurrentLevel());
        this.add(this.levelUI);
        
        /* create menu UI */
        this.menuUI = new MenuUI();
        
        
            
        /* add components in layout */
        this.add("Center",this.levelUI);
        this.add("East",this.menuUI);
        
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        addKeyListener(this);
        
        
        this.setResizable(false);
        this.setVisible(true);
        
        /*
        while(true)
        {
            this.game.getCurrentLevel().updatePosition();
            try
            {
                    Thread.sleep(50);
            } 
            catch (InterruptedException e) 
            {
                    // TODO Auto-generated catch block
                    System.out.println("fail");
            } 
        }
         */  
        
    }
    
    public void update()
    {
        /* updater le menu */
        System.out.println("Game updated");
    }
    
    
    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            /* show PauseUI */
            //this.add("Center", this.pauseUI);
        }
    }
}

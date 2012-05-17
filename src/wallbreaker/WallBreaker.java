package wallbreaker;

/**
 *
 * @author nexus_21
 */
public class WallBreaker {

    
    /**
     *  game core
     */
    private Game game;
    
    /**
     *  game user interface
     */
    private GameUI gameUI;
    
    
    /**
     * constructor of the application
     * @param game
     * @param gameUI 
     */
    public WallBreaker(Game game, GameUI gameUI)
    {
        this.game = game;
        this.gameUI = gameUI;
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Game game = new Game();
        game.initializeGame();
        
        GameUI gameUI = new GameUI(game);
        
        Thread t1 = new Thread(game);
        t1.start();
    }
}

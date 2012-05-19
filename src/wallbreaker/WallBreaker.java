package wallbreaker;

/**
 *
 * @author nexus_21
 */
public class WallBreaker {

    /**
     *  game core
     */
    private Game m_Game = Game.getInstance();
    /**
     *  game user interface
     */
    private GameUI gameUI;

    /**
     * constructor of the application
     * @param game
     * @param gameUI 
     */
    public WallBreaker(GameUI gameUI) {
        this.gameUI = gameUI;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Game game = Game.getInstance();
        
        game.initializeGame();

        GameUI gameUI = new GameUI(game);

        Thread t1 = new Thread(game);
        t1.start();
    }
}

package wallbreaker;

/**
 *
 * @author nexus_21
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Game game = Game.getInstance();

        game.initializeGame();

        GameUI gameUI = new GameUI(game);
        gameUI.setVisible(true);

        Thread t1 = new Thread(game);
        t1.start();
    }
}

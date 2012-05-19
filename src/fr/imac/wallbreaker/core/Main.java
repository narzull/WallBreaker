package fr.imac.wallbreaker.core;

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

        
        GameUI.getInstance().setVisible(true);

        
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wallbreaker;

/**
 *
 * @author nexus_21
 */
public class PhysicWorld {
 
    /**
     * instance de la classe
     */
    private static PhysicWorld physicWorld = new PhysicWorld();
    
    
    /**
     * constructeur privé pour utiliser le pattern singleton
     */
    private PhysicWorld(){};
    
    
    /**
     * retourne l'unique instance de cette classe
     * @return physicWordl
     */
    public static PhysicWorld getInstance() { 
            return physicWorld; 
    }
}

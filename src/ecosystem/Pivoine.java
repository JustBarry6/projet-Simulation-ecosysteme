package ecosystem;

/**
 * La classe Pivoine représente une plante vivace de type Pivoine dans l'écosystème.
 * Elle hérite de la classe abstraite Vivace.
 */
public class Pivoine extends Vivace {
    
    /**
     * Constructeur de la classe Pivoine.
     * Initialise les attributs de la Pivoine, tels que le nom, en appelant le constructeur de la classe parente Vivace.
     */
    public Pivoine() {
        super();
        nom = "Pivoine";
    }
}

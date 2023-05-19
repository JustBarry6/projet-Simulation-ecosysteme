package ecosystem;

/**
 * La classe Iris représente une plante vivace de type Iris dans l'écosystème.
 * Elle hérite de la classe abstraite Vivace.
 */
public class Iris extends Vivace {
    
    /**
     * Constructeur de la classe Iris.
     * Initialise les attributs de l'Iris, tels que le nom, en appelant le constructeur de la classe parente Vivace.
     */
    public Iris() {
        super();
        nom = "Iris";
    }
}

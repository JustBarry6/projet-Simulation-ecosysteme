package ecosystem;

/**
 * La classe Chene représente un arbre de type Chêne dans l'écosystème.
 * Elle hérite de la classe abstraite Arbre.
 */
public class Chene extends Arbre {
    
    /**
     * Constructeur de la classe Chene.
     * Initialise les attributs du Chêne, tels que le nom, en appelant le constructeur de la classe parente Arbre.
     */
    public Chene() {
        super();
        nom = "Chene";
    }
}

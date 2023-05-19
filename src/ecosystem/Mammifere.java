package ecosystem;

import java.awt.Color;

/**
 * La classe abstraite Mammifere représente un mammifère dans l'écosystème.
 * Elle hérite de la classe Animal.
 */
public abstract class Mammifere extends Animal {
    
    /**
     * Constructeur de la classe Mammifere.
     * @param rayon Le rayon du mammifère.
     * @param c La couleur du mammifère.
     */
    public Mammifere(int rayon, Color c) {
        super(rayon, c);
    }
    
}

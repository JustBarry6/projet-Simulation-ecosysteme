package ecosystem;

import java.awt.Color;

/**
 * La classe abstraite Insecte représente un insecte dans l'écosystème.
 * Elle hérite de la classe Animal.
 */
public abstract class Insecte extends Animal {

    /**
     * Constructeur de la classe Insecte.
     * @param rayon Le rayon de l'insecte.
     * @param c La couleur de l'insecte.
     */
    public Insecte(int rayon, Color c) {
        super(rayon, c);
    }
}

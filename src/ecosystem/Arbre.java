package ecosystem;

import java.awt.Color;

/**
 * La classe abstraite Arbre représente un arbre dans l'écosystème.
 */
public abstract class Arbre extends Vegetal {
    private static final int RAYON_ARBRE = 30;
    private static final Color COULEUR_ARBRE = new Color(38, 115, 0);
    private static final int SEUIL_EAU_CRITIQUE = 10;
    private static final int SEUIL_TEMP_CRITIQUE_MIN = -15;
    private static final int SEUIL_TEMP_CRITIQUE_MAX = 37;

    /**
     * Constructeur de la classe Arbre.
     * Initialise les attributs de l'arbre, tels que le nom, le rayon, les seuils d'eau et de température critique, la couleur, la quantité d'eau consommée, la quantité d'eau consommable et l'espérance de vie.
     */
    public Arbre() {
        super("Arbre", RAYON_ARBRE, SEUIL_EAU_CRITIQUE, SEUIL_TEMP_CRITIQUE_MIN, SEUIL_TEMP_CRITIQUE_MAX, COULEUR_ARBRE);
        qteEauConsommee = SEUIL_EAU_CRITIQUE; // Au départ, l'arbre a assez d'eau pour vivre
        maxEauConsommable = 20;
        esperanceDeVie = 500; // Valeur initiale à 60
    }
}

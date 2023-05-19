package ecosystem;

import java.awt.Color;

/**
 * La classe abstraite Vivace représente une plante vivace dans l'écosystème.
 */
public abstract class Vivace extends Vegetal {
    private static final int RAYON_VIVACE = 30;
    private static final Color COULEUR_VIVACE = new Color(255, 215, 0);
    private static final int SEUIL_EAU_CRITIQUE = 5;
    private static final int SEUIL_TEMP_CRITIQUE_MIN = -5;
    private static final int SEUIL_TEMP_CRITIQUE_MAX = 45;

    /**
     * Constructeur de la classe Vivace.
     * Initialise les attributs de la plante vivace, tels que le nom, le rayon, les seuils d'eau et de température critique, la couleur, la quantité d'eau consommée, la quantité d'eau consommable et l'espérance de vie.
     */
    public Vivace() {
        super("Vivace", RAYON_VIVACE, SEUIL_EAU_CRITIQUE, SEUIL_TEMP_CRITIQUE_MIN, SEUIL_TEMP_CRITIQUE_MAX, COULEUR_VIVACE);
        qteEauConsommee = SEUIL_EAU_CRITIQUE; // Au départ, le vivace a assez d'eau pour vivre
        maxEauConsommable = 20;
        esperanceDeVie = 90;
    }
}

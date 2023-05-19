package ecosystem;

import java.awt.Color;

public class Arbre extends Vegetal {
    private static final int RAYON_ARBRE = 30;
    private static final Color COULEUR_ARBRE = new Color(38, 115, 0);
    private static final int SEUIL_EAU_CRITIQUE = 50;
    private static final int SEUIL_TEMP_CRITIQUE_MIN = -10;
    private static final int SEUIL_TEMP_CRITIQUE_MAX = 40;
 
    public Arbre() {
        super("Arbre", RAYON_ARBRE, SEUIL_TEMP_CRITIQUE_MIN, SEUIL_TEMP_CRITIQUE_MAX, SEUIL_EAU_CRITIQUE, COULEUR_ARBRE);
        qteEauConsommee = SEUIL_EAU_CRITIQUE ; // Au départ l'arbre a assez d'eau pour vivre   
        maxEauConsommable = 500 ; 
        esperanceDeVie = 60 ;
    }

}


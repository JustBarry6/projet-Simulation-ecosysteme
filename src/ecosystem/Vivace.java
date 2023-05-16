package ecosystem;

import java.awt.Color;

public class Vivace extends Vegetal {
    private static final int RAYON_VIVACE = 20;
    private static final Color COULEUR_VIVACE = new Color(255, 215, 0);
    private static final int SEUIL_EAU_CRITIQUE = 20;
    private static final int SEUIL_TEMP_CRITIQUE = -5;
    

    public Vivace() {
        super("Vivace", RAYON_VIVACE, SEUIL_TEMP_CRITIQUE, SEUIL_EAU_CRITIQUE, COULEUR_VIVACE);
        qteEauConsommee = SEUIL_EAU_CRITIQUE ; // Au départ le vivace a assez d'eau pour vivre   
        maxEauConsommable = 300 ; 
    }

   
	

}

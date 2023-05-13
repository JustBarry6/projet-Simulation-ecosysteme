package ecosystem;

import java.awt.Color;

public class Arbre extends Vegetal {
    private static final int RAYON_ARBRE = 40;
    private static final Color COULEUR_ARBRE = new Color(38, 115, 0);
    private static final int SEUIL_EAU_ARBRE = 50;
    private static final int SEUIL_TEMP_ARBRE = -10;

    public Arbre() {
        super("Arbre", RAYON_ARBRE, SEUIL_TEMP_ARBRE, SEUIL_EAU_ARBRE, COULEUR_ARBRE);
    }

	@Override
	public void consommerEau() {
		// TODO Auto-generated method stub
	}
}


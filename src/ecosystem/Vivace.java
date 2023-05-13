package ecosystem;

import java.awt.Color;

public class Vivace extends Vegetal {
    private static final int RAYON_VIVACE = 20;
    private static final Color COULEUR_VIVACE = new Color(255, 215, 0);
    private static final int SEUIL_EAU_VIVACE = 20;
    private static final int SEUIL_TEMP_VIVACE = -5;

    public Vivace() {
        super("Vivace", RAYON_VIVACE, SEUIL_TEMP_VIVACE, SEUIL_EAU_VIVACE, COULEUR_VIVACE);
    }

	@Override
	public void consommerEau() {
		// TODO Auto-generated method stub

	}
}

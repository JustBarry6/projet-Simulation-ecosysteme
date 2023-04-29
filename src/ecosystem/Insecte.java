package ecosystem;

import java.awt.Color;

public abstract class Insecte extends Animal implements Herbivore {

	public Insecte(int rayon, Color c) {
        super(rayon, c);
    }

    @Override
    public void mangerVegetal() {
        // implémentation de la méthode mangerVegetal pour les insectes
    }
}

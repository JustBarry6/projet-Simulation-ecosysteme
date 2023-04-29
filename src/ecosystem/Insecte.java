package ecosystem;

import java.awt.Color;

public abstract class Insecte extends Animal implements Herbivore {

	public Insecte(int rayon, Color c, TypeAnimal type) {
        super(rayon, c, type);
    }

    @Override
    public void mangerVegetal() {
        // implémentation de la méthode mangerVegetal pour les insectes
    }
}

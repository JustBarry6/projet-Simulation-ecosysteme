package ecosystem;

import java.awt.Color;

public abstract class Oiseau extends Animal implements AnimalVolant {
    public Oiseau(int rayon, Color c, TypeAnimal type) {
        super(rayon, c, type);
    }

    @Override
    public void voler() {
        // implémentation de la méthode voler pour les oiseaux
    }
}


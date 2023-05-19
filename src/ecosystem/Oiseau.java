package ecosystem;

import java.awt.Color;

public abstract class Oiseau extends Animal implements AnimalVolant {
    public Oiseau(int rayon, Color c) {
        super(rayon, c);
    }

    @Override
    public abstract void voler();
}


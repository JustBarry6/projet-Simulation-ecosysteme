package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Biche extends Mammifere implements Herbivore {
	
	public Biche(int rayon) {
		super(rayon, Color.ORANGE, TypeAnimal.PREDATEUR);
	}

	@Override
	public void mangerVegetal() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public TypeAnimal getType() {
		return super.getType();
	}

	@Override
	public void seDeplacer() {
		// implémentation de la méthodeseDeplacer pour les Biche }
	}


	@Override
	public void boire() {
		// implémentation de la méthode boire pourles Biche
	}

	@Override
	public void mourir() {
		// implémentation de la méthode mourir pour les Biche
	}

	@Override
	public void seReproduire() {
		// implémentation de la méthode seReproduire pour les Biche
	}
}

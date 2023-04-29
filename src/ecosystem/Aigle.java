package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Aigle extends Oiseau implements Carnivore, AnimalVolant{
	
    public Aigle(int rayon) {
    	super(rayon, Color.PINK, TypeAnimal.PREDATEUR);
    }

	@Override
	public void mangerAnimal(Animal animal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TypeAnimal getType() {
		return super.getType();
	}
	

	@Override
	public void seDeplacer() {
		// implémentation de la méthodeseDeplacer pour les Aigle 
	}

	@Override
	public void boire() {
		// implémentation de la méthode boire pourles Aigle
	}

	@Override
	public void mourir() {
		// implémentation de la méthode mourir pour les Aigle
	}

	@Override
	public void seReproduire() {
		// implémentation de la méthode seReproduire pour les Aigle
	}
}

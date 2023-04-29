package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Aigle extends Oiseau implements Carnivore {
	
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
	
	

	/*
	 * @Override public void seDeplacer() { // implémentation de la méthode
	 * seDeplacer pour l'aigle voler(); }
	 * 
	 * @Override public void mourir() { // implémentation de la méthode mourir pour
	 * l'aigle }
	 * 
	 * @Override public void seReproduire() { // implémentation de la méthode
	 * seReproduire pour l'aigle }
	 * 
	 * @Override public void manger() { // TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void boire() { // TODO Auto-generated method stub
	 * 
	 * }
	 */
}

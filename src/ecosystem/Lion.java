import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Lion extends Mammifere implements Carnivore {
	
    public Lion(int rayon) {
    	super(rayon, Color.BLUE, TypeAnimal.PREDATEUR);
    }

	@Override
	public void mangerAnimal(Animal animal) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected TypeAnimal getType() {
		return super.getType();
	}
	/*
	 * @Override public void seDeplacer() { // implémentation de la méthode
	 * seDeplacer pour le lion }
	 * 
	 * @Override public void mourir() { // implémentation de la méthode mourir pour
	 * le lion }
	 * 
	 * @Override public void seReproduire() { // implémentation de la méthode
	 * seReproduire pour le lion }
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


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
	protected TypeAnimal getType() {
		return super.getType();
	}

	/*
	 * @Override public void seDeplacer() { // TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void manger() { // TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void boire() { // TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void mourir() { // TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void seReproduire() { // TODO Auto-generated method stub
	 * 
	 * }
	 */
}
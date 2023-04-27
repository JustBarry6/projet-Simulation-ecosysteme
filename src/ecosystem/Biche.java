
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Biche extends Mammifere implements Herbivore {
	
	private List<Animal> proies;
	private List<Animal> predateurs;

	public Biche(int rayon, Color c) {
		super(rayon, c);
		this.predateurs = new ArrayList<>();
		this.proies = new ArrayList<>();
	}

	@Override
	public void mangerVegetal() {
		// TODO Auto-generated method stub
		
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
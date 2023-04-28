import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Pigeon extends Oiseau {
	
	private List<Animal> proies;
	private List<Animal> predateurs;
	
    public Pigeon(int rayon) {
    	super(rayon, Color.GRAY);
    	this.proies = new ArrayList<>();
        this.predateurs = new ArrayList<>();
    }

	/*
	 * @Override public void seDeplacer() { // implémentation de la méthode
	 * seDeplacer pour le pigeon voler(); }
	 * 
	 * @Override public void mourir() { // implémentation de la méthode mourir pour
	 * le pigeon }
	 * 
	 * @Override public void seReproduire() { // implémentation de la méthode
	 * seReproduire pour le pigeon }
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
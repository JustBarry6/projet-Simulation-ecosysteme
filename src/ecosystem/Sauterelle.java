import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Sauterelle extends Insecte {
	
	
	private List<Animal> proies;
	private List<Animal> predateurs;
	
    public Sauterelle(int rayon) {
    	super(rayon, Color.BLACK);
        this.proies = new ArrayList<>();
        this.predateurs = new ArrayList<>();
    }

	/*
	 * @Override public void seDeplacer() { // implémentation de la méthode
	 * seDeplacer pour les sauterelles }
	 * 
	 * @Override public void manger() { // implémentation de la méthode manger pour
	 * les sauterelles }
	 * 
	 * @Override public void boire() { // implémentation de la méthode boire pour
	 * les sauterelles }
	 * 
	 * @Override public void mourir() { // implémentation de la méthode mourir pour
	 * les sauterelles }
	 * 
	 * @Override public void seReproduire() { // implémentation de la méthode
	 * seReproduire pour les sauterelles }
	 */
}

package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Aigle extends Oiseau implements Carnivore, AnimalVolant{
	
    public Aigle(int rayon) {
    	super(rayon, Color.PINK);
    	this.nom = "aigle" ; 
    	this.predateursV = null ; 
    	this.proiesV =  null ;
    	this.proiesA = new ArrayList <>(); 
    	this.predateursA = new ArrayList <>();  
    }

	@Override
	public void mangerAnimal(String nom) {
		// TODO Auto-generated method stub
		
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

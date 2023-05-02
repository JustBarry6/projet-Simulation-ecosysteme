package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Lion extends Mammifere implements Carnivore {
	
    public Lion(int rayon) {
    	super(rayon, Color.BLUE);
    	this.nom = "lion" ; 
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
		// implémentation de la méthodeseDeplacer pour les Lion }
	}


	@Override
	public void boire() {
		// implémentation de la méthode boire pourles Lion
	}

	@Override
	public void mourir() {
		// implémentation de la méthode mourir pour les Lion
	}

	@Override
	public void seReproduire() {
		// implémentation de la méthode seReproduire pour les Lion
	}
}

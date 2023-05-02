package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Chenille extends Insecte implements Herbivore {
	
    public Chenille(int rayon) {
    	super(rayon, Color.GREEN);
    	this.nom = "chenille" ; 
    	this.proiesA = null ; 
    	this.predateursV = null ; 
    	this.proiesV = new ArrayList <>(); 
    	this.predateursA = new ArrayList <>();  
    }
    
	@Override
	public void seDeplacer() {
		// implémentation de la méthodeseDeplacer pour les Chenilles }
	}

	@Override
	public void boire() {
		// implémentation de la méthode boire pourles Chenilles
	}

	@Override
	public void mourir() {
		// implémentation de la méthode mourir pour les Chenilles
	}

	@Override
	public void seReproduire() {
		// implémentation de la méthode seReproduire pour les Chenilles
	}
}

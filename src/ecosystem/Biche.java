package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import view.Ecosystem;

public class Biche extends Mammifere implements Herbivore {
	
	public Biche(int rayon) {
		super(rayon, Color.CYAN);
    	this.nom = "biche" ; 
    	this.proiesA = null ; 
    	this.predateursV = null ; 
    	this.proiesV = new ArrayList <Vegetal>(); 
    	this.predateursA = new ArrayList <Animal>();  
	}

	@Override
	public void mangerVegetal() {
		
	}

	@Override
	public void seDeplacer(Ecosystem eco, int i, int j) {
		moveAnimaux(eco, i, j, 25, Biche.class);
	}


	@Override
	public void boire() {
		// implémentation de la méthode boire pourles Biche
	}

	@Override
	public void mourir() {
		// implémentation de la méthode mourir pour les Biche
	}

	@Override
	public void seReproduire() {
		// implémentation de la méthode seReproduire pour les Biche
	}
}

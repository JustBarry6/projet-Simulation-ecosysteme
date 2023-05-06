package ecosystem;

import java.awt.Color;
import java.util.ArrayList;

import view.Ecosystem;

public class Pigeon extends Oiseau implements AnimalVolant, Herbivore
{
	
    public Pigeon(int rayon) {
    	super(rayon, Color.GRAY);
    	this.nom = "pigeon" ; 
    	this.proiesA = null ; 
    	this.predateursV = null ; 
    	this.proiesV = new ArrayList <Vegetal>(); 
    	this.predateursA = new ArrayList <Animal>();  
    	}
    
	@Override
	public void seDeplacer(Ecosystem eco, int i, int j) {
		moveAnimaux(eco, i, j, 25, Pigeon.class);
	}

	@Override
	public void boire() {
		// implémentation de la méthode boire pourles Pigeons
	}

	@Override
	public void mourir() {
		// implémentation de la méthode mourir pour les Pigeons
	}

	@Override
	public void seReproduire() {
		// implémentation de la méthode seReproduire pour les Pigeons
	}
	@Override
	public void mangerVegetal() {
		// TODO Auto-generated method stub
		
	}
}

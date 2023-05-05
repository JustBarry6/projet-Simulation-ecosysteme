package ecosystem;

import java.awt.Color;
import java.util.ArrayList;

import view.Ecosystem;

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

	public void seDeplacer(Ecosystem eco, int i, int j) {
		moveAnimaux(eco, i, j, 25, Aigle.class);
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

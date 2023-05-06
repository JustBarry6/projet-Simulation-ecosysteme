package ecosystem;

import java.awt.Color;
import java.util.ArrayList;

import view.Ecosystem;

public class Sauterelle extends Insecte implements Herbivore
{
	
	
    public Sauterelle(int rayon) {
    	super(rayon, Color.BLACK);
    	this.nom = "sauterelle" ; 
    	this.proiesA = null ; // La sautrelle ne mange pas d'autres animaux ; elle est herbivore
    	this.predateursV = null ; // Ses prédateurs ne peuvent pas être des vegetaux (sauf plante carnivore (pas dans le systeme))
    	this.proiesV = new ArrayList <Vegetal>(); // La sauterelle est herbivore donc ses proies sont végétales
    	this.predateursA = new ArrayList <Animal>();  
    	
    }

    @Override
    public void seDeplacer(Ecosystem eco, int i, int j) {
    	moveAnimaux(eco, i, j, 25, Sauterelle.class);
    }

	@Override
	public void boire() {
		// implémentation de la méthode boire pourles sauterelles
	}

	@Override
	public void mourir() {
		// implémentation de la méthode mourir pour les sauterelles
	}

	@Override
	public void seReproduire() {
		// implémentation de la méthode seReproduire pour les sauterelles
	}
	 
}

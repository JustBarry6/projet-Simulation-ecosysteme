package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import view.Ecosystem;

public class Biche extends Mammifere implements Herbivore {
	
	private List<Vegetal> proiesV;
	private List<Animal> predateursA;
	
	public Biche(int rayon) {
		super(rayon, Color.CYAN);
    	this.nom = "Biche"; 
    	this.proiesA = null; 
    	this.predateursV = null; 
    	this.proiesV = new ArrayList<Vegetal>(); 
    	this.predateursA = new ArrayList<Animal>();  
	}
	
	@Override
	public void manger(Ecosystem eco, int i, int j) {
	    // Récupérer la liste des végétaux dans la zone
	    List<Vegetal> vegetaux = eco.getZone(i,j).getVegetaux();

	    
	    // Rechercher un végétal
	    Vegetal vegetalTrouve = null;
	    for (Vegetal vegetal : vegetaux) {
	        if (vegetal instanceof Arbre) { // Ignorer les arbres
	            continue;
	        }
	        vegetalTrouve = vegetal;
	        break;
	    }

	    // Si un végétal a été trouvé, le consommer
	    if (vegetalTrouve != null) {
	    	
	        eco.getZone(i,j).removeVegetal(vegetalTrouve.getClass()); // Retirer le végétal de la zone
	        ajouterNourriture(vegetalTrouve); // Ajouter le végétal à la liste des nourritures de la biche

	        System.out.println("La biche a consommé un végétal : " + vegetalTrouve.getNom());
	    }
	}
	
	public void ajouterNourriture(Vegetal nourriture) {
	    this.proiesV.add(nourriture);
	}



	@Override
	public void seDeplacer(Ecosystem eco, int i, int j) {
		moveAnimaux(eco, i, j, 25, Biche.class);
	}


//	@Override
//	public void boire() {
//		// implémentation de la méthode boire pour les biches
//	}

//	@Override
//	public void mourir() {
//		// implémentation de la méthode mourir pour les biches
//	}

	@Override
	public void seReproduire() {
		// implémentation de la méthode seReproduire pour les biches
	}
}

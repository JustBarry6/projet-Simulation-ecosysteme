package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import view.Ecosystem;

public class Pigeon extends Oiseau implements AnimalVolant, Herbivore
{
	
    public Pigeon(int rayon) {
    	super(rayon, Color.GRAY);
    	this.nom = "pigeon" ; 
    	this.proiesA = new ArrayList <Animal>() ; 
    	this.predateursV = null ; 
    	this.proiesV = new ArrayList <Vegetal>(); 
    	this.predateursA = new ArrayList <Animal>();  
    	}
    
	@Override
	public void seDeplacer(Ecosystem eco, int i, int j) {
		moveAnimaux(eco, i, j, 25, Pigeon.class);
	}

//	@Override
//	public void boire() {
//		// implémentation de la méthode boire pourles Pigeons
//	}

	@Override
	public void mourir() {
		// implémentation de la méthode mourir pour les Pigeons
	}

	@Override
	public void seReproduire() {
		// implémentation de la méthode seReproduire pour les Pigeons
	}

	@Override
	public void manger(Ecosystem eco, int i, int j) {
	    // Récupérer la liste des végétaux et des animaux dans la zone
	    List<Vegetal> vegetaux = eco.getZone(i, j).getVegetaux();
	    List<Animal> animaux = eco.getZone(i, j).getAnimaux();

	    // Si la liste des végétaux n'est pas vide, chercher un végétal à consommer
	    if (!vegetaux.isEmpty()) {
	        Vegetal vegetalTrouve = vegetaux.get(0);

	        // Consommer le végétal trouvé
	        eco.getZone(i, j).removeVegetal(vegetalTrouve.getClass());
	        ajouterProieV(vegetalTrouve);
	        System.out.println("Le Pigeon a consommé un végétal : " + vegetalTrouve.getNom());

	        // Sortir de la méthode une fois que le végétal a été consommé
	        return;
	    }

	    // Si la liste des animaux n'est pas vide, chercher un animal à consommer en fonction de ses préférences
	    if (!animaux.isEmpty()) {
	        Animal animalTrouve = null;
	        for (Animal animal : animaux) {
	            if (animal instanceof Insecte) {
	                animalTrouve = animal;
	                break;
	            }
	        }

	        // Consommer l'animal trouvé
	        if (animalTrouve != null) {
	            eco.getZone(i, j).removeAnimal(animalTrouve.getClass());
	            ajouterProieA(animalTrouve);
	            System.out.println("Le Pigeon a consommé un animal : " + animalTrouve.getNom());
	        }

	        // Sortir de la méthode une fois que l'animal a été consommé
	        return;
	    }
	}
	
	private void ajouterProieA(Animal proie) {
        proiesA.add(proie);
    }
	
	public void ajouterProieV(Vegetal proie) {
		this.proiesV.add(proie);
	}
}

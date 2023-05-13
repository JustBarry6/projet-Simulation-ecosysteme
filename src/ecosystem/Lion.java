package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import view.Ecosystem;

public class Lion extends Mammifere implements Carnivore {
	
    public Lion(int rayon) {
    	super(rayon, Color.BLUE);
    	this.nom = "lion" ; 
    	this.predateursV = null ; 
    	this.proiesV =  null ;
    	this.proiesA = new ArrayList <Animal>(); 
    	this.predateursA = new ArrayList <Animal>();  
    }

    public void mangerAnimal(Ecosystem eco, int i, int j) {
        // Récupérer la liste des animaux dans la zone
        List<Animal> animaux = eco.getZone(i,j).getAnimaux();

        // Rechercher une proie
        Animal proieTrouvee = null;
        for (Animal animal : animaux) {
            if (animal instanceof Carnivore) { // Ignorer les carnivores
                continue;
            }
            if (animal instanceof Herbivore) {// Vérifier si l'animal est 
                proieTrouvee = animal;
                break;
            }
        }

        // Si une proie a été trouvée, effectuer la prédation
        if (proieTrouvee != null) {
            proieTrouvee.mourir(); // Tuer la proie
            eco.getZone(i,j).removeAnimal(proieTrouvee.getClass()); // Retirer la proie de la zone
            ajouterProieA(proieTrouvee); // Ajouter la proie à la liste des proies du lion

            System.out.println("Le lion a capturé une proie : " + proieTrouvee.getNom());
        }
    }
    
    
    private void ajouterProieA(Animal proie) {
        proiesA.add(proie);
    }
	
	@Override
	public void seDeplacer(Ecosystem eco, int i, int j) {
		moveAnimaux(eco, i, j, 25, Lion.class);
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

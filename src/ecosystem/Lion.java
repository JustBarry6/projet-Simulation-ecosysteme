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
    	this.proiesA = new ArrayList <>(); 
    	this.predateursA = new ArrayList <>();  
    }

    public void mangerAnimal(Zone zone) {
        // Gestion de la prédation d'une proie
    	List<Animal> proies = zone.getAnimaux(); // Liste des animaux proies du lion *******POUR L INSTANT TOUS LES ANIMAUX*****

        if (!proies.isEmpty()) {
            Animal proie = proies.get(0); // Sélectionner une proie (dans cet exemple, la première de la liste)

            // Effectuer l'action de prédation
            zone.removeAnimal(proie.getClass()); // Supprimer la proie de la zone
            ajouterProieA(proie); // Ajouter la proie à la liste des proies de l'aigle

            // Autres actions spécifiques au lioin lors de la prédation
            // ...

            System.out.println("Le lion a capturé une proie : " + proie.getNom());
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

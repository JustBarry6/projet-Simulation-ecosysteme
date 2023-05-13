package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import view.Ecosystem;

public class Aigle extends Oiseau implements Carnivore, AnimalVolant{
	
    public Aigle(int rayon) {
    	super(rayon, Color.PINK);
    	this.nom = "aigle" ; 
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

            System.out.println("L'aigle a capturé une proie : " + proieTrouvee.getNom());
        }
    }

    private void ajouterProieA(Animal proie) {
        proiesA.add(proie);
    }

	public void seDeplacer(Ecosystem eco, int i, int j) {
		moveAnimaux(eco, i, j, 25, Aigle.class);
		
//		// Mise à jour des prédateurs et proies dans les zones adjacentes
//	    Zone zoneActuelle = ecosystem.getZone(i, j);
//	    for (Zone zoneAdjacente : ecosystem.getZonesAdjacentes(i, j)) {
//	        List<Animal> animauxPredateurs = zoneActuelle.getAnimauxPredateursA();
//	        List<Animal> animauxProies = zoneAdjacente.getAnimauxProiesA();
//	        
//	        for (Animal predateur : animauxPredateurs) {
//	            animauxProies.add(predateur);
//	            predateur.ajouterPredateurA(this); // Définit l'aigle comme prédateur animal des proies dans la zone adjacente
//	        }
//	    }
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

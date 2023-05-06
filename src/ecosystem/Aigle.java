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

    public void mangerAnimal(Zone zone) {
        // Gestion de la prédation d'une proie
    	List<Animal> proies = zone.getAnimaux(); // Liste des animaux proies de l'aigle *******POUR L INSTANT TOUS LES ANIMAUX*****

        if (!proies.isEmpty()) {
            Animal proie = proies.get(0); // Sélectionner une proie (dans cet exemple, la première de la liste)

            // Effectuer l'action de prédation
            zone.removeAnimal(proie.getClass()); // Supprimer la proie de la zone
            ajouterProieA(proie); // Ajouter la proie à la liste des proies de l'aigle

            // Autres actions spécifiques à l'aigle lors de la prédation
            // ...

            System.out.println("L'aigle a capturé une proie : " + proie.getNom());
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

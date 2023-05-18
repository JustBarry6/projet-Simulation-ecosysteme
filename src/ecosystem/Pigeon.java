package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import view.Ecosystem;

public class Pigeon extends Oiseau implements AnimalVolant, Herbivore {
	
    public Pigeon(int rayon) {
        super(rayon, Color.GRAY);
        this.nom = "Pigeon";
        this.proiesAnimales = new ArrayList<>();
        this.predateursVegetaux = null;
        this.proiesVegetales = new ArrayList<>();
        this.predateursAnimaux = new ArrayList<>();
    }
    
    @Override
    public void seDeplacer(Ecosystem eco, int i, int j) {
        moveAnimaux(eco, i, j, 25, Pigeon.class);
    }

//	@Override
//	public void boire() {
//		// implémentation de la méthode boire pour les pigeons
//	}

//	@Override
//	public void mourir() {
//		// implémentation de la méthode mourir pour les pigeons
//	}


    @Override
    public void manger(Ecosystem eco, int i, int j) {
        // Récupérer la liste des végétaux et des animaux dans la zone
        List<Vegetal> vegetaux = eco.getZone(i, j).getVegetaux();
        List<Animal> animaux = eco.getZone(i, j).getAnimaux();

        // Si la liste des végétaux n'est pas vide, chercher un végétal à consommer
        if (!vegetaux.isEmpty()) {
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
                eco.getZone(i, j).removeVegetal(vegetalTrouve.getClass()); // Retirer le végétal de la zone
                ajouterProieVegetale(vegetalTrouve); // Ajouter le végétal à la liste des nourritures du pigeon

                System.out.println("Le pigeon a consommé un végétal : " + vegetalTrouve.getNom());
            }
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
                ajouterProieAnimale(animalTrouve);
                System.out.println("Le pigeon a consommé un animal : " + animalTrouve.getNom());
            }

            // Sortir de la méthode une fois que l'animal a été consommé
            return;
        }
    }
    
    private void ajouterProieAnimale(Animal proie) {
        proiesAnimales.add(proie);
    }
    
    public void ajouterProieVegetale(Vegetal proie) {
        this.proiesVegetales.add(proie);
    }

    @Override
    public Pigeon seReproduire(Herbivore partenaire) {
        return new Pigeon(30);
    }
}

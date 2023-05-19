package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import view.Ecosystem;

/**
 * La classe Lion représente un lion dans l'écosystème.
 */
public class Lion extends Mammifere implements Carnivore {
	
	/**
	 * Constructeur de la classe Lion.
	 * @param rayon le rayon d'action du lion
	 */
    public Lion(int rayon) {
        super(rayon, Color.BLUE);
        this.nom = "Lion";
        this.proiesVegetales = null;
        this.predateursVegetaux = null;
        this.proiesAnimales = new ArrayList<>();
        this.predateursAnimaux = new ArrayList<>();
    }

    /**
     * Méthode de prédation du lion.
     * Le lion recherche une proie dans la zone spécifiée et effectue la prédation si une proie est trouvée.
     * @param eco l'écosystème
     * @param i la position i de la zone
     * @param j la position j de la zone
     */
    @Override
    public void manger(Ecosystem eco, int i, int j) {
        // Récupérer la liste des animaux dans la zone
        List<Animal> animaux = eco.getZone(i, j).getAnimaux();

        // Rechercher une proie
        Animal proieTrouvee = null;
        for (Animal animal : animaux) {
            if (animal instanceof Carnivore) { // Ignorer les carnivores
                continue;
            }
            if (animal instanceof Herbivore) {
                proieTrouvee = animal;
                break;
            }
        }

        // Si une proie a été trouvée, effectuer la prédation
        if (proieTrouvee != null) {
            proieTrouvee.mourir(eco.getZone(i, j)); // Tuer la proie
//            eco.getZone(i, j).removeAnimal(proieTrouvee.getClass()); // Retirer la proie de la zone
            ajouterProieAnimale(proieTrouvee); // Ajouter la proie à la liste des proies du lion

            System.out.println("Le lion a capturé une proie : " + proieTrouvee.getNom());
        }
    }
    
    /**
     * Méthode privée pour ajouter une proie animale à la liste des proies du lion.
     * @param proie la proie à ajouter
     */
    private void ajouterProieAnimale(Animal proie) {
        proiesAnimales.add(proie);
    }
	
    /**
     * Méthode de déplacement du lion.
     * Le lion se déplace dans l'écosystème en utilisant l'algorithme de déplacement des animaux prédédateurs.
     * @param eco l'écosystème
     * @param i la position i de la zone
     * @param j la position j de la zone
     * @throws ZonePleineException si la zone de destination est déjà pleine
     */
    @Override
    public void seDeplacer(Ecosystem eco, int i, int j) throws ZonePleineException{
        moveAnimaux(eco, i, j, POURCENTAGE_DEPLACEMENT_PREDATEUR, Lion.class);
    }

    /**
     * Méthode de reproduction du lion.
     * @param partenaire le partenaire carnivore avec lequel se reproduire
     * @return un nouveau lion résultant de la reproduction
     */
    @Override
    public Lion seReproduire(Carnivore partenaire) {
        return new Lion(30);
    }
}

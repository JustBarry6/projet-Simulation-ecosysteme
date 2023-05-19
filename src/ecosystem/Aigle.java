package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import view.Ecosystem;

/**
 * La classe Aigle représente un aigle dans notre écosystème simulé.
 * Elle hérite de la classe Oiseau et implémente les interfaces Carnivore et AnimalVolant.
 */
public class Aigle extends Oiseau implements Carnivore, AnimalVolant {

    /**
     * Constructeur de la classe Aigle.
     * @param rayon le rayon de l'aigle
     */
    public Aigle(int rayon) {
        super(rayon, Color.PINK);
        this.nom = "Aigle";
        this.predateursVegetaux = null;
        this.proiesVegetales = null;
        this.proiesAnimales = new ArrayList<>();
        this.predateursAnimaux = new ArrayList<>();
    }

    /**
     * Méthode permettant à l'aigle de se nourrir dans l'écosystème.
     * @param eco l'écosystème
     * @param i la coordonnée i de la zone
     * @param j la coordonnée j de la zone
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
            if (animal instanceof Herbivore) { // Vérifier si l'animal est un herbivore
                proieTrouvee = animal;
                break;
            }
        }

        // Si une proie a été trouvée, effectuer la prédation
        if (proieTrouvee != null) {
            proieTrouvee.mourir(eco.getZone(i, j)); // Tuer la proie
            ajouterProieAnimale(proieTrouvee); // Ajouter la proie à la liste des proies de l'aigle

            System.out.println("L'aigle a capturé une proie : " + proieTrouvee.getNom());
        }
    }

    /**
     * Méthode privée permettant d'ajouter une proie animale à la liste des proies de l'aigle.
     * @param proie la proie à ajouter
     */
    private void ajouterProieAnimale(Animal proie) {
        proiesAnimales.add(proie);
    }

    /**
     * Méthode permettant à l'aigle de se déplacer dans l'écosystème.
     * @param eco l'écosystème
     * @param i la coordonnée i de la zone
     * @param j la coordonnée j de la zone
     * @throws ZonePleineException si la zone dans laquelle l'aigle doit se déplacer est déjà pleine
     */
    @Override
    public void seDeplacer(Ecosystem eco, int i, int j) throws ZonePleineException {
        moveAnimaux(eco, i, j, POURCENTAGE_DEPLACEMENT_PREDATEUR, Aigle.class);
    }

    /**
     * Méthode permettant à l'aigle de se reproduire avec un autre carnivore.
     * @param partenaire le partenaire de reproduction
     * @return un nouvel aigle issu de la reproduction
     */
    @Override
    public Aigle seReproduire(Carnivore partenaire) {
        return new Aigle(30);
    }

    /**
     * Méthode permettant à l'aigle de voler dans les airs.
     */
    @Override
    public void voler() {
        System.out.println("L'aigle vole dans les airs !");
    }
}

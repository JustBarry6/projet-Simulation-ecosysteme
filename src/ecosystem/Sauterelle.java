package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import view.Ecosystem;

/**
 * La classe Sauterelle représente une sauterelle dans notre écosystème simulé.
 * Elle hérite de la classe Insecte et implémente l'interface Herbivore.
 */
public class Sauterelle extends Insecte implements Herbivore {

    /**
     * Constructeur de la classe Sauterelle.
     * @param rayon le rayon de la sauterelle
     */
    public Sauterelle(int rayon) {
        super(rayon, Color.BLACK);
        this.nom = "Sauterelle";
        this.proiesVegetales = new ArrayList<>();
        this.predateursVegetaux = new ArrayList<>();
        this.predateursAnimaux = new ArrayList<>();
    }

    /**
     * Méthode permettant à la sauterelle de se déplacer dans l'écosystème.
     * @param eco l'écosystème
     * @param i la coordonnée i de la zone
     * @param j la coordonnée j de la zone
     * @throws ZonePleineException si la zone dans laquelle la sauterelle doit se déplacer est déjà pleine
     */
    @Override
    public void seDeplacer(Ecosystem eco, int i, int j) throws ZonePleineException {
        moveAnimaux(eco, i, j, POURCENTAGE_DEPLACEMENT_PROIE, Sauterelle.class);
    }

    /**
     * Méthode permettant à la sauterelle de se reproduire avec un autre herbivore.
     * @param partenaire le partenaire de reproduction
     * @return une nouvelle instance de sauterelle
     */
    @Override
    public Sauterelle seReproduire(Herbivore partenaire) {
        return new Sauterelle(30);
    }

    /**
     * Méthode permettant à la sauterelle de se nourrir dans l'écosystème.
     * @param eco l'écosystème
     * @param i la coordonnée i de la zone
     * @param j la coordonnée j de la zone
     */
    @Override
    public void manger(Ecosystem eco, int i, int j) {
        // Récupérer la liste des végétaux dans la zone
        List<Vegetal> vegetaux = eco.getZone(i, j).getVegetaux();

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
            ajouterNourriture(vegetalTrouve); // Ajouter le végétal à la liste des nourritures de la sauterelle

            System.out.println("La sauterelle a consommé un végétal : " + vegetalTrouve.getNom());
        }
    }

    /**
     * Méthode permettant d'ajouter une nourriture (végétal) à la liste des proies végétales de la sauterelle.
     * @param nourriture le végétal à ajouter
     */
    public void ajouterNourriture(Vegetal nourriture) {
        this.proiesVegetales.add(nourriture);
    }

}

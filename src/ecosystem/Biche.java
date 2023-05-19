package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import view.Ecosystem;

/**
 * La classe Biche représente une biche dans l'écosystème.
 */
public class Biche extends Mammifere implements Herbivore {

    private List<Vegetal> proiesVegetales;

    /**
     * Constructeur de la classe Biche.
     * @param rayon le rayon d'action de la biche
     */
    public Biche(int rayon) {
        super(rayon, Color.CYAN);
        this.nom = "Biche";
        this.proiesAnimales = null;
        this.predateursVegetaux = null;
        this.proiesVegetales = new ArrayList<>();
        this.predateursAnimaux = new ArrayList<>();
    }

    /**
     * Méthode de consommation de nourriture de la biche.
     * La biche consomme un végétal dans la zone spécifiée si un végétal est trouvé.
     * @param eco l'écosystème
     * @param i la position i de la zone
     * @param j la position j de la zone
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
            ajouterNourriture(vegetalTrouve); // Ajouter le végétal à la liste des nourritures de la biche

            System.out.println("La biche a consommé un végétal : " + vegetalTrouve.getNom());
        }
    }

    /**
     * Méthode privée pour ajouter une nourriture à la liste des nourritures de la biche.
     * @param nourriture le végétal à ajouter comme nourriture
     */
    public void ajouterNourriture(Vegetal nourriture) {
        this.proiesVegetales.add(nourriture);
    }

    /**
     * Méthode de déplacement de la biche.
     * La biche se déplace dans l'écosystème en utilisant l'algorithme de déplacement des animaux proies.
     * @param eco l'écosystème
     * @param i la position i de la zone
     * @param j la position j de la zone
     * @throws ZonePleineException si la zone de destination est déjà pleine
     */
    @Override
    public void seDeplacer(Ecosystem eco, int i, int j) throws ZonePleineException{
        moveAnimaux(eco, i, j, POURCENTAGE_DEPLACEMENT_PROIE, Biche.class);
    }

    /**
     * Méthode de reproduction de la biche.
     * La biche se reproduit avec un partenaire herbivore et renvoie une nouvelle biche résultant de la reproduction, avec un rayon d'action de 30.
     * @param partenaire le partenaire herbivore avec qui se reproduire
     * @return une nouvelle biche résultant de la reproduction
     */
    @Override
    public Biche seReproduire(Herbivore partenaire) {
        return new Biche(30);
    }
}

package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import view.Ecosystem;

/**
 * La classe Chenille représente une chenille dans l'écosystème.
 */
public class Chenille extends Insecte implements Herbivore {

    /**
     * Constructeur de la classe Chenille.
     * @param rayon le rayon d'action de la chenille
     */
    public Chenille(int rayon) {
        super(rayon, Color.WHITE);
        this.nom = "Chenille";
        this.proiesAnimales = null;
        this.predateursVegetaux = null;
        this.proiesVegetales = new ArrayList<>();
        this.predateursAnimaux = new ArrayList<>();
    }

    /**
     * Méthode de déplacement de la chenille.
     * La chenille se déplace dans l'écosystème en utilisant l'algorithme de déplacement des animaux proies.
     * @param eco l'écosystème
     * @param i la position i de la zone
     * @param j la position j de la zone
     * @throws ZonePleineException si la zone de destination est déjà pleine
     */
    @Override
    public void seDeplacer(Ecosystem eco, int i, int j) throws ZonePleineException{
        moveAnimaux(eco, i, j, POURCENTAGE_DEPLACEMENT_PROIE, Chenille.class);
    }

    /**
     * Méthode de reproduction de la chenille.
     * @param partenaire le partenaire herbivore avec lequel se reproduire
     * @return une nouvelle chenille résultant de la reproduction
     */
    @Override
    public Chenille seReproduire(Herbivore partenaire) {
        return new Chenille(30);
    }

    /**
     * Méthode de consommation de nourriture de la chenille.
     * La chenille consomme un végétal dans la zone spécifiée si un végétal est trouvé.
     * @param eco l'écosystème
     * @param i la position i de la zone
     * @param j la position j de la zone
     */
    @Override
    public void manger(Ecosystem eco, int i, int j) {
        // Récupérer la liste des végétaux dans la zone
        List<Vegetal> vegetaux = eco.getZone(i, j).getVegetaux();

        // Si la liste des végétaux n'est pas vide, chercher un végétal à consommer
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
            ajouterNourriture(vegetalTrouve); // Ajouter le végétal à la liste des nourritures de la chenille

            System.out.println("La Chenille a consommé un végétal : " + vegetalTrouve.getNom());
        }
    }

    /**
     * Méthode privée pour ajouter une nourriture à la liste des nourritures de la chenille.
     * @param nourriture le végétal à ajouter comme nourriture
     */
    public void ajouterNourriture(Vegetal nourriture) {
        this.proiesVegetales.add(nourriture);
    }
}

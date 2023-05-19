package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import view.Ecosystem;

public class Biche extends Mammifere implements Herbivore {

    private List<Vegetal> proiesVegetales;
    //private List<Animal> predateursAnimaux;

    public Biche(int rayon) {
        super(rayon, Color.CYAN);
        this.nom = "Biche";
        this.proiesAnimales = null;
        this.predateursVegetaux = null;
        this.proiesVegetales = new ArrayList<>();
        this.predateursAnimaux = new ArrayList<>();
    }

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

    public void ajouterNourriture(Vegetal nourriture) {
        this.proiesVegetales.add(nourriture);
    }

    @Override
    public void seDeplacer(Ecosystem eco, int i, int j) throws ZonePleineException{
        moveAnimaux(eco, i, j, POURCENTAGE_DEPLACEMENT_PROIE, Biche.class);
    }

    @Override
    public Biche seReproduire(Herbivore partenaire) {
        return new Biche(30);
    }
}

package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import view.Ecosystem;

public class Sauterelle extends Insecte implements Herbivore {

    public Sauterelle(int rayon) {
        super(rayon, Color.BLACK);
        this.nom = "Sauterelle";
        this.proiesVegetales = new ArrayList<>();
        this.predateursVegetaux = new ArrayList<>();
        this.predateursAnimaux = new ArrayList<>();
    }

    @Override
    public void seDeplacer(Ecosystem eco, int i, int j) {
        moveAnimaux(eco, i, j, 25, Sauterelle.class);
    }

    @Override
    public Sauterelle seReproduire(Herbivore partenaire) {
        return new Sauterelle(30);
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
            ajouterNourriture(vegetalTrouve); // Ajouter le végétal à la liste des nourritures de la sauterelle

            System.out.println("La sauterelle a consommé un végétal : " + vegetalTrouve.getNom());
        }
    }

    public void ajouterNourriture(Vegetal nourriture) {
        this.proiesVegetales.add(nourriture);
    }

}

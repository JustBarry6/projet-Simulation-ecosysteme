package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import view.Ecosystem;

public class Chenille extends Insecte implements Herbivore {

    public Chenille(int rayon) {
        super(rayon, Color.WHITE);
        this.nom = "Chenille";
        this.proiesAnimales = null;
        this.predateursVegetaux = null;
        this.proiesVegetales = new ArrayList<>();
        this.predateursAnimaux = new ArrayList<>();
    }

    @Override
    public void seDeplacer(Ecosystem eco, int i, int j) {
        moveAnimaux(eco, i, j, 25, Chenille.class);
    }

    @Override
    public Chenille seReproduire(Herbivore partenaire) {
        return new Chenille(30);
    }

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

    public void ajouterNourriture(Vegetal nourriture) {
        this.proiesVegetales.add(nourriture);
    }
}

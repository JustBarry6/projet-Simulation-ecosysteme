package ecosystem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import view.Ecosystem;

public class Chenille extends Insecte implements Herbivore {

	public Chenille(int rayon) {
		super(rayon, Color.WHITE);
		this.nom = "chenille";
		this.proiesA = null;
		this.predateursV = null;
		this.proiesV = new ArrayList<Vegetal>();
		this.predateursA = new ArrayList<Animal>();

	}

	@Override
	public void seDeplacer(Ecosystem eco, int i, int j) {
		moveAnimaux(eco, i, j, 25, Chenille.class);
	}

//	@Override
//	public void boire() {
//		// implémentation de la méthode boire pourles Chenilles
//	}

	@Override
	public void mourir() {
		// implémentation de la méthode mourir pour les Chenilles
	}

	@Override
	public void seReproduire() {
		// implémentation de la méthode seReproduire pour les Chenilles
	}

	@Override
	public void manger(Ecosystem eco, int i, int j) {
		// Récupérer la liste des végétaux dans la zone
		List<Vegetal> vegetaux = eco.getZone(i, j).getVegetaux();

		// Si la liste des végétaux n'est pas vide, chercher un végétal à consommer
		Vegetal vegetalTrouve = null;
		if (!vegetaux.isEmpty()) {
			vegetalTrouve = vegetaux.get(0);
		}

		// Si un végétal a été trouvé, le consommer
		if (vegetalTrouve != null) {
			eco.getZone(i, j).removeVegetal(vegetalTrouve.getClass()); // Retirer le végétal de la zone
			ajouterNourriture(vegetalTrouve); // Ajouter le végétal à la liste des nourritures de la chenille

			System.out.println("La Chenille a consommé un végétal : " + vegetalTrouve.getNom());
		}

	}

	public void ajouterNourriture(Vegetal nourriture) {
		this.proiesV.add(nourriture);
	}

}

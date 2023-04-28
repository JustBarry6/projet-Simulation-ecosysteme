import java.awt.Color;
import java.util.Random;

public class MainNature {
	public static void main(String[] args) {
		int nbCasesL = 7, nbCasesH = 8;
		Ecosystem grille = new Ecosystem(nbCasesL, nbCasesH, 100);

		Random r = new Random();

		int p1 = 30; // Pourcentage de chance qu'un lapin soit placé dans une case
		int p2 = 10; // Pourcentage de chance qu'un aigle soit placé dans une case
		int p3 = 20; // Pourcentage de chance de reproduction des lapins
		int p4 = 20; // Pourcentage de chance de reproduction des aigles
		int p5 = 10; // Pourcentage de chance de prédation
		int p6 = 25; // Pourcentage de chance de déplacement des proies
		int p7 = 25; // Pourcentage de chance de déplacement des prédateurs
		int nbIterations = 100; // Nombre d'itérations de la simulation

		for (int i = 0; i < nbCasesL; i++) {
			for (int j = 0; j < nbCasesH; j++) {
				if (i < j)
					grille.colorieFond(i, j, Color.YELLOW);
				else
					grille.colorieFond(i, j, Color.BLUE);
			}
		}

		grille.redessine();

		// Pause de 2s
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < nbCasesL; i++) {
			for (int j = 0; j < nbCasesH; j++) {
				if (r.nextInt(100) < p1) {
					grille.addAnimal(i, j, r.nextInt(20) + 5, Color.GREEN); // Ajoute un lapin avec un rayon aléatoire
				}
				if (r.nextInt(100) < p2) {
					grille.addAnimal(i, j, r.nextInt(20) + 5, Color.RED); // Ajoute un aigle avec un rayon aléatoire
				}
				grille.redessine();
				// Pause de 2s
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		// Boucle de simulation
		for (int iteration = 0; iteration < nbIterations; iteration++) {
			// Reproduction des proies
			for (int i = 0; i < nbCasesL; i++) {
				for (int j = 0; j < nbCasesH; j++) {
					Zone zone = grille.getZone(i, j);
					int nbLapins = zone.getNbAnimaux(Color.GREEN);
					if (nbLapins >= 2 && r.nextInt(100) < p3) {
						grille.addAnimal(i, j, r.nextInt(20) + 5, Color.GREEN); // Ajoute un lapin avec un poids aléatoire
					}
				}
			}

			// Reproduction des prédateurs
			for (int i = 0; i < nbCasesL; i++) {
				for (int j = 0; j < nbCasesH; j++) {
					Zone zone = grille.getZone(i, j);
					int nbLapins = zone.getNbAnimaux(Color.GREEN);
					int nbAigles = zone.getNbAnimaux(Color.RED);
					if (nbLapins > 0 && nbAigles >= 2 && r.nextInt(100) < p4) {
						grille.addAnimal(i, j, r.nextInt(20) + 5, Color.RED); // Ajoute un lapin avec un poids aléatoire
					}
				}
			}

			// Prédation
			for (int i = 0; i < nbCasesL; i++) {
				for (int j = 0; j < nbCasesH; j++) {
					Zone zone = grille.getZone(i, j);
					int nbLapins = zone.getNbAnimaux(Color.GREEN);
					int nbAigles = zone.getNbAnimaux(Color.RED);
					for (int k = 0; k < nbAigles; k++) {
						if (r.nextInt(100) < p5 && nbLapins > 0) {
							// Suppression d'un lapin de la zone
							grille.removeAnimal(i, j, Color.GREEN);
							// Réduction du nombre de lapins
							nbLapins--;
						}
					}
				}
			}

			// Déplacement des proies
			for (int i = 0; i < nbCasesL; i++) {
				for (int j = 0; j < nbCasesH; j++) {
					grille.moveProies(i, j);
				}
			}

			// Déplacement des prédateurs
			for (int i = 0; i < nbCasesL; i++) {
				for (int j = 0; j < nbCasesH; j++) {
					grille.movePredateurs(i, j);
				}
			}

			grille.redessine();

			// Pause entre les itérations
			try {
				Thread.sleep(500); // 1 seconde de pause
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

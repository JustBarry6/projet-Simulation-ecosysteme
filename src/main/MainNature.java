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

		initialiserGrille(grille, nbCasesL, nbCasesH);

		// Pause de 2s
		pause(1000);

		placerAnimauxInitiaux(grille, p1, p2, r);

		// Pause de 2s
		pause(1000);

		// Boucle de simulation
		for (int iteration = 0; iteration < nbIterations; iteration++) {
			reproductionProies(grille, p3, r);
			reproductionPredateurs(grille, p4, r);
			predation(grille, p5, r);
			deplacementProies(grille, p6, r);
			deplacementPredateurs(grille, p7, r);

			grille.redessine();

			// Pause entre les itérations
			pause(100);
		}
	}

	private static void initialiserGrille(Ecosystem grille, int nbCasesL, int nbCasesH) {
		for (int i = 0; i < nbCasesL; i++) {
			for (int j = 0; j < nbCasesH; j++) {
				if (i < j)
					grille.colorieFond(i, j, Color.YELLOW);
				else
					grille.colorieFond(i, j, Color.WHITE);
			}
		}
	}
	

	private static void placerAnimauxInitiaux(Ecosystem grille, int p1, int p2, Random r) {
        int nbCasesL = grille.getNbCasesL();
        int nbCasesH = grille.getNbCasesH();

        for (int i = 0; i < nbCasesL; i++) {
            for (int j = 0; j < nbCasesH; j++) {
                if (r.nextInt(100) < p1) {
                    grille.addAnimal(i, j, new Sauterelle(r.nextInt(20) + 5)); // Ajoute une sauterelle avec un rayon aléatoire
                }
                if (r.nextInt(100) < p2) {
                    grille.addAnimal(i, j, new Aigle(r.nextInt(20) + 5)); // Ajoute un aigle avec un rayon aléatoire
                }
                grille.redessine();
                // Pause de 2s
                pause(100);
            }
        }
	}

	private static void reproductionProies(Ecosystem grille, int p3, Random r) {
		int nbCasesL = grille.getNbCasesL();
		int nbCasesH = grille.getNbCasesH();

		for (int i = 0; i < nbCasesL; i++) {
			for (int j = 0; j < nbCasesH; j++) {
				Zone zone = grille.getZone(i, j);
				int nbSauterelle = zone.getNbAnimal(Color.BLACK);
				if (nbSauterelle >= 2 && r.nextInt(100) < p3) {
					grille.addAnimal(i, j, new Sauterelle(r.nextInt(20) + 5)); // Ajoute une sauterelle avec un poids
																				// aléatoire
				}
			}
		}
	}

	private static void reproductionPredateurs(Ecosystem grille, int p4, Random r) {
		int nbCasesL = grille.getNbCasesL();
		int nbCasesH = grille.getNbCasesH();

		for (int i = 0; i < nbCasesL; i++) {
			for (int j = 0; j < nbCasesH; j++) {
				Zone zone = grille.getZone(i, j);
				int nbSauterelle = zone.getNbAnimal(Color.BLACK);
				int nbAigles = zone.getNbAnimal(Color.PINK);
				if (nbSauterelle > 0 && nbAigles >= 2 && r.nextInt(100) < p4) {
					grille.addAnimal(i, j, new Aigle(r.nextInt(20) + 5)); // Ajoute un aigle avec un poids aléatoire
				}
			}
		}
	}

	private static void predation(Ecosystem grille, int p5, Random r) {
		int nbCasesL = grille.getNbCasesL();
		int nbCasesH = grille.getNbCasesH();

		for (int i = 0; i < nbCasesL; i++) {
			for (int j = 0; j < nbCasesH; j++) {
				Zone zone = grille.getZone(i, j);
				int nbSauterelle = zone.getNbAnimal(Color.BLACK);
				int nbAigles = zone.getNbAnimal(Color.PINK);
				for (int k = 0; k < nbAigles; k++) {
					if (r.nextInt(100) < p5 && nbSauterelle > 0) {
						// Suppression d'une sauterelle de la zone
						grille.removeAnimal(i, j, Color.BLACK);
						// Réduction du nombre de sauterelles
						nbSauterelle--;
					}
				}
			}
		}
	}

	private static void deplacementProies(Ecosystem grille, int p6, Random r) {
	    deplacementAnimaux(grille, p6, r, Color.BLACK);
	}

	private static void deplacementPredateurs(Ecosystem grille, int p7, Random r) {
	    deplacementAnimaux(grille, p7, r, Color.PINK);
	}

	private static void deplacementAnimaux(Ecosystem grille, int p, Random r, Color couleur) {
	    int nbCasesL = grille.getNbCasesL();
	    int nbCasesH = grille.getNbCasesH();

	    for (int i = 0; i < nbCasesL; i++) {
	        for (int j = 0; j < nbCasesH; j++) {
	            grille.moveAnimaux(i, j, p, r, couleur);
	        }
	    }
	}


	private static void pause(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

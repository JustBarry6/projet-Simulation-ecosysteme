import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainNature {
	
	static int p1 = 30; // Pourcentage de chance qu'un ... soit placé dans une case
	static int p2 = 10; // Pourcentage de chance qu'un ... soit placé dans une case
	static int p3 = 20; // Pourcentage de chance de reproduction des ...
	static int p4 = 20; // Pourcentage de chance de reproduction des ...
	static int p5 = 10; // Pourcentage de chance de prédation
	static int p6 = 25; // Pourcentage de chance de déplacement des proies
	static int p7 = 25; // Pourcentage de chance de déplacement des prédateurs
	public static void main(String[] args) {
		int nbCasesL = 7, nbCasesH = 8;
		Ecosystem grille = new Ecosystem(nbCasesL, nbCasesH, 100);

		Random r = new Random();

		int nbIterations = 100; // Nombre d'itérations de la simulation

		initialiserGrille(grille, nbCasesL, nbCasesH);

		// Pause de 2s
		pause(1000);

		placerAnimauxInitiaux(grille, r);

		// Pause de 2s
		pause(1000);

		// Boucle de simulation
		for (int iteration = 0; iteration < nbIterations; iteration++) {
			deplacementAnimaux(grille, r);
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
	

	private static void placerAnimauxInitiaux(Ecosystem grille, Random r) {
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


	private static void deplacementAnimaux(Ecosystem grille, Random r) {
	    int nbCasesL = grille.getNbCasesL();
	    int nbCasesH = grille.getNbCasesH();
	    
	    for (int i = 0; i < nbCasesL; i++) {
	        for (int j = 0; j < nbCasesH; j++) {
	            Zone zone = grille.getZone(i, j);
	            List<Animal> animaux = new ArrayList<>(zone.getAnimaux()); // Créer une copie de la liste d'animaux
	            
	            for (Animal animal : animaux) {
	                if (animal.getType() == TypeAnimal.PREDATEUR) {
	                    grille.moveAnimaux(i, j, p7, r, animal);
	                } else if (animal.getType() == TypeAnimal.PROIE) {
	                    grille.moveAnimaux(i, j, p6, r, animal);
	                }
	            }
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

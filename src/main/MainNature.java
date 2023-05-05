package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ecosystem.Aigle;
import ecosystem.Animal;
import ecosystem.Lion;
import ecosystem.Pigeon;
import ecosystem.Sauterelle;
import ecosystem.Zone;
import view.Ecosystem;

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
		Ecosystem ecosystem = new Ecosystem(nbCasesL, nbCasesH, 100);

		Random r = new Random();

		int nbIterations = 100; // Nombre d'itérations de la simulation

		initialiserecosystem(ecosystem, nbCasesL, nbCasesH);

		// Pause de 2s
		pause(1000);

		placerAnimauxInitiaux(ecosystem, r);

		// Pause de 2s
		pause(1000);

		// Boucle de simulation
		for (int iteration = 0; iteration < nbIterations; iteration++) {
			deplacementAnimaux(ecosystem);
			ecosystem.redessine();

			// Pause entre les itérations
			pause(100);
		}
	}

	private static void initialiserecosystem(Ecosystem ecosystem, int nbCasesL, int nbCasesH) {
		for (int i = 0; i < nbCasesL; i++) {
			for (int j = 0; j < nbCasesH; j++) {
				if (i < j)
					ecosystem.colorieFond(i, j, Color.YELLOW);
				else
					ecosystem.colorieFond(i, j, Color.WHITE);
			}
		}
	}
	

	private static void placerAnimauxInitiaux(Ecosystem ecosystem, Random r) {
        int nbCasesL = ecosystem.getNbCasesL();
        int nbCasesH = ecosystem.getNbCasesH();

        for (int i = 0; i < nbCasesL; i++) {
            for (int j = 0; j < nbCasesH; j++) {
                if (r.nextInt(100) < p1) {
                    ecosystem.addAnimal(i, j, new Sauterelle(r.nextInt(20) + 5)); // Ajoute une sauterelle avec un rayon aléatoire
                }
                if (r.nextInt(100) < p1) {
                    ecosystem.addAnimal(i, j, new Pigeon(r.nextInt(20) + 5)); // Ajoute une sauterelle avec un rayon aléatoire
                }
                if (r.nextInt(100) < p2) {
                    ecosystem.addAnimal(i, j, new Aigle(r.nextInt(20) + 5)); // Ajoute un aigle avec un rayon aléatoire                    
                }
                if (r.nextInt(100) < p2) {
                    ecosystem.addAnimal(i, j, new Lion(r.nextInt(20) + 5)); // Ajoute un aigle avec un rayon aléatoire                    
                }
                ecosystem.redessine();
                // Pause de 2s
                pause(100);
            }
        }
	}
	
	private static void deplacementAnimaux(Ecosystem ecosystem) {
	    int nbCasesL = ecosystem.getNbCasesL();
	    int nbCasesH = ecosystem.getNbCasesH();
	    
	    for (int i = 0; i < nbCasesL; i++) {
	        for (int j = 0; j < nbCasesH; j++) {
	            Zone zone = ecosystem.getZone(i, j);
	            List<Animal> animaux = new ArrayList<>(zone.getAnimaux()); // Créer une copie de la liste d'animaux
	            
	            for (Animal animal : animaux) {
	                if (animal instanceof Aigle) {
	                    ((Aigle) animal).seDeplacer(ecosystem, i, j);
	                } else if (animal instanceof Sauterelle) {
	                    ((Sauterelle) animal).seDeplacer(ecosystem, i, j);
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
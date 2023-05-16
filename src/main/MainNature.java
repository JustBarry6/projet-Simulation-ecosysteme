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
import ecosystem.Vegetal;
import ecosystem.Biche;
import ecosystem.Vivace;
import ecosystem.Chenille;
import ecosystem.Arbre;
import ecosystem.Zone;
import view.Ecosystem;

public class MainNature {

	static int p1 = 30; // Pourcentage de chance qu'une proie soit placée dans une case
	static int p2 = 10; // Pourcentage de chance qu'un predateur soit placé dans une case
	static int p3 = 15; // Pourcentage de chance de reproduction des ...
	static int p4 = 15; // Pourcentage de chance de reproduction des ...
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

		placerAnimauxInitiaux(ecosystem, r); // changer le nom de cette fonction puisqu'elle place aussi des Vegetaux

		// Pause de 2s
		pause(1000);

		// Boucle de simulation
		for (int iteration = 0; iteration < nbIterations; iteration++) {
			deplacementAnimaux(ecosystem);
			nutritionAnimauxVegetaux(ecosystem) ; 
			ecosystem.redessine();

			// Pause entre les itérations
			pause(200);
		}
	}

	private static void initialiserecosystem(Ecosystem ecosystem, int nbCasesL, int nbCasesH) {
		for (int i = 0; i < nbCasesL; i++) {
			for (int j = 0; j < nbCasesH; j++) {
				if (i < j)
					ecosystem.colorieFond(i, j,ecosystem.getZone(i, j).getCouleur());
				else
					ecosystem.colorieFond(i, j,ecosystem.getZone(i, j).getCouleur());
			}
		}
	}

	private static void placerAnimauxInitiaux(Ecosystem ecosystem, Random r) {
		int nbCasesL = ecosystem.getNbCasesL();
		int nbCasesH = ecosystem.getNbCasesH();

		// ! Ici on place une instance de chaque animal par case en fonction des
		// pourcentages
		// ! Il faudra determiner une certaine quantité "faible" d'instance d'animaux
		// qu'on peut placer
		// Comment définir une proie et un predateur; sachant que certains animaux sont
		// proies et prédateurs à la fois
		for (int i = 0; i < nbCasesL; i++) {
			for (int j = 0; j < nbCasesH; j++) {
				if (r.nextInt(100) < p1) {
					ecosystem.addAnimal(i, j, new Sauterelle(15)); 
				}
				if (r.nextInt(100) < p1) {
					ecosystem.addAnimal(i, j, new Pigeon(15)); 
				}
				if (r.nextInt(100) < p2) {
					ecosystem.addAnimal(i, j, new Aigle(15)); 
				}
				if (r.nextInt(100) < p2) {
					ecosystem.addAnimal(i, j, new Lion(15));
				}
				if (r.nextInt(100) < p1) {
					ecosystem.addAnimal(i, j, new Biche(15)); 
				}
				if (r.nextInt(100) < p1) {
					ecosystem.addAnimal(i, j, new Chenille(15)); 
				}
				if (r.nextInt(100) < p1) {
					ecosystem.addVegetal(i, j, new Arbre()); 		//seuil ? temp ? pas rayon ?	: à voir
				}
				if (r.nextInt(100) < p1) {
					ecosystem.addVegetal(i, j, new Vivace());	//seuil ? temp ? pas rayon ?		à voir 											// aléatoire
				}
				ecosystem.redessine();
				// Pause de 2s
				pause(200);
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
					animal.seDeplacer(ecosystem, i, j);
//					animal.manger(ecosystem, i, j);
				}
//				zone.changementZone() ; 
				ecosystem.update(i, j);
			}
		}
	}
	
	private static void nutritionAnimauxVegetaux(Ecosystem ecosystem) {
		int nbCasesL = ecosystem.getNbCasesL();
		int nbCasesH = ecosystem.getNbCasesH();

		for (int i = 0; i < nbCasesL; i++) {
			for (int j = 0; j < nbCasesH; j++) {
				Zone zone = ecosystem.getZone(i, j);
				List<Animal> animaux = new ArrayList<>(zone.getAnimaux()); // Créer une copie de la liste d'animaux
				List<Vegetal> vegetaux = new ArrayList<>(zone.getVegetaux()) ; 
				
				for (Animal animal : animaux) {
					animal.manger(ecosystem, i, j);
					animal.boire(zone);
					zone.changementZone() ; 
				}
				for (Vegetal V : vegetaux) {
					V.consommerEau(zone);
					zone.changementZone() ; 
				}
				ecosystem.update(i, j);
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
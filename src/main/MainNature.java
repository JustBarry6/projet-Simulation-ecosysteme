package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ecosystem.Aigle;
import ecosystem.Animal;
import ecosystem.Arbre;
import ecosystem.Biche;
import ecosystem.Chenille;
import ecosystem.Lion;
import ecosystem.Pigeon;
import ecosystem.Sauterelle;
import ecosystem.Vegetal;
import ecosystem.Vivace;
import ecosystem.Zone;
import view.Ecosystem;

public class MainNature {

	private static final int POURCENTAGE_PROIE = 30;
	private static final int POURCENTAGE_PREDATEUR = 10;
	private static final int POURCENTAGE_PREDATION = 10;
	private static final int POURCENTAGE_DEPLACEMENT_PROIE = 25;
	private static final int POURCENTAGE_DEPLACEMENT_PREDATEUR = 25;

	public static void main(String[] args) {
		int nbCasesL = 7, nbCasesH = 8;
		Ecosystem ecosystem = new Ecosystem(nbCasesL, nbCasesH, 100);

		Random r = new Random();

		int nbIterations = 100; // Nombre d'itérations de la simulation

		initialiserEcosystem(ecosystem, nbCasesL, nbCasesH);

		// Pause de 2s
		pause(1000);

		placerAnimauxEtVegetauxInitiaux(ecosystem, r);

		// Pause de 2s
		pause(1000);

		// Boucle de simulation
		for (int iteration = 0; iteration < nbIterations; iteration++) {
			deplacementAnimaux(ecosystem);
			nutritionAnimauxVegetaux(ecosystem);
			vieillissementCollectif(ecosystem);
			checkEsperanceDeVie(ecosystem);
			ecosystem.redessine();

			// Pause entre les itérations
			pause(200);
		}
	}

	private static void initialiserEcosystem(Ecosystem ecosystem, int nbCasesL, int nbCasesH) {
		for (int i = 0; i < nbCasesL; i++) {
			for (int j = 0; j < nbCasesH; j++) {
				if (i < j)
					ecosystem.colorieFond(i, j, ecosystem.getZone(i, j).getCouleur());
				else
					ecosystem.colorieFond(i, j, ecosystem.getZone(i, j).getCouleur());
			}
		}
	}

	private static void placerAnimauxEtVegetauxInitiaux(Ecosystem ecosystem, Random random) {
		int nbCasesL = ecosystem.getNbCasesL();
		int nbCasesH = ecosystem.getNbCasesH();

		for (int i = 0; i < nbCasesL; i++) {
			for (int j = 0; j < nbCasesH; j++) {
				placerAnimalSelonPourcentage(ecosystem, random, i, j, new Sauterelle(30), POURCENTAGE_PROIE);
				placerAnimalSelonPourcentage(ecosystem, random, i, j, new Pigeon(30), POURCENTAGE_PROIE);
				placerAnimalSelonPourcentage(ecosystem, random, i, j, new Aigle(30), POURCENTAGE_PREDATEUR);
				placerAnimalSelonPourcentage(ecosystem, random, i, j, new Lion(30), POURCENTAGE_PREDATEUR);
				placerAnimalSelonPourcentage(ecosystem, random, i, j, new Biche(30), POURCENTAGE_PROIE);
				placerAnimalSelonPourcentage(ecosystem, random, i, j, new Chenille(30), POURCENTAGE_PROIE);
				placerVegetalSelonPourcentage(ecosystem, random, i, j, new Arbre(), POURCENTAGE_PROIE);
				placerVegetalSelonPourcentage(ecosystem, random, i, j, new Vivace(), POURCENTAGE_PROIE);

				ecosystem.redessine();
				pause(200);
			}
		}
	}

	public static void placerAnimalSelonPourcentage(Ecosystem ecosystem, Random random, int x, int y, Animal animal,
			double pourcentage) {
		if (random.nextInt(101) < pourcentage) {
			ecosystem.placerAnimal(x, y, animal);
		}
	}

	public static void placerVegetalSelonPourcentage(Ecosystem ecosystem, Random random, int x, int y, Vegetal vegetal,
			double pourcentage) {
		if (random.nextInt(101) < pourcentage) {
			ecosystem.placerVegetal(x, y, vegetal);
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
					animal.utiliserEau(); // Le déplacement entraîne la baisse de la réserve d'eau
				}

				ecosystem.updateAnimaux(i, j);
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
				List<Vegetal> vegetaux = new ArrayList<>(zone.getVegetaux());

				for (Animal animal : animaux) {
					animal.manger(ecosystem, i, j);
					animal.boire(zone);
					zone.changementZone();
				}

				for (Vegetal vegetal : vegetaux) {
					vegetal.consommerEau(zone);
					zone.changementZone();
				}

				ecosystem.mettreAJourAnimaux(i, j);
			}
		}
	}

	private static void checkEsperanceDeVie(Ecosystem ecosystem) {
		int nbCasesL = ecosystem.getNbCasesL();
		int nbCasesH = ecosystem.getNbCasesH();

		for (int i = 0; i < nbCasesL; i++) {
			for (int j = 0; j < nbCasesH; j++) {
				Zone zone = ecosystem.getZone(i, j);
				List<Animal> animaux = new ArrayList<>(zone.getAnimaux()); // Créer une copie de la liste d'animaux
				List<Vegetal> vegetaux = new ArrayList<>(zone.getVegetaux());

				for (Animal animal : animaux) {
					if (animal.getAge() >= animal.getEsperanceDeVie())
						animal.mourir(zone);
				}
				for (Vegetal V : vegetaux) {
					if (V.getAge() >= V.getEsperanceDeVie())
						V.mourir(zone);
				}
				ecosystem.mettreAJourAnimaux(i, j);
			}
		}
	}

	public static void vieillissementCollectif(Ecosystem ecosystem) {
		int nbCasesL = ecosystem.getNbCasesL();
		int nbCasesH = ecosystem.getNbCasesH();

		for (int i = 0; i < nbCasesL; i++) {
			for (int j = 0; j < nbCasesH; j++) {
				Zone zone = ecosystem.getZone(i, j);
				List<Animal> animaux = new ArrayList<>(zone.getAnimaux()); // Créer une copie de la liste d'animaux
				List<Vegetal> vegetaux = new ArrayList<>(zone.getVegetaux());

				for (Animal animal : animaux) {
					animal.vieillir();
				}
				for (Vegetal V : vegetaux) {
					V.vieillir();
				}
				ecosystem.mettreAJourAnimaux(i, j);
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

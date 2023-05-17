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
	private static final int POURCENTAGE_REPRODUCTION_PROIE = 15;
	private static final int POURCENTAGE_REPRODUCTION_PREDATEUR = 15;
	private static final int POURCENTAGE_PREDATION = 10;
	private static final int POURCENTAGE_DEPLACEMENT_PROIE = 25;
	private static final int POURCENTAGE_DEPLACEMENT_PREDATEUR = 25;

	public static void main(String[] args) {
		int nbCasesL = 7, nbCasesH = 8;
		Ecosystem ecosystem = new Ecosystem(nbCasesL, nbCasesH, 130);

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
			nutritionAnimauxVegetaux(ecosystem);
			vieillissementCollectif(ecosystem) ; 
			checkEsperanceDeVie(ecosystem);
			ecosystem.redessine();

			// Pause entre les itérations
			pause(200);
		}
	}

	private static void initialiserecosystem(Ecosystem ecosystem, int nbCasesL, int nbCasesH) {
		for (int i = 0; i < nbCasesL; i++) {
			for (int j = 0; j < nbCasesH; j++) {
				if (i < j)
					ecosystem.colorieFond(i, j, ecosystem.getZone(i, j).getCouleur());
				else
					ecosystem.colorieFond(i, j, ecosystem.getZone(i, j).getCouleur());
			}
		}
	}

	private static void placerAnimauxInitiaux(Ecosystem ecosystem, Random random) {
		int nbCasesL = ecosystem.getNbCasesL();
		int nbCasesH = ecosystem.getNbCasesH();

		for (int i = 0; i < nbCasesL; i++) {
			for (int j = 0; j < nbCasesH; j++) {
				placerAnimalSelonPourcentage(ecosystem, random, i, j, new Sauterelle(15), POURCENTAGE_PROIE);
				placerAnimalSelonPourcentage(ecosystem, random, i, j, new Pigeon(15), POURCENTAGE_PROIE);
				placerAnimalSelonPourcentage(ecosystem, random, i, j, new Aigle(15), POURCENTAGE_PREDATEUR);
				placerAnimalSelonPourcentage(ecosystem, random, i, j, new Lion(15), POURCENTAGE_PREDATEUR);
				placerAnimalSelonPourcentage(ecosystem, random, i, j, new Biche(15), POURCENTAGE_PROIE);
				placerAnimalSelonPourcentage(ecosystem, random, i, j, new Chenille(15), POURCENTAGE_PROIE);
				placerVegetalSelonPourcentage(ecosystem, random, i, j, new Arbre(), POURCENTAGE_PROIE);
				placerVegetalSelonPourcentage(ecosystem, random, i, j, new Vivace(), POURCENTAGE_PROIE);

				ecosystem.redessine();
				pause(200);
			}
		}
	}

	public static void placerAnimalSelonPourcentage(Ecosystem ecosysteme, Random random, int x, int y, Animal animal,
			double pourcentage) {
		if (random.nextInt(101) < pourcentage) {
			ecosysteme.placerAnimal(x, y, animal);
		}
	}

	public static void placerVegetalSelonPourcentage(Ecosystem ecosysteme, Random random, int x, int y, Vegetal vegetal,
			double pourcentage) {
		if (random.nextInt(101) < pourcentage) {
			ecosysteme.placerVegetal(x, y, vegetal);
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
					animal.utiliserEau(); // Le deplacement entraine la baisse de la reserve d'eau
					// animal.manger(ecosystem, i, j);

				}
				// zone.changementZone() ;
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
				List<Vegetal> vegetaux = new ArrayList<>(zone.getVegetaux());

				for (Animal animal : animaux) {
					animal.manger(ecosystem, i, j);
					animal.boire(zone);
					zone.changementZone();
				}
				for (Vegetal V : vegetaux) {
					V.consommerEau(zone);
					zone.changementZone();
				}
				ecosystem.update(i, j);
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
				ecosystem.update(i, j);
			}
		}
	}
	
	public static void vieillissementCollectif(Ecosystem ecosystem)
	{
		int nbCasesL = ecosystem.getNbCasesL();
		int nbCasesH = ecosystem.getNbCasesH();

		for (int i = 0; i < nbCasesL; i++) {
			for (int j = 0; j < nbCasesH; j++) {
				Zone zone = ecosystem.getZone(i, j);
				List<Animal> animaux = new ArrayList<>(zone.getAnimaux()); // Créer une copie de la liste d'animaux
				List<Vegetal> vegetaux = new ArrayList<>(zone.getVegetaux());

				for (Animal animal : animaux) {
						animal.vieillir() ; 
				}
				for (Vegetal V : vegetaux) {
						V.vieillir() ; 
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
package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ecosystem.Aigle;
import ecosystem.Animal;
import ecosystem.AnimalVolant;
import ecosystem.Biche;
import ecosystem.Chene;
import ecosystem.Chenille;
import ecosystem.Iris;
import ecosystem.Lion;
import ecosystem.NoWaterException;
import ecosystem.Pigeon;
import ecosystem.Pivoine;
import ecosystem.Sauterelle;
import ecosystem.TypeZone;
import ecosystem.Vegetal;
import ecosystem.Zone;
import ecosystem.ZonePleineException;
import view.Ecosystem;

public class MainNature {

	private static final int POURCENTAGE_PROIE = 30;  // Valeur initiale = 30
	private static final int POURCENTAGE_PREDATEUR = 10; // Valeur initiale = 10

	/**
	 * Méthode principale du programme.
	 *
	 * @param args Les arguments de ligne de commande (non utilisés)
	 */
	public static void main(String[] args) {
		int nbCasesL = 7, nbCasesH = 8;
		Ecosystem ecosystem = new Ecosystem(nbCasesL, nbCasesH, 100);

		Random r = new Random();

		int nbIterations = 100; // Nombre d'itérations de la simulation

		initialiserEcosystem(ecosystem, nbCasesL, nbCasesH);

		// Pause de 2s
		pause(100);

		placerAnimauxEtVegetauxInitiaux(ecosystem, r);

		// Pause de 2s
		pause(100);

		// Boucle de simulation
		for (int iteration = 0; iteration < nbIterations; iteration++) {
			deplacementAnimaux(ecosystem);
			checkConditionsTemperatureEau(ecosystem);
			nutritionAnimauxVegetaux(ecosystem);
			vieillissementCollectif(ecosystem);
			checkEsperanceDeVie(ecosystem);
			ecosystem.redessine();

			// Pause entre les itérations
			pause(200);
		}
	}

	/**
	 * Initialise les couleurs de fond des zones de l'écosystème.
	 *
	 * @param ecosystem L'écosystème à initialiser
	 * @param nbCasesL  Le nombre de cases en largeur
	 * @param nbCasesH  Le nombre de cases en hauteur
	 */
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

	/**
	 * Place les animaux et végétaux initiaux dans l'écosystème.
	 *
	 * @param ecosystem L'écosystème
	 * @param random    Objet Random pour la génération aléatoire
	 */

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
				placerVegetalSelonPourcentage(ecosystem, random, i, j, new Chene(), POURCENTAGE_PROIE);
				placerVegetalSelonPourcentage(ecosystem, random, i, j, new Pivoine(), POURCENTAGE_PROIE);
				placerVegetalSelonPourcentage(ecosystem, random, i, j, new Iris(), POURCENTAGE_PROIE);

				ecosystem.redessine();
				pause(200);
			}
		}
	}

	/**
	 * Place un animal dans une zone selon un pourcentage donné.
	 *
	 * @param ecosystem   L'écosystème
	 * @param random      Objet Random pour la génération aléatoire
	 * @param x           La position en largeur de la zone
	 * @param y           La position en hauteur de la zone
	 * @param animal      L'animal à placer
	 * @param pourcentage Le pourcentage de chance de placer l'animal
	 */
	public static void placerAnimalSelonPourcentage(Ecosystem ecosystem, Random random, int x, int y, Animal animal,
			double pourcentage) {
		if (random.nextInt(101) < pourcentage) {
			try {
				ecosystem.placerAnimal(x, y, animal);
			} catch (ZonePleineException e) {
				// Gérer l'exception lorsque la zone est pleine
				e.printStackTrace();
			}
		}
	}

	/**
	 * Place un végétal dans une zone selon un pourcentage donné.
	 *
	 * @param ecosystem   L'écosystème
	 * @param random      Objet Random pour la génération aléatoire
	 * @param x           La position en largeur de la zone
	 * @param y           La position en hauteur de la zone
	 * @param vegetal     Le végétal à placer
	 * @param pourcentage Le pourcentage de chance de placer le végétal
	 */
	public static void placerVegetalSelonPourcentage(Ecosystem ecosystem, Random random, int x, int y, Vegetal vegetal,
			double pourcentage) {
		if (random.nextInt(101) < pourcentage) {
			ecosystem.placerVegetal(x, y, vegetal);
		}
	}

	/**
	 * Effectue le déplacement des animaux dans l'écosystème.
	 *
	 * @param ecosystem L'écosystème
	 */
	private static void deplacementAnimaux(Ecosystem ecosystem) {
		int nbCasesL = ecosystem.getNbCasesL();
		int nbCasesH = ecosystem.getNbCasesH();

		for (int i = 0; i < nbCasesL; i++) {
			for (int j = 0; j < nbCasesH; j++) {
				Zone zone = ecosystem.getZone(i, j);
				List<Animal> animaux = new ArrayList<>(zone.getAnimaux()); // Crée une copie de la liste d'animaux

				for (Animal animal : animaux) {
					try {
						animal.seDeplacer(ecosystem, i, j);
					} catch (ZonePleineException e) {
						// Gérer l'exception lorsque la zone est pleine
						e.printStackTrace();
					}
					animal.utiliserEau(); // Le déplacement entraîne la baisse de la réserve d'eau
					if (animal instanceof AnimalVolant) {
						((AnimalVolant) animal).voler();
					}
				}

				ecosystem.updateAnimaux(i, j);
			}
		}
	}

	/**
	 * Gère la nutrition des animaux et des végétaux dans l'écosystème.
	 *
	 * @param ecosystem L'écosystème
	 */
	private static void nutritionAnimauxVegetaux(Ecosystem ecosystem) {
	    int nbCasesL = ecosystem.getNbCasesL();
	    int nbCasesH = ecosystem.getNbCasesH();

	    for (int i = 0; i < nbCasesL; i++) {
	        for (int j = 0; j < nbCasesH; j++) {
	            Zone zone = ecosystem.getZone(i, j);
	            List<Animal> animaux = new ArrayList<>(zone.getAnimaux()); // Créer une copie de la liste d'animaux
	            List<Vegetal> vegetaux = new ArrayList<>(zone.getVegetaux());

	            for (Animal animal : animaux) {
	                try {
	                    animal.manger(ecosystem, i, j);
	                    animal.boire(zone);
	                    zone.changementZone();
	                } catch (NoWaterException e) {
	                    // Gérer l'exception lorsqu'il n'y a pas assez d'eau
	                    System.out.println("Plus d'eau disponible dans la zone !");
	                }
	            }

	            for (Vegetal vegetal : vegetaux) {
	                try {
	                    vegetal.consommerEau(zone);
	                    zone.changementZone();
	                } catch (NoWaterException e) {
	                    // Gérer l'exception lorsqu'il n'y a pas assez d'eau
	                    System.out.println("Plus d'eau disponible dans la zone !");
	                }
	            }

	            ecosystem.mettreAJourAnimaux(i, j);
	        }
	    }
	}


	/**
	 * Vérifie si l'espérance de vie des animaux et des végétaux est atteinte.
	 * Les animaux ou les végétaux dont l'âge est supérieur ou égal à l'espérance de vie meurent.
	 *
	 * @param ecosystem L'écosystème
	 */
	private static void checkEsperanceDeVie(Ecosystem ecosystem) {
		int nbCasesL = ecosystem.getNbCasesL();
		int nbCasesH = ecosystem.getNbCasesH();

		for (int i = 0; i < nbCasesL; i++) {
			for (int j = 0; j < nbCasesH; j++) {
				Zone zone = ecosystem.getZone(i, j);
				List<Animal> animaux = new ArrayList<>(zone.getAnimaux()); // Crée une copie de la liste d'animaux
				List<Vegetal> vegetaux = new ArrayList<>(zone.getVegetaux());

				for (Animal animal : animaux) {
					if (animal.getAge() >= animal.getEsperanceDeVie()) {
						animal.mourir(zone);
						System.out.println("ESPERANCE DE VIE ANIMALE ATTEINTE\n");
					}

				}
				for (Vegetal vegetal : vegetaux) {
					if (vegetal.getAge() >= vegetal.getEsperanceDeVie()) {
						vegetal.mourir(zone);
						System.out.println("ESPERANCE DE VIE VEGETALE ATTEINTE\n");
					}
				}
				ecosystem.mettreAJourAnimaux(i, j);
			}
		}
	}

	/**
	 * Vérifie si les conditions de température et d'eau sont adéquates pour les animaux et les végétaux.
	 * Les animaux ou les végétaux dont les conditions ne sont pas respectées meurent.
	 *
	 * @param ecosystem L'écosystème
	 */
	private static void checkConditionsTemperatureEau(Ecosystem ecosystem) {
		int nbCasesL = ecosystem.getNbCasesL();
		int nbCasesH = ecosystem.getNbCasesH();

		for (int i = 0; i < nbCasesL; i++) {
			for (int j = 0; j < nbCasesH; j++) {
				Zone zone = ecosystem.getZone(i, j);
				List<Vegetal> vegetaux = new ArrayList<>(zone.getVegetaux());

				for (Vegetal vegetal : vegetaux) {
					if (zone.getTemperarure() < vegetal.getSeuilTempCritiqueMin() ||
							zone.getTemperarure() > vegetal.getSeuilTempCritiqueMax() || zone.getType() == TypeZone.DESERT) {
						vegetal.mourir(zone);
						System.out.println("TEMPERATURE INSUPPORTABLE\n");
					}

					// Si le niveau d'eau est critique, le végétal cherche à en consommer, s'il n'en trouve pas il meurt
					if (vegetal.getQteEauConsommee() < vegetal.getSeuilEauCritique()) {
						try {
							vegetal.consommerEau(zone);
						} catch (NoWaterException e) {
							vegetal.mourir(zone);
							System.out.println("MORT SUITE A L'ABSENCE D'EAU\n");
						}
					}
				}
				ecosystem.mettreAJourAnimaux(i, j);
			}
		}
	}

	/**
	 * Effectue le vieillissement collectif des animaux et des végétaux dans l'écosystème.
	 * Les animaux et les végétaux vieillissent d'un an.
	 *
	 * @param ecosystem L'écosystème
	 */
	public static void vieillissementCollectif(Ecosystem ecosystem) {
		int nbCasesL = ecosystem.getNbCasesL();
		int nbCasesH = ecosystem.getNbCasesH();

		for (int i = 0; i < nbCasesL; i++) {
			for (int j = 0; j < nbCasesH; j++) {
				Zone zone = ecosystem.getZone(i, j);
				List<Animal> animaux = new ArrayList<>(zone.getAnimaux()); // Crée une copie de la liste d'animaux
				List<Vegetal> vegetaux = new ArrayList<>(zone.getVegetaux());

				for (Animal animal : animaux) {
					animal.vieillir();
				}
				for (Vegetal vegetal : vegetaux) {
					vegetal.vieillir();
				}
				ecosystem.mettreAJourAnimaux(i, j);
			}
		}
	}

	/**
	 * Fait une pause dans l'exécution du programme pendant un certain nombre de millisecondes.
	 *
	 * @param milliseconds Le nombre de millisecondes de la pause
	 */
	private static void pause(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

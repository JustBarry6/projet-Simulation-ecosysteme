package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ecosystem.Animal;
import ecosystem.Arbre;
import ecosystem.Carnivore;
import ecosystem.Herbivore;
import ecosystem.TypeZone;
import ecosystem.Vegetal;
import ecosystem.Vivace;
import ecosystem.Zone;
import ecosystem.ZonePleineException;

/**
 * Classe représentant un écosystème.
 */
public class Ecosystem extends JPanel {
    private int nbCasesL; // Nombre de cases en largeur
    private int nbCasesH; // Nombre de cases en hauteur
    private int nbPixelCoteCase; // Nombre de pixels par côté de la case
    private Zone[][] zone; // Matrice de zones représentant l'écosystème
    private Random random; // Générateur de nombres aléatoires
    private static final String IMAGE_PATH_BICHE = "src/view/images/biche.png";
    private static final String IMAGE_PATH_LION = "src/view/images/lion.png";
    private static final String IMAGE_PATH_SAUTERELLE = "src/view/images/sauterelle.png";
    private static final String IMAGE_PATH_AIGLE = "src/view/images/aigle.png";
    private static final String IMAGE_PATH_PIGEON = "src/view/images/pigeon.png";
    private static final String IMAGE_PATH_CHENILLE = "src/view/images/chenille.png";
    private static final String IMAGE_PATH_ARBRE = "src/view/images/arbre.png";
    private static final String IMAGE_PATH_VIVACE = "src/view/images/vivace.png";

    private static final int POURCENTAGE_REPRODUCTION_PROIE = 25;
    private static final int POURCENTAGE_REPRODUCTION_PREDATEUR = 25;

    /**
     * Constructeur de la classe Ecosystem.
     * @param nbCasesL le nombre de cases en largeur
     * @param nbCasesH le nombre de cases en hauteur
     * @param nbPixelCoteCase le nombre de pixels par côté de la case
     */
    public Ecosystem(int nbCasesL, int nbCasesH, int nbPixelCoteCase) {
        int i, j;
        this.nbCasesL = nbCasesL;
        this.nbCasesH = nbCasesH;
        this.nbPixelCoteCase = nbPixelCoteCase;
        random = new Random();

        JFrame window = new JFrame();
        window.setSize(nbCasesL * nbPixelCoteCase + 50, nbCasesH * nbPixelCoteCase + 50);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(this);
        window.setVisible(true);

        this.zone = new Zone[nbCasesL][nbCasesH];
        // L'écosystème est divisé au début en plaine et désert, on crée donc autant de types de forêt que de désert
        for (i = 0; i < nbCasesL; i++) {
            for (j = 0; j < nbCasesH; j++) {
                if (i < j)
                    zone[i][j] = new Zone(50, 10, TypeZone.FORET);
                else
                    zone[i][j] = new Zone(50, 10, TypeZone.PLAINE);
            }
        }
    }

	public void redessine() {
		repaint();
	}

	/**
	 * Change la couleur de fond d'une case de l'écosystème.
	 *
	 * @param i l'index de la case en largeur
	 * @param j l'index de la case en hauteur
	 * @param c la couleur de fond à attribuer à la case
	 */
	public void colorieFond(int i, int j, Color c) {
	    zone[i][j].setCouleur(c);
	}

	/**
	 * Ajoute un animal à une case de l'écosystème.
	 *
	 * @param i l'index de la case en largeur
	 * @param j l'index de la case en hauteur
	 * @param animal l'animal à ajouter à la case
	 * @throws ZonePleineException si la case est pleine et ne peut pas accueillir l'animal
	 */
	public void addAnimal(int i, int j, Animal animal) throws ZonePleineException {
	    zone[i][j].addAnimal(animal);
	}

	/**
	 * Ajoute un végétal à une case de l'écosystème.
	 *
	 * @param i l'index de la case en largeur
	 * @param j l'index de la case en hauteur
	 * @param vegetal le végétal à ajouter à la case
	 */
	public void addVegetal(int i, int j, Vegetal vegetal) {
	    zone[i][j].addVegetal(vegetal);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Dessiner les cases
		for (int i = 0; i < nbCasesL; i++) {
			for (int j = 0; j < nbCasesH; j++) {
				int cellX = 10 + (i * nbPixelCoteCase);
				int cellY = 10 + (j * nbPixelCoteCase);
				g.setColor(zone[i][j].getCouleur());
				if (zone[i][j].getType() == TypeZone.DESERT) {
					dessinerImage(g, zone[i][j].getImagePath(), cellX, cellY, nbPixelCoteCase);
				} else {
					g.fillRect(cellX, cellY, nbPixelCoteCase, nbPixelCoteCase);
				}

				// Dessiner les animaux
				for (Animal animal : zone[i][j].getAnimaux()) {
					g.setColor(animal.getCouleur());
					if (animal.getNom().equals("Biche")) {
						dessinerImage(g, IMAGE_PATH_BICHE, cellX, cellY, animal.getRayon());
					} else if (animal.getNom().equals("Lion")) {
						dessinerImage(g, IMAGE_PATH_LION, cellX + nbPixelCoteCase / 2 - animal.getRayon() / 2, cellY,
								animal.getRayon());
					} else if (animal.getNom().equals("Sauterelle")) {
						dessinerImage(g, IMAGE_PATH_SAUTERELLE, cellX + nbPixelCoteCase - animal.getRayon(), cellY,
								animal.getRayon());
					} else if (animal.getNom().equals("Aigle")) {
						dessinerImage(g, IMAGE_PATH_AIGLE, cellX + nbPixelCoteCase - animal.getRayon(),
								cellY + nbPixelCoteCase / 2 - animal.getRayon() / 2, animal.getRayon());
					} else if (animal.getNom().equals("Pigeon")) {
						dessinerImage(g, IMAGE_PATH_PIGEON, cellX + nbPixelCoteCase - animal.getRayon(),
								cellY + nbPixelCoteCase - animal.getRayon(), animal.getRayon());
					} else if (animal.getNom().equals("Chenille")) {
						dessinerImage(g, IMAGE_PATH_CHENILLE, cellX, cellY + nbPixelCoteCase - animal.getRayon(),
								animal.getRayon());
					} else {
						g.fillOval(cellX + nbPixelCoteCase / 2 - animal.getRayon() / 2,
								cellY + nbPixelCoteCase / 2 - animal.getRayon() / 2, animal.getRayon(),
								animal.getRayon());
					}
				}

				// Dessiner les végétaux
				for (Vegetal vegetal : zone[i][j].getVegetaux()) {
					g.setColor(vegetal.getCouleur());
					int vegX = cellX + nbPixelCoteCase / 4 - vegetal.getRayon() / 2;
					int vegY = cellY + nbPixelCoteCase / 4 - vegetal.getRayon() / 2;
					if (vegetal instanceof Arbre) {
						dessinerImage(g, IMAGE_PATH_ARBRE, vegX, vegY, vegetal.getRayon());
					} else if (vegetal instanceof Vivace) {
						dessinerImage(g, IMAGE_PATH_VIVACE, vegX + 15, vegY + 15, vegetal.getRayon());
					} else {
						g.fillOval(vegX, vegY, vegetal.getRayon(), vegetal.getRayon());
					}
				}
			}
		}

		// Dessiner les contours des cases
		g.setColor(Color.BLACK);
		g.drawRect(10, 10, nbCasesL * nbPixelCoteCase, nbCasesH * nbPixelCoteCase);

		// Dessiner les lignes verticales
		for (int i = 10; i <= nbCasesL * nbPixelCoteCase; i += nbPixelCoteCase) {
			g.drawLine(i, 10, i, nbCasesH * nbPixelCoteCase + 10);
		}

		// Dessiner les lignes horizontales
		for (int i = 10; i <= nbCasesH * nbPixelCoteCase; i += nbPixelCoteCase) {
			g.drawLine(10, i, nbCasesL * nbPixelCoteCase + 10, i);
		}
	}

	/**
	 * Dessine une image à l'emplacement spécifié avec les dimensions données.
	 *
	 * @param g         le contexte graphique dans lequel dessiner
	 * @param imagePath le chemin vers l'image à dessiner
	 * @param x         la coordonnée x de l'emplacement de l'image
	 * @param y         la coordonnée y de l'emplacement de l'image
	 * @param rayon     le rayon de l'image à dessiner
	 */
	private void dessinerImage(Graphics g, String imagePath, int x, int y, int rayon) {
	    try {
	        BufferedImage image = ImageIO.read(new File(imagePath));
	        int newWidth = rayon;
	        int newHeight = rayon;
	        Image resizedImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
	        g.drawImage(resizedImage, x, y, null);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	/**
	 * Récupère le nombre d'animaux d'une classe spécifique dans une zone donnée.
	 *
	 * @param i      l'index de la case en largeur
	 * @param j      l'index de la case en hauteur
	 * @param animal la classe d'animal spécifique
	 * @return le nombre d'animaux de la classe spécifiée dans la zone donnée
	 */
	public int getNbAnimal(int i, int j, Class<? extends Animal> animal) {
	    Zone z = zone[i][j];
	    int count = z.getNbAnimal(animal);
	    return count;
	}

	/**
	 * Récupère la zone de l'écosystème à l'index spécifié.
	 *
	 * @param i l'index de la case en largeur
	 * @param j l'index de la case en hauteur
	 * @return la zone à l'index spécifié
	 */
	public Zone getZone(int i, int j) {
	    return zone[i][j];
	}

	/**
	 * Récupère le nombre de cases en largeur de l'écosystème.
	 *
	 * @return le nombre de cases en largeur
	 */
	public int getNbCasesL() {
	    return nbCasesL;
	}

	/**
	 * Récupère le nombre de cases en hauteur de l'écosystème.
	 *
	 * @return le nombre de cases en hauteur
	 */
	public int getNbCasesH() {
	    return nbCasesH;
	}

	/**
	 * Place un animal dans une case de l'écosystème.
	 *
	 * @param x      la coordonnée x de la case
	 * @param y      la coordonnée y de la case
	 * @param animal l'animal à placer dans la case
	 * @throws ZonePleineException si la case est pleine et ne peut pas accueillir l'animal
	 */
	public void placerAnimal(int x, int y, Animal animal) throws ZonePleineException {
	    Zone zone = getZone(x, y);
	    if (zone != null) {
	        zone.addAnimal(animal);
	    }
	}

	/**
	 * Place un végétal dans une case de l'écosystème.
	 *
	 * @param x        la coordonnée x de la case
	 * @param y        la coordonnée y de la case
	 * @param vegetal  le végétal à placer dans la case
	 */
	public void placerVegetal(int x, int y, Vegetal vegetal) {
		Zone zone = getZone(x, y);
		if (zone != null && zone.getType() != TypeZone.DESERT) {
			zone.addVegetal(vegetal);
		}
	}

	/**
	 * Met à jour les animaux dans la zone spécifiée.
	 * Effectue la reproduction des proies et des prédateurs, et met à jour la taille des animaux.
	 *
	 * @param i l'index de la case en largeur
	 * @param j l'index de la case en hauteur
	 */
	public void updateAnimaux(int i, int j) {
	    // Récupère la liste des animaux dans la zone spécifiée
	    List<Animal> animaux = this.getZone(i, j).getAnimaux();
	    int countProies = 0;
	    int countPredateurs = 0;

	    // Compte le nombre de proies et de prédateurs dans la zone
	    for (Animal animal : animaux) {
	        if (animal instanceof Herbivore) {
	            countProies++;
	        } else if (animal instanceof Carnivore) {
	            countPredateurs++;
	        }
	    }

	    // Si suffisamment de proies et aucun prédateur, les proies se reproduisent
	    if (countProies >= 2 && countPredateurs == 0) {
	        reproduireProies(i, j, POURCENTAGE_REPRODUCTION_PROIE);
	    }
	    // Si suffisamment de prédateurs et au moins une proie, les prédateurs se reproduisent
	    else if (countPredateurs >= 2 && countProies > 0) {
	        reproduirePredateurs(i, j, POURCENTAGE_REPRODUCTION_PREDATEUR);
	    }

	    // Met à jour les animaux dans la zone
	    mettreAJourAnimaux(i, j);
	}

	/**
	 * Effectue la reproduction des proies dans la zone spécifiée.
	 * Les proies se reproduisent si au moins deux herbivores sont présents dans la zone.
	 * La reproduction a lieu avec un certain pourcentage de chance.
	 *
	 * @param i                      l'index de la case en largeur
	 * @param j                      l'index de la case en hauteur
	 * @param pourcentageReproduction le pourcentage de chance de reproduction des proies
	 */
	public void reproduireProies(int i, int j, int pourcentageReproduction) {
		// Récupère la liste des animaux dans la zone spécifiée
		List<Animal> animaux = zone[i][j].getAnimaux();
		List<Herbivore> herbivores = new ArrayList<>();

		// Filtre les herbivores dans la liste des animaux
		for (Animal animal : animaux) {
			if (animal instanceof Herbivore) {
				herbivores.add((Herbivore) animal);
			}
		}

		// Si au moins deux herbivores sont présents dans la zone
		if (herbivores.size() >= 2) {
			// Effectue une reproduction avec un certain pourcentage de chance
			if (random.nextInt(100) < pourcentageReproduction) {
				Herbivore parent1 = herbivores.get(0);
				Herbivore parent2 = herbivores.get(1);
				Herbivore nouvelAnimal = parent1.seReproduire(parent2);
				try {
					zone[i][j].addAnimal((Animal) nouvelAnimal);
				} catch (ZonePleineException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Nouvel animal herbivore créé : " + ((Animal) nouvelAnimal).getNom());
			}
		}
	}

	/**
	 * Effectue la reproduction des prédateurs dans la zone spécifiée.
	 * Les prédateurs se reproduisent si au moins deux carnivores sont présents dans la zone.
	 * La reproduction a lieu avec un certain pourcentage de chance.
	 *
	 * @param i                      l'index de la case en largeur
	 * @param j                      l'index de la case en hauteur
	 * @param pourcentageReproduction le pourcentage de chance de reproduction des prédateurs
	 */
	public void reproduirePredateurs(int i, int j, int pourcentageReproduction) {
	    // Récupère la liste des animaux dans la zone spécifiée
	    List<Animal> animaux = zone[i][j].getAnimaux();
	    List<Carnivore> carnivores = new ArrayList<>();

	    // Filtre les carnivores dans la liste des animaux
	    for (Animal animal : animaux) {
	        if (animal instanceof Carnivore) {
	            carnivores.add((Carnivore) animal);
	        }
	    }

	    // Si au moins deux carnivores sont présents dans la zone
	    if (carnivores.size() >= 2) {
	        // Effectue une reproduction avec un certain pourcentage de chance
	        if (random.nextInt(100) < pourcentageReproduction) {
	            Carnivore parent1 = carnivores.get(0);
	            Carnivore parent2 = carnivores.get(1);
	            Carnivore nouvelAnimal = parent1.seReproduire(parent2);
	            try {
	                zone[i][j].addAnimal((Animal) nouvelAnimal);
	            } catch (ZonePleineException e) {
	                e.printStackTrace();
	            }
	            System.out.println("Nouvel animal carnivore créé : " + ((Animal) nouvelAnimal).getNom());
	        }
	    }
	}

	/**
	 * Met à jour la taille des animaux dans la zone spécifiée en fonction de leur nombre.
	 * Les animaux du même type ont leur taille augmentée en fonction du nombre d'animaux du même type dans la zone.
	 * Si un animal est le seul de son type dans la zone, sa taille est réinitialisée.
	 *
	 * @param i l'index de la case en largeur
	 * @param j l'index de la case en hauteur
	 */
	public void mettreAJourAnimaux(int i, int j) {
	    // Récupère la liste des animaux dans la zone spécifiée
	    List<Animal> animaux = this.getZone(i, j).getAnimaux();

	    // Parcourt tous les animaux dans la zone
	    for (Animal animal : animaux) {
	        int count = this.getZone(i, j).getNbAnimal(animal.getClass());

	        // Si plusieurs animaux du même type sont présents dans la zone
	        if (count > 1 && count < 3) {
	            int newRadius = animal.getRayon() + count;
	            animal.setRayon(newRadius);
	        } else {
	            // Si l'animal est le seul de son type dans la zone, on réinitialise sa taille
	            animal.setRayon(30);
	        }
	    }
	}


}

package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ecosystem.Animal;
import ecosystem.TypeZone;
import ecosystem.Vegetal;
import ecosystem.Zone;

public class Ecosystem extends JPanel {
	private int nbCasesL, nbCasesH;
	private int nbPixelCoteCase;
	private Zone[][] zone;
	private Random random;

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
		// L'ecosyteme est divise au debut en plaine et desert on cree donc autant de
		// type Foret que desert
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

	public void colorieFond(int i, int j, Color c) {
		zone[i][j].setCouleur(c);
	}

	public void addAnimal(int i, int j, Animal animal) {
		zone[i][j].addAnimal(animal);
	}

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
				g.fillRect(cellX, cellY, nbPixelCoteCase, nbPixelCoteCase);

				// Dessiner les animaux
				for (Animal animal : zone[i][j].getAnimaux()) {
					g.setColor(animal.getCouleur());
					if (animal.getNom().equals("Biche")) {
						dessinerImage(g, "src/view/images/biche.png", cellX, cellY, animal.getRayon());
					} else if (animal.getNom().equals("Lion")) {
						dessinerImage(g, "src/view/images/lion.png", cellX + nbPixelCoteCase / 2 - animal.getRayon() / 2,
								cellY, animal.getRayon());
					} else if (animal.getNom().equals("Sauterelle")) {
						dessinerImage(g, "src/view/images/sauterelle.png", cellX + nbPixelCoteCase - animal.getRayon(), cellY,
								animal.getRayon());
					} else if (animal.getNom().equals("Aigle")) {
						dessinerImage(g, "src/view/images/aigle.png", cellX + nbPixelCoteCase - animal.getRayon(),
								cellY + nbPixelCoteCase / 2 - animal.getRayon() / 2, animal.getRayon());
					} else if (animal.getNom().equals("Pigeon")) {
						dessinerImage(g, "src/view/images/pigeon.png", cellX + nbPixelCoteCase - animal.getRayon(),
								cellY + nbPixelCoteCase - animal.getRayon(), animal.getRayon());
					} else if (animal.getNom().equals("Chenille")) {
						dessinerImage(g, "src/view/images/chenille.png", cellX, cellY + nbPixelCoteCase - animal.getRayon(),
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
					if (vegetal.getNom().equals("Arbre")) {
						dessinerImage(g, "src/view/images/arbre.png", vegX, vegY, vegetal.getRayon());
					} else if (vegetal.getNom().equals("Vivace")) {
						dessinerImage(g, "src/view/images/vivace.png", vegX+10, vegY+10, vegetal.getRayon());
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

	public int getNbAnimal(int i, int j, Class<? extends Animal> animal) {
		Zone z = zone[i][j];
		int count = z.getNbAnimal(animal);
		return count;
	}

	public Zone getZone(int i, int j) {
		return zone[i][j];
	}

	public int getNbCasesL() {
		return nbCasesL;
	}

	public int getNbCasesH() {
		return nbCasesH;
	}

	public void updateAnimaux(int i, int j) {
	    List<Animal> animaux = this.getZone(i, j).getAnimaux();
	    for (Animal animal : animaux) {
	        int count = this.getZone(i, j).getNbAnimal(animal.getClass());
	        if (count > 1) {
	            for (int k = 0; k < count - 1; k++) {
	                Animal removedAnimal = this.getZone(i, j).removeAnimal(animal.getClass());
	                int newRadius = removedAnimal.getRayon() + (count+5);
	                Animal newAnimal = null;
	                try {
	                    newAnimal = animal.getClass().getDeclaredConstructor(int.class).newInstance(newRadius);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	                this.getZone(i, j).addAnimal(newAnimal);
	            }
	        } else {
	            // Si l'animal est le seul de son type dans la zone, on réinitialise son rayon
	            animal.setRayon(15);
	        }
	    }
	}


//	public void updateVegetaux() {
//		for (Zone zone : zone) {
//			List<Vegetal> vegetaux = zone.getVegetaux();
//			for (Class<? extends Vegetal> vegetalClass : vegetalClasses) {
//				int count = zone.getNbVegetal(vegetalClass);
//				if (count > 1) {
//					for (int i = 0; i < count - 1; i++) {
//						zone.removeVegetal(vegetalClass);
//					}
//				}
//			}
//		}
//	}
	
	public void placerAnimal(int x, int y, Animal animal) {
		Zone zone = getZone(x, y);
		if (zone != null) {
			zone.addAnimal(animal);
		}
	}

	public void placerVegetal(int x, int y, Vegetal vegetal) {
		Zone zone = getZone(x, y);
		if (zone != null) {
			zone.addVegetal(vegetal);
		}
	}

	public void update(int i, int j) {
		// updateVegetaux();
		updateAnimaux(i, j);
	}

}

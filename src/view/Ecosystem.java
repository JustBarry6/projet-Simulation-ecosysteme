package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Random;

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
		int[] centre = new int[2];
		int[] coinSupG = new int[2];
		int[] coinSupD = new int[2];
		int[] coinInfG = new int[2];
		int[] coinInfD = new int[2];
		int[] milieuH = new int[2];
		int[] milieuB = new int[2];
		int[] milieuG = new int[2];
		int[] milieuD = new int[2];
		int i, j;
		for (i = 0; i < nbCasesL; i++) {
			for (j = 0; j < nbCasesH; j++) {
				int cellX = 10 + (i * nbPixelCoteCase);
				int cellY = 10 + (j * nbPixelCoteCase);
				g.setColor(zone[i][j].getCouleur());
				g.fillRect(cellX, cellY, nbPixelCoteCase, nbPixelCoteCase);

				// Coordonnées pour le placement dynamique (9 positions possibles)
				centre[0] = cellX + (nbPixelCoteCase / 2);
				centre[1] = cellY + (nbPixelCoteCase / 2);

				coinSupG[0] = cellX + 2; // coordonée X decalee de 2 pour ne pas toucher les bords
				coinSupG[1] = cellY + 2; // coordonnée Y

				milieuH[0] = coinSupG[0] + (nbPixelCoteCase / 2);
				milieuH[1] = cellY + 2;

				coinSupD[0] = milieuH[0] + (nbPixelCoteCase / 2) - 4;
				coinSupD[1] = cellY + 2;

				milieuD[0] = coinSupD[0] - 1;
				milieuD[1] = cellY + (nbPixelCoteCase / 2);

				coinInfD[0] = coinSupD[0] - 1; // coordonée X
				coinInfD[1] = milieuD[1] + (nbPixelCoteCase / 2) - 2; // coordonnée Y

				milieuB[0] = coinSupD[0] - (nbPixelCoteCase / 2);
				milieuB[1] = coinInfD[1] - 2;

				coinInfG[0] = milieuB[0] - (nbPixelCoteCase / 2) + 4;
				coinInfG[1] = coinInfD[1] - 2;

				milieuG[0] = coinInfG[0] + 2;
				milieuD[1] = coinInfD[1] - (nbPixelCoteCase / 2);

				for (Animal animal : zone[i][j].getAnimaux()) {
					g.setColor(animal.getCouleur());
					switch (animal.getNom()) {
					case "biche":
						g.fillOval(coinSupG[0], coinSupG[1], animal.getRayon(), animal.getRayon());
						break;
					case "lion":
						g.fillOval(milieuH[0] - animal.getRayon() / 2, milieuH[1], animal.getRayon(),
								animal.getRayon());
						break;
					case "sauterelle":
						g.fillOval(coinSupD[0] - animal.getRayon(), coinSupD[1], animal.getRayon(), animal.getRayon());
						break;
					case "aigle":
						g.fillOval(milieuD[0] - animal.getRayon(), milieuD[1] - animal.getRayon() / 2,
								animal.getRayon(), animal.getRayon());
						break;
					case "pigeon":
						g.fillOval(coinInfD[0] - animal.getRayon(), coinInfD[1] - (animal.getRayon()),
								animal.getRayon(), animal.getRayon());
						break;
					case "chenille":
						g.fillOval(coinInfG[0], coinInfD[1] - (animal.getRayon()), animal.getRayon(),
								animal.getRayon());
						break;
					default:
						g.fillOval(centre[0] - animal.getRayon() / 2, centre[1] - animal.getRayon() / 2,
								animal.getRayon(), animal.getRayon());
						break;
					}
				}

				for (Vegetal vegetal : zone[i][j].getVegetaux()) {
					g.setColor(vegetal.getCouleur());
					switch (vegetal.getNom()) {
					case "Arbre":
						g.fillOval(centre[0] - vegetal.getRayon() / 2, centre[1] - vegetal.getRayon() / 2,
								vegetal.getRayon(), vegetal.getRayon());
						break;
					case "Vivace":
						g.fillOval(centre[0] - vegetal.getRayon() / 2, centre[1] - vegetal.getRayon() / 2,
								vegetal.getRayon(), vegetal.getRayon());
						break;
					default:
						break;
					}
				}

			}
		}

		g.setColor(Color.BLACK);
		g.drawRect(10, 10, nbCasesL * nbPixelCoteCase, nbCasesH * nbPixelCoteCase);

		for (i = 10; i <= nbCasesL * nbPixelCoteCase; i += nbPixelCoteCase) {
			g.drawLine(i, 10, i, nbCasesH * nbPixelCoteCase + 10);
		}

		for (i = 10; i <= nbCasesH * nbPixelCoteCase; i += nbPixelCoteCase) {
			g.drawLine(10, i, nbCasesL * nbPixelCoteCase + 10, i);
		}
	}

	public int getNbAnimal(int i, int j, Class<? extends Animal> animalClass) {
		Zone z = zone[i][j];
		int count = z.getNbAnimal(animalClass);
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
}

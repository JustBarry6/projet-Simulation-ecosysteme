import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
		for (i = 0; i < nbCasesL; i++) {
			for (j = 0; j < nbCasesH; j++) {
				zone[i][j] = new Zone(50, 10, TypeZone.DESERT);
			}
		}
	}

	public void redessine() {
		repaint();
	}

	public void colorieFond(int i, int j, Color c) {
		zone[i][j].setCouleur(c);
	}

	public void addAnimal(int i, int j, int rayon, Color c) {
		zone[i][j].addAnimal(new Lion(rayon, c));
		zone[i][j].addAnimal(new Chenille(rayon+6, c));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int i, j;
		for (i = 0; i < nbCasesL; i++) {
			for (j = 0; j < nbCasesH; j++) {
				int cellX = 10 + (i * nbPixelCoteCase);
				int cellY = 10 + (j * nbPixelCoteCase);
				g.setColor(zone[i][j].getCouleur());
				g.fillRect(cellX, cellY, nbPixelCoteCase, nbPixelCoteCase);

				for (Animal animal : zone[i][j].getAnimaux()) {
					g.setColor(animal.getCouleur());
					g.fillOval(cellX + 10, cellY + 10, animal.getRayon(), animal.getRayon());
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

	public int getNbAnimal(int i, int j, Color couleur) {
		int count = 0;
		Zone z = zone[i][j];
		for (Animal animal : z.getAnimaux()) {
			if (animal.getCouleur().equals(couleur)) {
				count++;
			}
		}
		return count;
	}

	public void moveProies(int i, int j) {
		int nbLapins = getNbAnimal(i, j, Color.GREEN);
		for (int k = 0; k < nbLapins; k++) {
			if (random.nextInt(100) < 25) {
				int newI = (i + random.nextInt(3) - 1 + nbCasesL) % nbCasesL;
				int newJ = (j + random.nextInt(3) - 1 + nbCasesH) % nbCasesH;
				if (getNbAnimal(newI, newJ, Color.GREEN) == 0 && getNbAnimal(newI, newJ, Color.RED) == 0) {
					moveAnimal(i, j, newI, newJ, Color.GREEN);
				}
			}
		}
	}

	public void movePredateurs(int i, int j) {
		int nbAigles = getNbAnimal(i, j, Color.RED);
		for (int k = 0; k < nbAigles; k++) {
			if (random.nextInt(100) < 25) {
				int newI = (i + random.nextInt(3) - 1 + nbCasesL) % nbCasesL;
				int newJ = (j + random.nextInt(3) - 1 + nbCasesH) % nbCasesH;
				if (getNbAnimal(newI, newJ, Color.GREEN) == 0 && getNbAnimal(newI, newJ, Color.RED) == 0) {
					moveAnimal(i, j, newI, newJ, Color.RED);
				}
			}
		}
	}

	private void moveAnimal(int x, int y, int newX, int newY, Color couleur) {
		Zone currentZone = zone[x][y];
		Zone newZone = zone[newX][newY];
		Animal animalToMove = currentZone.removeAnimal(couleur);
		newZone.addAnimal(animalToMove);
	}

	public void removeAnimal(int x, int y, Color couleur) {
		System.out.println("Animal a supprimer");
		// zone[x][y].removeIf(Animal -> Animal.getX() == x && Animal.getY() == y &&
		// Animal.getCouleur().equals(couleur));
	}

	public Zone getZone(int i, int j) {
		return zone[i][j];
	}

}

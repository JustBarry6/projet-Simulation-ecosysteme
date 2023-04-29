package view ; 

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ecosystem.Animal;
import ecosystem.TypeZone;
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

	public void addAnimal(int i, int j, Animal animal) {
        zone[i][j].addAnimal(animal);
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

	public int getNbAnimal(int i, int j, Animal animal) {
	    Zone z = zone[i][j];
	    int count = z.getNbAnimal(animal.getType());
	    return count;
	}

	public void moveAnimaux(int i, int j, int p, Random r, Animal animal) {
	    int nbAnimaux = getNbAnimal(i, j, animal);
	    for (int k = 0; k < nbAnimaux; k++) {
	        if (r.nextInt(100) < p) {
	            int newI = (i + r.nextInt(3) - 1 + nbCasesL) % nbCasesL;
	            int newJ = (j + r.nextInt(3) - 1 + nbCasesH) % nbCasesH;
	            if (getNbAnimal(newI, newJ, animal) == 0) {
	                moveAnimal(i, j, newI, newJ, animal);
	            }
	        }
	    }
	}


	private void moveAnimal(int x, int y, int newX, int newY, Animal animal) {
		Zone currentZone = zone[x][y];
		Zone newZone = zone[newX][newY];
		Animal animalToMove = currentZone.removeAnimal(animal);
		newZone.addAnimal(animalToMove);
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
	
	public List<Animal> getAnimaux(int i, int j) {
        return zone[i][j].getAnimaux();
    }

}

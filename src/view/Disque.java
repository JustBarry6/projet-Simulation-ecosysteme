import java.awt.Color;

public class Disque {
	private int x;
	private int y;
	private int rayon;
	private Color couleur;

	public Disque(int x, int y, int rayon, Color couleur) {
		this.x = x;
		this.y = y;
		this.rayon = rayon;
		this.couleur = couleur;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getRayon() {
		return rayon;
	}

	public Color getCouleur() {
		return couleur;
	}
}

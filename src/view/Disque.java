import java.awt.Color;

class Disque {

    private int rayon;
    private Color couleur;

    public Disque(int rayon, Color couleur) {
        this.rayon = rayon;
        this.couleur = couleur;
    }

    public int getRayon() {
        return rayon;
    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
}
